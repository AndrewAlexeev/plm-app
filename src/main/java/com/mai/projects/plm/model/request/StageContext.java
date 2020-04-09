package com.mai.projects.plm.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Valid
@NoArgsConstructor
public class StageContext {
	@NotBlank
	private Integer serialNumber;
	@NotBlank
	private String title;
	@NotNull
	private LocalDate startDate;
	@NotNull
	private LocalDate finishDate;
}
