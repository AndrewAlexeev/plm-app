package com.mai.projects.plm.controllers;

import com.mai.projects.plm.entities.Document;
import com.mai.projects.plm.entities.Product;
import com.mai.projects.plm.entities.Stage;
import com.mai.projects.plm.enums.ErrorEnum;
import com.mai.projects.plm.enums.StatusEnum;
import com.mai.projects.plm.exception.ServerException;
import com.mai.projects.plm.model.response.ResponseObject;
import com.mai.projects.plm.repository.DocumentRepository;
import com.mai.projects.plm.repository.ProductRepository;
import com.mai.projects.plm.repository.StageRepository;
import liquibase.util.file.FilenameUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DocumentControllerImpl extends AbstractMainController implements DocumentController {

	private final DocumentRepository documentRepository;
	private final StageRepository stageRepository;
	private final ProductRepository productRepository;

	private final static String SAVE_DIR = "";
	private final Path rootLocation = Paths.get("upload");

	@PostConstruct
	public void init() throws IOException {
		Files.createDirectories(rootLocation);
	}

	@Override
	public ResponseEntity<ResponseObject<Object>> upload(MultipartFile file, Long docId, HttpServletRequest httpServletRequest) throws IOException {

		//String path2 = httpServletRequest.getServletContext().getRealPath("");
		String fileName = docId.toString() +"."+ FilenameUtils.getExtension(file.getOriginalFilename());

		Files.copy(file.getInputStream(), rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
		Document document = documentRepository.
				findById(docId)
				.orElseThrow(() -> new ServerException(ErrorEnum.INVALID_DOCUMENT_ID, List.of(docId.toString())));

		document.setPath(fileName);
		Stage stage = document.getStage();
		StatusEnum newStatusStage = fetchNewStageStatus(stage);
		if (stage.getStatus() != newStatusStage) {
			stage.setStatus(newStatusStage);
			Product product = stage.getProduct();
			StatusEnum newProductStatus = fetchNewProductStatus(product);
			if (product.getStatus() != newProductStatus) {
				product.setStatus(newProductStatus);
				productRepository.save(product);
			}
			stageRepository.save(stage);
		}
		documentRepository.save(document);

		return prepareEmptyResponseEntity();

	}

	@Override
	public ResponseEntity<ByteArrayResource> download(Long docId) throws IOException {

		Document document = documentRepository.findById(docId)
				.orElseThrow(() -> new ServerException(ErrorEnum.INVALID_DOCUMENT_ID, List.of(docId.toString())));

		Path path = rootLocation.resolve(document.getPath());
		byte[] data = Files.readAllBytes(path);
		ByteArrayResource resource = new ByteArrayResource(data);
		String contentType = Files.probeContentType(path);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline")
				.header(HttpHeaders.CONTENT_TYPE, contentType)
				.contentLength(data.length)
				.body(resource);
	}

	private StatusEnum fetchNewStageStatus(Stage stage) {
		return stage.getDocuments().stream().anyMatch(doc -> doc.getPath() == null) ? StatusEnum.STARTED : StatusEnum.FINISHED;
	}

	private StatusEnum fetchNewProductStatus(Product product) {
		return product.getStages().stream().allMatch(stage -> stage.getStatus() == StatusEnum.FINISHED) ? StatusEnum.FINISHED : StatusEnum.STARTED;
	}
}
