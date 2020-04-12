package com.mai.projects.plm.controllers;

import com.mai.projects.plm.model.request.AddProductRequest;
import com.mai.projects.plm.model.response.ProductResponse;
import com.mai.projects.plm.model.response.ProductsResponse;
import com.mai.projects.plm.model.response.ResponseObject;
import com.mai.projects.plm.utils.ProductDetailResponse;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Api
@RequestMapping(path = "product", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ProductController {
	@PostMapping()
	ResponseEntity<ResponseObject<Object>> addProduct(@RequestBody AddProductRequest addProductRequest);

	@GetMapping()
	ResponseEntity<ResponseObject<ProductsResponse>> fetchProducts();

	@GetMapping(value = "/{productId}")
	ResponseEntity<ResponseObject<ProductDetailResponse>> fetchProduct(@PathVariable Long productId);
}
