package com.mai.projects.plm.controllers;

import com.mai.projects.plm.entities.User;
import com.mai.projects.plm.model.response.ResponseObject;
import com.mai.projects.plm.model.response.TokenResponse;
import com.mai.projects.plm.security.jwt.JwtTokenProvider;
import com.mai.projects.plm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class AuthenticationControllerImpl extends AbstractMainController implements AuthenticationController {
	private final AuthenticationManager authenticationManager;

	private final JwtTokenProvider jwtTokenProvider;

	private final UserService userService;

	@ResponseBody
	public ResponseEntity<ResponseObject<TokenResponse>> login(Principal principal) {
		User user = userService.findByUserName(principal.getName());

		String token = jwtTokenProvider.createToken(principal.getName(), user.getRoles());

		TokenResponse tokenResponse = new TokenResponse();
		tokenResponse.setToken(token);

		return prepareResponseEntity(tokenResponse);

	}

}
