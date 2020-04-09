package com.mai.projects.plm.controllers;

import com.mai.projects.plm.entities.Product;
import com.mai.projects.plm.entities.User;
import com.mai.projects.plm.model.request.AddProductRequest;
import com.mai.projects.plm.model.request.DocumentContext;
import com.mai.projects.plm.model.response.ResponseObject;
import com.mai.projects.plm.repository.DocumentRepository;
import com.mai.projects.plm.repository.ProductRepository;
import com.mai.projects.plm.repository.StageRepository;
import com.mai.projects.plm.repository.UserRepository;
import com.mai.projects.plm.utils.AddProductRequest2Product;
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

		Product product = AddProductRequest2Product.convert(addProductRequest,users);
		productRepository.save(product);
		stageRepository.saveAll(product.getStages());
		product.getStages().forEach(stage -> {
			documentRepository.saveAll(stage.getDocuments());
		});
		return prepareEmptyResponseEntity();
	}
}
