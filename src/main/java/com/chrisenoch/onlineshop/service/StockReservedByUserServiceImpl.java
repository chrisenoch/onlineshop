package com.chrisenoch.onlineshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrisenoch.onlineshop.dao.ProductDao;
import com.chrisenoch.onlineshop.dao.StockReservedByUserDao;
import com.chrisenoch.onlineshop.entity.Product;
import com.chrisenoch.onlineshop.entity.StockReservedByUser;
import com.chrisenoch.onlineshop.entity.User;

@Service
public class StockReservedByUserServiceImpl implements StockReservedByUserService{
	
	private StockReservedByUserDao stockReservedByUserDao;
	private ProductService productService;

	@Autowired
	public StockReservedByUserServiceImpl(StockReservedByUserDao stockReservedByUserDao,
			ProductService productService) {
		this.stockReservedByUserDao = stockReservedByUserDao;
		this.productService = productService;
	}

	@Override
	@Transactional
	public void save(StockReservedByUser stockReservedByUser) {
		stockReservedByUserDao.save(stockReservedByUser);
		
	}

	@Override
	@Transactional
	public void delete(StockReservedByUser stockReservedByUser) {
		stockReservedByUserDao.delete(stockReservedByUser);
		
	}
	
	@Override
	@Transactional
	public void shiftStockFromProductToStockReservedByUser(int productId, int quantityToReduce,
			StockReservedByUser stockReservedByUser) throws Exception {

		stockReservedByUserDao.save(stockReservedByUser);
		
		productService.reduceStock(productId, quantityToReduce);
		
	}
	
	@Override
	@Transactional
	public void shiftStockFromStockReservedByUserToProduct(StockReservedByUser stockReservedByUser)
			throws Exception {
		//get quantity	
		int quantity = stockReservedByUser.getQuantity();	
		
		//get product
		Product product = stockReservedByUser.getProduct();
		
		//delete stockReservedByUser
		stockReservedByUserDao.delete(stockReservedByUser);

		//add stock quantity to Product
		productService.addStock(product.getId(), quantity);
		
		
	}
	
	@Override
	@Transactional
	public List<StockReservedByUser> getStockReservedByUser(User theUser){
		
		return stockReservedByUserDao.getStockReservedByUser(theUser);
		
		
	}

	@Override
	@Transactional
	public void deleteStockReservedByUser(User theUser) {
		stockReservedByUserDao.deleteStockReservedByUser(theUser);
		
	}
	
	
	
	

	
	

	
	
	

}
