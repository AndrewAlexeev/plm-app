package com.mai.projects.plm.utils;

import com.mai.projects.plm.entities.Stage;
import com.mai.projects.plm.model.request.StageContext;

import java.util.List;
import java.util.stream.Collectors;

public class StageContext2Stage {
	public static List<Stage> convert(List<StageContext> stageContexts) {
		return stageContexts
				.stream()
				.map(StageContext2Stage::convert)
				.collect(Collectors.toList());
	}

	public static Stage convert(StageContext stageContext) {
		Stage stage = new Stage();
		stage.setStartDate(stageContext.getStartDate());
		stage.setFinishDate(stageContext.getFinishDate());
		stage.setSerialNumber(stageContext.getSerialNumber());
		stage.setTitle(stageContext.getTitle());
		return stage;
	}
}
