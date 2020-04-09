package com.mai.projects.plm.controllers;

import com.mai.projects.plm.model.response.ResponseObject;
import com.mai.projects.plm.model.response.UserResponse;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Api
@Validated
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public interface UserController {
	@GetMapping()
	ResponseEntity<ResponseObject<List<UserResponse>>> fetchAllUsers();
}
