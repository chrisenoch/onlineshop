package com.chrisenoch.onlineshop.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import com.chrisenoch.onlineshop.entity.LineItem;
import com.chrisenoch.onlineshop.entity.Product;

 public interface ProductDao {
	
	 void save(Product theProduct);
	 void delete(Product theProduct);
	 List<Product> getAllProducts();
	 Product getProduct(int productId) throws NoResultException;

}
