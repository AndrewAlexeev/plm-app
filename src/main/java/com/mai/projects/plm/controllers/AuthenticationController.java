package com.mai.projects.plm.controllers;


import com.mai.projects.plm.model.response.ResponseObject;
import com.mai.projects.plm.model.response.TokenResponse;
import com.mai.projects.plm.security.jwt.model.JwtUser;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RequestMapping(value = "/api/v1/auth/login", produces = MediaType.APPLICATION_JSON_VALUE)
@Api
public interface AuthenticationController {
	@PostMapping()
	ResponseEntity<ResponseObject<TokenResponse>> login(Principal principal);
}
