package com.chrisenoch.onlineshop.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrisenoch.onlineshop.dao.ProductDao;
import com.chrisenoch.onlineshop.entity.LineItem;
import com.chrisenoch.onlineshop.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	private ProductDao productDao;
	
	@Autowired	
	public ProductServiceImpl(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	@Transactional
	public void save(Product theProduct) {
		productDao.save(theProduct);

	}
	
	@Override
	@Transactional
	public void delete(Product theProduct) {
		productDao.delete(theProduct);

	}
	
	@Override
	@Transactional
	public List<Product> getAllProducts(){
		return productDao.getAllProducts();
	}
	
	@Override
	@Transactional
	public Product getProduct(int productId) throws NoResultException{
		return productDao.getProduct(productId);
	}
	
	@Override
	public Map<Product, Integer> getProductsInBasketByMostRecent(List<LineItem> list){
		Map<Product, Integer> map = new LinkedHashMap<>();
		
		int size = list.size();
		for (int i = 1; i <= list.size(); i++) {
			Product product = list.get(size - i).getProduct();
			Integer quantity = list.get(size - i).getQuantity();
			map.put(product, quantity);
		}
		
		return map;
	}

	@Override
	@Transactional
	public int reduceStock(int productId, int quantityToReduce) throws Exception {
		if (quantityToReduce < 1) {
			throw new Exception("Invalid quantity entered. You cannot reduce the stock by a number less than one.");
		} //Do a test for this
		
		Product product = productDao.getProduct(productId);
		
		int currentStockQuantity = product.getStock();	
		
		int newStockQuantity = currentStockQuantity - quantityToReduce;
		
		if (newStockQuantity < 0) {
			throw new Exception ("You cannot reduce the stock level by an amount higher than "
					+ "the current stock level. " + product.getName() + " with product id: " + 
					product.getId() + ", currently has: " + currentStockQuantity + " items in stock");
		}
		
		product.setStock(newStockQuantity);
		
		return newStockQuantity;
		
	}

	@Override
	@Transactional
	public int addStock(int productId, int quantityToAdd) throws Exception {
		Product product = productDao.getProduct(productId);
		
		int newStock = product.getStock() + quantityToAdd;
		product.setStock(newStock);
		
		return newStock;
		
	}

}
