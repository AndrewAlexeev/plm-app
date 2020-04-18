package com.mai.projects.plm.controllers;

import com.mai.projects.plm.entities.User;
import com.mai.projects.plm.model.response.ResponseObject;
import com.mai.projects.plm.model.response.UserResponse;
import com.mai.projects.plm.service.UserService;
import com.mai.projects.plm.utils.User2UserResponseAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl extends AbstractMainController implements UserController {
	private final UserService userService;

	@Override
	public ResponseEntity<ResponseObject<List<UserResponse>>> fetchAllUsers() {

		List<UserResponse> userResponseList = userService
				.findAll()
				.stream()
				.map(User2UserResponseAdapter::convert)
				.collect(Collectors.toList());
		return prepareResponseEntity(userResponseList);
	}

	@Override
	public ResponseEntity<ResponseObject<UserResponse>> fetchUser(Long userId) {
		User user = userService.findById(userId);
		return prepareResponseEntity(User2UserResponseAdapter.convert(user));
	}
}
