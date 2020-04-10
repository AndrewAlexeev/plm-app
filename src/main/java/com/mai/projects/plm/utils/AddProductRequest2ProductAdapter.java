package com.mai.projects.plm.utils;

import com.mai.projects.plm.entities.Product;
import com.mai.projects.plm.entities.User;
import com.mai.projects.plm.model.request.AddProductRequest;

import java.util.List;

public class AddProductRequest2ProductAdapter {
	public static Product convert(AddProductRequest addProductRequest, List<User> users) {
		Product product = new Product();
		product.setTitle(addProductRequest.getProductName());
		product.setSerialNumber(addProductRequest.getProductNumber());
		product.setStages(StageContext2StageAdapter.convert(addProductRequest.getStages(),users));
		product.getStages().forEach(stage -> stage.setProduct(product));
		return product;
	}
}
