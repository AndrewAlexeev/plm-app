package com.mai.projects.plm.utils;

import com.mai.projects.plm.entities.Document;
import com.mai.projects.plm.model.response.DocumentResponse;

public class Document2DocumentResponseAdapter {
	public static DocumentResponse convert(Document document){
		DocumentResponse documentResponse = new DocumentResponse();
		documentResponse.setPath(document.getPath());
		documentResponse.setTitle(document.getTitle());
		documentResponse.setUser(User2UserResponseAdapter.convert(document.getUser()));
		return documentResponse;
	}
}
