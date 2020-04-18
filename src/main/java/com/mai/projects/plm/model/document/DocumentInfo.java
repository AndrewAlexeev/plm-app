package com.mai.projects.plm.model.document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DocumentInfo {
	private  byte[] data;
	private String contentType;
}
