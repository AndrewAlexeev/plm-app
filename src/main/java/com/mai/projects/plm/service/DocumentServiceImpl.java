package com.mai.projects.plm.service;

import com.mai.projects.plm.entities.Document;
import com.mai.projects.plm.entities.Product;
import com.mai.projects.plm.entities.Stage;
import com.mai.projects.plm.enums.ErrorEnum;
import com.mai.projects.plm.enums.StatusEnum;
import com.mai.projects.plm.exception.BaseServerException;
import com.mai.projects.plm.model.document.DocumentInfo;
import com.mai.projects.plm.repository.DocumentRepository;
import com.mai.projects.plm.repository.ProductRepository;
import com.mai.projects.plm.repository.StageRepository;
import liquibase.util.file.FilenameUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
	private final DocumentRepository documentRepository;
	private final StageRepository stageRepository;
	private final ProductRepository productRepository;

	private final static String FILE_NAME_FORMAT = "%s.%s";

	private final Path rootLocation = Paths.get("upload");

	@PostConstruct
	public void init() throws IOException {
		Files.createDirectories(rootLocation);
	}

	@Override
	public void upload(MultipartFile file, Long docId) throws IOException {

		String fileName = String.format(FILE_NAME_FORMAT, docId, FilenameUtils.getExtension(file.getOriginalFilename()));

		Files.copy(file.getInputStream(), rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
		Document document = documentRepository.
				findById(docId)
				.orElseThrow(() -> new BaseServerException(ErrorEnum.INVALID_DOCUMENT_ID, List.of(docId.toString())));

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

	}

	@Override
	public DocumentInfo download(Long docId) throws IOException {
		Document document = documentRepository.findById(docId)
				.orElseThrow(() -> new BaseServerException(ErrorEnum.INVALID_DOCUMENT_ID, List.of(docId.toString())));

		Path path = rootLocation.resolve(document.getPath());
		DocumentInfo documentInfo = new DocumentInfo();
		documentInfo.setData(Files.readAllBytes(path));
		documentInfo.setContentType(Files.probeContentType(path));
		return documentInfo;
	}


	private StatusEnum fetchNewStageStatus(Stage stage) {
		return stage
				.getDocuments()
				.stream()
				.anyMatch(doc -> doc.getPath() == null)
				? StatusEnum.STARTED
				: StatusEnum.FINISHED;
	}

	private StatusEnum fetchNewProductStatus(Product product) {
		return product.getStages().stream().allMatch(stage -> stage.getStatus() == StatusEnum.FINISHED) ? StatusEnum.FINISHED : StatusEnum.STARTED;
	}
}
