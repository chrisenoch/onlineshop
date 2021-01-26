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
import com.chrisenoch.onlineshop.entity.OrderContents;
import com.chrisenoch.onlineshop.entity.Product;
import com.chrisenoch.onlineshop.entity.StockReservedByUser;
import com.chrisenoch.onlineshop.entity.User;

@Service
public class StockReservedByUserServiceImpl implements StockReservedByUserService{
	
	private StockReservedByUserDao stockReservedByUserDao;
	private ProductService productService;
	private OrderContentsService orderContentsService;

	@Autowired
	public StockReservedByUserServiceImpl(StockReservedByUserDao stockReservedByUserDao,
			ProductService productService,  OrderContentsService orderContentsService) {
		this.stockReservedByUserDao = stockReservedByUserDao;
		this.productService = productService;
		this.orderContentsService = orderContentsService;
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
	public void shiftStockFromProductToStockReservedByUserByOrderContents(User theUser,
			List<OrderContents> orderContents) throws Exception {
		for (OrderContents oC : orderContents) {
			int stockQuantityToDelete = oC.getQuantity();
			Product product = oC.getProduct();
			//deleteStockByProductId	
			
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
	public void assignNewOrderContentsAccordingToStock(Order theOrder,
			Map<Product, Map<Integer, Integer>> updatedOrderContentsAccordingToStock) {
		theOrder.getOrderContents().clear(); //remove associations so orderContents won't be resaved by cascade
		
		//only delete orderContents if they have stock problems			
		for (Entry<Product, Map<Integer, Integer>>  map : updatedOrderContentsAccordingToStock.entrySet()) {
			orderContentsService.delete(map.getKey());
		}	

		//loop over map and add new order contents to database		
		for ( Entry<Product, Map<Integer, Integer>> map : updatedOrderContentsAccordingToStock.entrySet()) {
			Product product = (Product) map.getKey();
			
			for ( Entry<Integer, Integer> map2 : map.getValue().entrySet()) {
				//If available stock is zero, do not save new OrderContents in database
				if (map2.getValue() < 1) {
					continue;
				}
				
				OrderContents oC = new OrderContents(theOrder, product, map2.getValue());
				orderContentsService.save(oC);
			}				
		}
		
		//Reassign orderContents to Order object
		List<OrderContents> updatedOrderContents = orderContentsService.getOrderContents(theOrder);
		theOrder.setOrderContents(updatedOrderContents);
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
