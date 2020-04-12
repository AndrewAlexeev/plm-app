package com.mai.projects.plm.utils;

import com.mai.projects.plm.entities.Product;
import com.mai.projects.plm.model.response.ProductResponse;
import com.mai.projects.plm.model.response.StageResponse;

import java.util.List;
import java.util.stream.Collectors;

public class Product2ProductResponseAdapter {
	public static ProductResponse convert(Product product) {
		ProductResponse productResponse = new ProductResponse();
		productResponse.setId(product.getId());
		productResponse.setSerialNumber(product.getSerialNumber());
		productResponse.setTitle(product.getTitle());
		return productResponse;
	}

	public static ProductDetailResponse convertDetail(Product product) {
		ProductDetailResponse productDetailResponse = new ProductDetailResponse();
		productDetailResponse.setId(product.getId());
		productDetailResponse.setSerialNumber(product.getSerialNumber());
		productDetailResponse.setTitle(product.getTitle());

		List<StageResponse> stageResponseList = product
				.getStages()
				.stream()
				.map(Stage2StageResponseAdapter::convert)
				.collect(Collectors.toList());

		productDetailResponse.setStages(stageResponseList);
		return productDetailResponse;
	}
}
