package com.mai.projects.plm.model.response;

import com.mai.projects.plm.enums.StatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponse {
	private Long id;
	private String title;
	private String serialNumber;
	private StatusEnum status;
}
