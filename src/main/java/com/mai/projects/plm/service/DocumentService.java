package com.mai.projects.plm.service;

import com.mai.projects.plm.model.document.DocumentInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DocumentService
{
	void upload(MultipartFile file,Long docId) throws IOException;

	DocumentInfo download(Long docId) throws IOException;
}
