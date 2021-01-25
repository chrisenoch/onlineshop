package com.chrisenoch.onlineshop.service;

import java.util.List;

import com.chrisenoch.onlineshop.entity.OrderContents;
import com.chrisenoch.onlineshop.entity.StockReservedByUser;
import com.chrisenoch.onlineshop.entity.User;

public interface StockReservedByUserService {
	
	void save(StockReservedByUser stockReservedByUser);

	void delete(StockReservedByUser stockReservedByUser);

	void shiftStockFromProductToStockReservedByUser(int productId, int quantityToReduce,
			StockReservedByUser stockReservedByUser) throws Exception;

	void shiftStockFromStockReservedByUserToProduct(StockReservedByUser stockReservedByUser) throws Exception;

	List<StockReservedByUser>  getStockReservedByUser(User theUser);
	
	void deleteStockReservedByUser(User theUser);

	void shiftStockFromProductToStockReservedByUserByOrderContents(User theUser, List<OrderContents> orderContents)
			throws Exception;

}
