package com.mai.projects.plm.service;

import com.mai.projects.plm.entities.Product;
import com.mai.projects.plm.enums.ErrorEnum;
import com.mai.projects.plm.exception.ServerException;
import com.mai.projects.plm.repository.DocumentRepository;
import com.mai.projects.plm.repository.ProductRepository;
import com.mai.projects.plm.repository.StageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;
	private final StageRepository stageRepository;
	private final DocumentRepository documentRepository;

	@Override
	public void addProduct(Product product) {
		productRepository.save(product);
		stageRepository.saveAll(product.getStages());
		product.getStages().forEach(stage -> {
			documentRepository.saveAll(stage.getDocuments());
		});
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product findById(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new ServerException(ErrorEnum.PRODUCT_NOT_FOUND));
	}
}
