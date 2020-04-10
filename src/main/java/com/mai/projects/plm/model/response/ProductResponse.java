package com.mai.projects.plm.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponse {
	private Long id;
	private String title;
	private String serialNumber;
}
