package com.mai.projects.plm.utils;

import com.mai.projects.plm.entities.Stage;
import com.mai.projects.plm.model.response.DocumentResponse;
import com.mai.projects.plm.model.response.StageResponse;

import java.util.List;
import java.util.stream.Collectors;

public class Stage2StageResponseAdapter {
	public static StageResponse convert(Stage stage) {
		StageResponse stageResponse = new StageResponse();
		stageResponse.setFinishDate(stage.getFinishDate());
		stageResponse.setStartDate(stage.getStartDate());
		stageResponse.setSerialNumber(stage.getSerialNumber());
		stageResponse.setTitle(stage.getTitle());

		List<DocumentResponse> documentResponseList = stage
				.getDocuments()
				.stream()
				.map(Document2DocumentResponseAdapter::convert)
				.collect(Collectors.toList());

		stageResponse.setDocuments(documentResponseList);

		return stageResponse;
	}
}
