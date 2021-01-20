package com.chrisenoch.onlineshop.service;

import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrisenoch.onlineshop.dao.ProductDao;
import com.chrisenoch.onlineshop.entity.OrderContents;
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
	@Transactional
	public Map<Product, Integer> getProductsInBasketByMostRecent(List<OrderContents> list){
		return productDao.getProductsInBasketByMostRecent(list);
	}

	@Override
	@Transactional
	public int reduceStock(int productId, int quantityToReduce) throws Exception {
		return productDao.reduceStock(productId, quantityToReduce);
	}

	@Override
	@Transactional
	public int addStock(int productId, int quantityToAdd) throws Exception {
		return productDao.addStock(productId, quantityToAdd);
		
	}

}
