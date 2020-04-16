package com.mai.projects.plm.controllers;


import com.mai.projects.plm.model.response.ResponseObject;
import com.mai.projects.plm.model.response.TokenResponse;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
@Api
public interface AuthenticationController {
	@GetMapping()
	ResponseEntity<ResponseObject<TokenResponse>> login(Principal principal);
}
