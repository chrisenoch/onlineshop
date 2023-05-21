package com.chrisenoch.onlineshop.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrisenoch.onlineshop.dao.ProductDao;
import com.chrisenoch.onlineshop.dao.StockReservedByUserDao;
import com.chrisenoch.onlineshop.entity.Order;
import com.chrisenoch.onlineshop.entity.LineItem;
import com.chrisenoch.onlineshop.entity.Product;
import com.chrisenoch.onlineshop.entity.StockReservedByUser;
import com.chrisenoch.onlineshop.entity.User;

@Service
public class StockReservedByUserServiceImpl implements StockReservedByUserService{
	
	private StockReservedByUserDao stockReservedByUserDao;
	private ProductService productService;
	private LineItemService lineItemService;

	@Autowired
	public StockReservedByUserServiceImpl(StockReservedByUserDao stockReservedByUserDao,
			ProductService productService,  LineItemService lineItemService) {
		this.stockReservedByUserDao = stockReservedByUserDao;
		this.productService = productService;
		this.lineItemService = lineItemService;
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
	public void shiftStockFromProductToStockReservedByUserForEachLineItem(User theUser,
			List<LineItem> lineItems) throws Exception {
		for (LineItem lineItem : lineItems) {
			int stockQuantityToDelete = lineItem.getQuantity();
			Product product = lineItem.getProduct();
			
			StockReservedByUser stockReservedByUser = new StockReservedByUser(product, 
					stockQuantityToDelete, theUser);
			
			shiftStockFromProductToStockReservedByUser(product.getId(),
					stockQuantityToDelete, stockReservedByUser);		
			
		}
	}
	
	@Override
	@Transactional
	public void shiftStockFromStockReservedByUserToProduct(StockReservedByUser stockReservedByUser)
			throws Exception {
		int quantity = stockReservedByUser.getQuantity();	

		Product product = stockReservedByUser.getProduct();

		stockReservedByUserDao.delete(stockReservedByUser);

		productService.addStock(product.getId(), quantity);
		
		
	}
	
	@Override
	@Transactional
	public void assignNewLineItemsAccordingToStock(Order theOrder,
			Map<Product, Map<Integer, Integer>> updatedLineItemsAccordingToStock) {
		theOrder.getLineItems().clear(); //remove associations so line items won't be resaved by cascade
		
		//only delete line items if they have stock problems			
		for (Entry<Product, Map<Integer, Integer>>  map : updatedLineItemsAccordingToStock.entrySet()) {
			lineItemService.delete(map.getKey());
		}	

		//loop over map and add new line items to database		
		for ( Entry<Product, Map<Integer, Integer>> map : updatedLineItemsAccordingToStock.entrySet()) {
			Product product = map.getKey();
			
			for ( Entry<Integer, Integer> map2 : map.getValue().entrySet()) {
				//If available stock is zero, do not save new line item in database
				if (map2.getValue() < 1) {
					continue;
				}
				
				LineItem lineItem = new LineItem(theOrder, product, map2.getValue());
				lineItemService.save(lineItem);
			}				
		}
		
		//Reassign line items to Order object
		List<LineItem> updatedLineItems = lineItemService.getLineItems(theOrder);
		theOrder.setLineItems(updatedLineItems);
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
