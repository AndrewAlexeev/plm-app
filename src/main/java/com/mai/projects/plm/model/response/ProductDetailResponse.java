package com.mai.projects.plm.model.response;

import com.mai.projects.plm.enums.StatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductDetailResponse {
	private Long id;
	private String title;
	private String serialNumber;
	private List<StageResponse> stages;
	private StatusEnum status;
}
