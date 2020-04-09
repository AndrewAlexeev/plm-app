package com.mai.projects.plm.controllers;

import com.mai.projects.plm.entities.Product;
import com.mai.projects.plm.model.request.AddProductRequest;
import com.mai.projects.plm.repository.ProductRepository;
import com.mai.projects.plm.repository.StageRepository;
import com.mai.projects.plm.utils.AddProductRequest2Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl extends AbstractMainController implements ProductController {
	private final ProductRepository productRepository;
	private final StageRepository stageRepository;

	@Override
	public ResponseEntity addProduct(AddProductRequest addProductRequest) {
		Product product = AddProductRequest2Product.convert(addProductRequest);
		productRepository.save(product);
		//stageRepository.saveAll(product.getStages());
		return prepareEmptyResponseEntity();
	}
}
