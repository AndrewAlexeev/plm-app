package com.mai.projects.plm.controllers;

import com.mai.projects.plm.model.document.DocumentInfo;
import com.mai.projects.plm.model.response.ResponseObject;
import com.mai.projects.plm.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DocumentControllerImpl extends AbstractMainController implements DocumentController {

	private final DocumentService documentService;

	@Override
	public ResponseEntity<ResponseObject<Object>> upload(MultipartFile file, Long docId, HttpServletRequest httpServletRequest) throws IOException {
		return prepareEmptyResponseEntity();
	}

	@Override
	public ResponseEntity<ByteArrayResource> download(Long docId) throws IOException {
		DocumentInfo documentInfo = documentService.download(docId);
		ByteArrayResource resource = new ByteArrayResource(documentInfo.getData());
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline")
				.header(HttpHeaders.CONTENT_TYPE, documentInfo.getContentType())
				.contentLength(documentInfo.getData().length)
				.body(resource);
	}


}
