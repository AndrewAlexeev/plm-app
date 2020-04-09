package com.mai.projects.plm.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Valid
@NoArgsConstructor
public class DocumentContext {
	@NotBlank
	private String title;
	@NotNull
	private Long employeeId;
}
