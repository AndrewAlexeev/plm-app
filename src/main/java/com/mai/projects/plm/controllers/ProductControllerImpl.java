package com.mai.projects.plm.controllers;

import com.mai.projects.plm.entities.Product;
import com.mai.projects.plm.entities.User;
import com.mai.projects.plm.enums.ErrorEnum;
import com.mai.projects.plm.exception.ServerException;
import com.mai.projects.plm.model.request.AddProductRequest;
import com.mai.projects.plm.model.request.DocumentContext;
import com.mai.projects.plm.model.response.ProductResponse;
import com.mai.projects.plm.model.response.ProductsResponse;
import com.mai.projects.plm.model.response.ResponseObject;
import com.mai.projects.plm.repository.DocumentRepository;
import com.mai.projects.plm.repository.ProductRepository;
import com.mai.projects.plm.repository.StageRepository;
import com.mai.projects.plm.repository.UserRepository;
import com.mai.projects.plm.utils.AddProductRequest2ProductAdapter;
import com.mai.projects.plm.utils.Product2ProductResponseAdapter;
import com.mai.projects.plm.utils.ProductDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl extends AbstractMainController implements ProductController {
	private final ProductRepository productRepository;
	private final StageRepository stageRepository;
	private final DocumentRepository documentRepository;
	private final UserRepository userRepository;

	@Override
	public ResponseEntity<ResponseObject<Object>> addProduct(AddProductRequest addProductRequest) {
		List<Long> userId = addProductRequest
				.getStages()
				.stream()
				.flatMap(stageContext -> stageContext.getDocuments().stream().map(DocumentContext::getEmployeeId))
				.collect(Collectors.toList());
		List<User> users = userRepository.findAllById(userId);
		if (userId.size() != users.size()) {
			throw new ServerException(ErrorEnum.USERS_NOT_FOUND, null);
		}
		Product product = AddProductRequest2ProductAdapter.convert(addProductRequest, users);
		productRepository.save(product);
		stageRepository.saveAll(product.getStages());
		product.getStages().forEach(stage -> {
			documentRepository.saveAll(stage.getDocuments());
		});
		return prepareEmptyResponseEntity();
	}

	@Override
	public ResponseEntity<ResponseObject<ProductsResponse>> fetchProducts() {
		List<ProductResponse> productResponses = productRepository
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
		Product product = productRepository
				.findById(productId)
				.orElseThrow(() -> new ServerException(ErrorEnum.USERS_NOT_FOUND, null));

		return prepareResponseEntity(Product2ProductResponseAdapter.convertDetail(product));
	}
}
