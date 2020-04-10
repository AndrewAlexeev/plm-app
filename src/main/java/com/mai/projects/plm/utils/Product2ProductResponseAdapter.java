package com.mai.projects.plm.utils;

import com.mai.projects.plm.entities.Product;
import com.mai.projects.plm.model.response.ProductResponse;

public class Product2ProductResponseAdapter {
	public static ProductResponse convert(Product product) {
		ProductResponse productResponse = new ProductResponse();
		productResponse.setId(product.getId());
		productResponse.setSerialNumber(product.getSerialNumber());
		productResponse.setTitle(product.getTitle());
		return productResponse;
	}
}
