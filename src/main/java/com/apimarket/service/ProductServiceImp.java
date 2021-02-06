package com.apimarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apimarket.data.ProductData;
import com.apimarket.model.Product;

@Service
@Transactional(readOnly = true)
public class ProductServiceImp implements ProductService{
	
	@Autowired
	private ProductData productData;

	@Override
	@Transactional
	public int save(Product product) {
		return productData.save(product);
	}

	@Override
	@Transactional
	public Product get(int code) {
		return productData.get(code);
	}

	@Override
	@Transactional
	public List<Product> list() {
		return productData.list();
	}

	@Override
	@Transactional
	public void update(int code, Product product) {
		productData.update(code, product);
		
	}

	@Override
	@Transactional
	public void delete(int code) {
		productData.delete(code);
		
	}

}
