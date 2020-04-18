package com.mai.projects.plm.service;

import com.mai.projects.plm.entities.Product;

import java.util.List;

public interface ProductService {
	void addProduct(Product product);
	List<Product> findAll();
	Product findById(Long id);
}
