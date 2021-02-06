package com.apimarket.service;

import java.util.List;

import com.apimarket.model.Product;

public interface ProductService {
	
	int save(Product product);
	Product get(int code);
	List<Product> list();
	void update(int code, Product product);
	void delete(int code);
}
