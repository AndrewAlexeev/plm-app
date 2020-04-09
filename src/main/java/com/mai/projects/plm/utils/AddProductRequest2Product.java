package com.mai.projects.plm.utils;

import com.mai.projects.plm.entities.Product;
import com.mai.projects.plm.model.request.AddProductRequest;

public class AddProductRequest2Product {
	public static Product convert(AddProductRequest addProductRequest) {
		Product product = new Product();
		product.setTitle(addProductRequest.getProductName());
		product.setSerialNumber(addProductRequest.getProductNumber());
		product.setStages(StageContext2Stage.convert(addProductRequest.getStages()));
		product.getStages().forEach(stage -> stage.setProduct(product));
		return product;
	}
}
