package com.mai.projects.plm.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Valid
@NoArgsConstructor
public class AddProductRequest {
	@NotBlank
	private String productName;
	@NotBlank
	private String productNumber;
	@NotEmpty
	private List<StageContext> stages;

}
