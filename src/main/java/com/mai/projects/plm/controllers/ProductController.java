package com.mai.projects.plm.controllers;

import com.mai.projects.plm.model.request.AddProductRequest;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Api
@RequestMapping("product")
public interface ProductController {
	@PostMapping()
	ResponseEntity addProduct(@RequestBody AddProductRequest addProductRequest);
}
