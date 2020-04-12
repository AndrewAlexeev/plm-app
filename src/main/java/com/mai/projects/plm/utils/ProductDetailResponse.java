package com.mai.projects.plm.utils;

import com.mai.projects.plm.model.response.StageResponse;
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
}
