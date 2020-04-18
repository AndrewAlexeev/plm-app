package com.mai.projects.plm.controllers;

import com.mai.projects.plm.entities.Product;
import com.mai.projects.plm.entities.User;
import com.mai.projects.plm.model.request.AddProductRequest;
import com.mai.projects.plm.model.request.DocumentContext;
import com.mai.projects.plm.model.response.ProductDetailResponse;
import com.mai.projects.plm.model.response.ProductResponse;
import com.mai.projects.plm.model.response.ProductsResponse;
import com.mai.projects.plm.model.response.ResponseObject;
import com.mai.projects.plm.service.ProductService;
import com.mai.projects.plm.service.UserService;
import com.mai.projects.plm.utils.AddProductRequest2ProductAdapter;
import com.mai.projects.plm.utils.Product2ProductResponseAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl extends AbstractMainController implements ProductController {

	private final UserService userService;
	private final ProductService productService;

	@Override
	public ResponseEntity<ResponseObject<Object>> addProduct(AddProductRequest addProductRequest) {
		List<Long> userId = fetchUsersId(addProductRequest);
		List<User> users = userService.findAllById(userId);

		Product product = AddProductRequest2ProductAdapter.convert(addProductRequest, users);
		productService.addProduct(product);
		return prepareEmptyResponseEntity();
	}

	@Override
	public ResponseEntity<ResponseObject<ProductsResponse>> fetchProducts() {
		List<ProductResponse> productResponses = productService
				.findAll()
				.stream()
				.map(Product2ProductResponseAdapter::convert)
				.collect(Collectors.toList());
		ProductsResponse productsResponse = new ProductsResponse();
		productsResponse.setProducts(productResponses);
		return prepareResponseEntity(productsResponse);
	}

	@Override
	public ResponseEntity<ResponseObject<ProductDetailResponse>> fetchProduct(Long productId) {
		Product product = productService.findById(productId);

		return prepareResponseEntity(Product2ProductResponseAdapter.convertDetail(product));
	}

	private List<Long> fetchUsersId(AddProductRequest addProductRequest) {
		return addProductRequest
				.getStages()
				.stream()
				.flatMap(stageContext -> stageContext.getDocuments().stream().map(DocumentContext::getEmployeeId))
				.collect(Collectors.toList());
	}
}
