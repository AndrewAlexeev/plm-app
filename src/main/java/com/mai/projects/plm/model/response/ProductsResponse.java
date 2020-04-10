package com.mai.projects.plm.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductsResponse {
	private List<ProductResponse> products;
}
