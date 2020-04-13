package com.mai.projects.plm.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mai.projects.plm.enums.StatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class StageResponse {
	private Integer serialNumber;
	private String title;
	@JsonFormat
	private LocalDate startDate;
	private LocalDate finishDate;
	private List<DocumentResponse> documents;
	private StatusEnum status;
}
