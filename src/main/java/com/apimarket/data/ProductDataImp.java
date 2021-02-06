package com.apimarket.data;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.apimarket.model.Product;

@Repository
public class ProductDataImp implements ProductData {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int save(Product product) {
		sessionFactory.getCurrentSession().save(product);
		return product.getCode();
	}

	@Override
	public Product get(int code) {
		return sessionFactory.getCurrentSession().get(Product.class, code);
	}

	@Override
	public List<Product> list() {
		List<Product> list = sessionFactory.getCurrentSession().createQuery("from product").list();
		return list;
	}

	@Override
	public void update(int code, Product product) {
		Session session = sessionFactory.getCurrentSession();
		Product updateProduct = session.byId(Product.class).load(code);
		updateProduct.setDescription(product.getDescription());
		updateProduct.setBrand(product.getBrand());
		updateProduct.setPresentation(product.getPresentation());
		updateProduct.setPrice(product.getPrice());
		updateProduct.setLocation(product.getLocation());
		session.flush();
	}

	@Override
	public void delete(int code) {
		Product product = sessionFactory.getCurrentSession().byId(Product.class).load(code);
		sessionFactory.getCurrentSession().delete(product);
		
	}

}
