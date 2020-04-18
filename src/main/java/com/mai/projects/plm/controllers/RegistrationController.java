package com.mai.projects.plm.controllers;


import com.mai.projects.plm.model.request.RegistrationRequest;
import com.mai.projects.plm.model.response.ResponseObject;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Api
@Validated
@RequestMapping(path = "/registration", produces = MediaType.APPLICATION_JSON_VALUE)
public interface RegistrationController {

	@PostMapping()
	ResponseEntity<ResponseObject<Object>> registration(@Valid RegistrationRequest registrationRequest);
}
