package com.mai.projects.plm.controllers;

import com.mai.projects.plm.model.request.AddProductRequest;
import com.mai.projects.plm.model.response.ProductsResponse;
import com.mai.projects.plm.model.response.ResponseObject;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Api
@RequestMapping("product")
public interface ProductController {
	@PostMapping()
	ResponseEntity<ResponseObject<Object>> addProduct(@RequestBody AddProductRequest addProductRequest);

	@GetMapping()
	ResponseEntity<ResponseObject<ProductsResponse>> fetchProducts();
}
