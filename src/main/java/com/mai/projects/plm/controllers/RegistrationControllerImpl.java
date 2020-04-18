package com.mai.projects.plm.controllers;

import com.mai.projects.plm.entities.Status;
import com.mai.projects.plm.entities.User;
import com.mai.projects.plm.model.request.RegistrationRequest;
import com.mai.projects.plm.model.response.ResponseObject;
import com.mai.projects.plm.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@Slf4j
@RestController
@AllArgsConstructor
public class RegistrationControllerImpl extends AbstractMainController implements RegistrationController {
	private final UserService userService;

	@Override
	public ResponseEntity<ResponseObject<Object>> registration(RegistrationRequest registrationRequest) {
		User user = new User();

		user.setUserName(registrationRequest.getUserName());
		user.setFirstName(registrationRequest.getFirstName());
		user.setLastName(registrationRequest.getLastName());
		user.setEmail(registrationRequest.getEmail());
		user.setPassword(registrationRequest.getPassword());
		user.setCreated(new Date());
		user.setUpdated(new Date());
		user.setStatus(Status.ACTIVE);
		userService.register(user);

		return prepareResponseEntity();
	}
}
