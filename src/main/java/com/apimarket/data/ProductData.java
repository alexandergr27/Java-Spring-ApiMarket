package com.apimarket.data;

import java.util.List;

import com.apimarket.model.Product;

public interface ProductData {
	
	int save(Product product);
	Product get(int code);
	List<Product> list();
	void update(int code, Product product);
	void delete(int code);
}
