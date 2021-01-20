package com.chrisenoch.onlineshop.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import com.chrisenoch.onlineshop.entity.OrderContents;
import com.chrisenoch.onlineshop.entity.Product;

 public interface ProductDao {
	
	 void save(Product theProduct);
	 void delete(Product theProduct);
	 List<Product> getAllProducts();
	 Product getProduct(int productId) throws NoResultException;
	 Map<Product, Integer> getProductsInBasketByMostRecent(List<OrderContents> list);
	 int reduceStock(int productId, int quantityToReduce) throws Exception;
	 int addStock(int productId, int quantityToAdd) throws Exception;

}
