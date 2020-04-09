package com.mai.projects.plm.controllers;

import com.mai.projects.plm.model.response.ResponseObject;
import com.mai.projects.plm.model.response.UserResponse;
import com.mai.projects.plm.repository.UserRepository;
import com.mai.projects.plm.utils.User2UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl extends AbstractMainController implements UserController {
	private final UserRepository userRepository;

	@Override
	public ResponseEntity<ResponseObject<List<UserResponse>>> fetchAllUsers() {

		List<UserResponse> userResponseList = userRepository.findAll().stream().map(User2UserResponse::convert).collect(Collectors.toList());
		return prepareResponseEntity(userResponseList);
	}
}
