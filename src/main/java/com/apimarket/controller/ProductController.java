package com.apimarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apimarket.model.Product;
import com.apimarket.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> list = productService.list();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping("/product")
	public ResponseEntity<?> save(@RequestBody Product product) {
		int code = productService.save(product);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@GetMapping("/product/{code}")
	public ResponseEntity<Product> get(@PathVariable("code")  int code) {
		Product product = productService.get(code);
		return ResponseEntity.ok().body(product);
	}
	
	@PutMapping("/product/{code}")
	public ResponseEntity<?> update(@PathVariable("code") int code, @RequestBody Product product) {
		productService.update(code, product);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@DeleteMapping("/product/{code}")
	public ResponseEntity<?> delete(@PathVariable("code") int code) {
		productService.delete(code);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}

}
