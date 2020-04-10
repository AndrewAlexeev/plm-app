package com.mai.projects.plm.utils;

import com.mai.projects.plm.entities.Document;
import com.mai.projects.plm.entities.Stage;
import com.mai.projects.plm.entities.User;
import com.mai.projects.plm.model.request.StageContext;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StageContext2StageAdapter {
	public static List<Stage> convert(List<StageContext> stageContexts, List<User> users) {
		return stageContexts
				.stream()
				.map(stageContext -> convert(stageContext, users))
				.collect(Collectors.toList());
	}

	public static Stage convert(StageContext stageContext, List<User> users) {
		Stage stage = new Stage();
		stage.setStartDate(stageContext.getStartDate());
		stage.setFinishDate(stageContext.getFinishDate());
		stage.setSerialNumber(stageContext.getSerialNumber());
		stage.setTitle(stageContext.getTitle());
		Map<Long, User> userHashMap = users.stream().collect(Collectors.toMap(User::getId, Function.identity()));

		stage.setDocuments(stageContext.getDocuments().stream().map(documentContext -> {
			Document document = new Document();
			document.setStage(stage);
			document.setTitle(documentContext.getTitle());
			document.setUser(userHashMap.get(documentContext.getEmployeeId()));
			return document;
		}).collect(Collectors.toList()));
		return stage;
	}
}
