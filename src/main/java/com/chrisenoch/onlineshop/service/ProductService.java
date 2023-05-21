package com.chrisenoch.onlineshop.service;

import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import com.chrisenoch.onlineshop.entity.LineItem;
import com.chrisenoch.onlineshop.entity.Product;

public interface ProductService {
	
	public void save(Product theProduct);
	public void delete(Product theProduct);
	public List<Product> getAllProducts();
	public Product getProduct(int productId) throws NoResultException;
	public Map<Product, Integer> getProductsInBasketByMostRecent(List<LineItem> list);
	public int reduceStock(int productId, int quantityToReduce) throws Exception;
	public int addStock(int productId, int quantityToAdd) throws Exception;

}
