package com.mai.projects.plm.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DocumentResponse {
	private String title;
	private UserResponse user;
	private String path;
}
