package com.mai.projects.plm.controllers;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequestMapping(value = "document/")
public interface DocumentController {
	@PostMapping(value = "upload/{docId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	ResponseEntity<ByteArrayResource> upload(@RequestParam("file") MultipartFile file, @PathVariable Long docId, HttpServletRequest httpServletRequest) throws IOException;
}
