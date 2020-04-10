package com.mai.projects.plm.utils;

import com.mai.projects.plm.entities.User;
import com.mai.projects.plm.model.response.UserResponse;

public class User2UserResponseAdapter {
	public static UserResponse convert(User user) {
		UserResponse userResponse = new UserResponse();
		userResponse.setEmail(user.getEmail());
		userResponse.setFirstName(user.getFirstName());
		userResponse.setLastName(user.getLastName());
		userResponse.setId(user.getId());
		userResponse.setUserName(user.getUserName());
		return userResponse;
	}
}
