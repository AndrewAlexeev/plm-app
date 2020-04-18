package com.mai.projects.plm.controllers;

import com.mai.projects.plm.entities.Status;
import com.mai.projects.plm.entities.User;
import com.mai.projects.plm.enums.ErrorEnum;
import com.mai.projects.plm.exception.ServerException;
import com.mai.projects.plm.model.request.RegistrationRequest;
import com.mai.projects.plm.model.response.ResponseObject;
import com.mai.projects.plm.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@Slf4j
public class RegistrationControllerImpl extends AbstractMainController implements RegistrationController {
	private final UserService userService;

	@Override
	public ResponseEntity<ResponseObject<Object>> registration(@Valid RegistrationRequest registrationRequest) {
		User user = new User();

		user.setUserName(registrationRequest.getUserName());
		user.setFirstName(registrationRequest.getFirstName());
		user.setLastName(registrationRequest.getLastName());
		user.setEmail(registrationRequest.getEmail());
		user.setPassword(registrationRequest.getPassword());
		user.setCreated(new Date());
		user.setUpdated(new Date());
		user.setStatus(Status.ACTIVE);
		try {
			userService.register(user);
		} catch (DataIntegrityViolationException ex) {
			throw new ServerException(ErrorEnum.USERNAME_OR_EMAIL_ARE_ALWAYS_EXIST);
		}
		return prepareResponseEntity();
	}
}
