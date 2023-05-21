package com.chrisenoch.onlineshop.service;

import java.util.List;
import java.util.Map;

import com.chrisenoch.onlineshop.entity.Order;
import com.chrisenoch.onlineshop.entity.LineItem;
import com.chrisenoch.onlineshop.entity.Product;
import com.chrisenoch.onlineshop.entity.StockReservedByUser;
import com.chrisenoch.onlineshop.entity.User;

public interface StockReservedByUserService {
	
	void save(StockReservedByUser stockReservedByUser);

	void delete(StockReservedByUser stockReservedByUser);
	
	/**
	 * Delete a quantity of stock from one product from the general stock level and add it to the stock reserved by a user.
	 * More specifically, this method deletes from {@link com.chrisenoch.onlineshop.entity.Product} and adds to
	 *  {@link com.chrisenoch.onlineshop.entity.StockReservedByUser}
	 * This can be used when a user clicks on checkout. This stops other users purchasing the same stock in the time period
	 * between the user clicking on the checkout button and the user completing the purchase.
	 * @param productId
	 * @param quantityToReduce
	 * @param stockReservedByUser
	 * @throws Exception
	 */
	void shiftStockFromProductToStockReservedByUser(int productId, int quantityToReduce,
			StockReservedByUser stockReservedByUser) throws Exception;

	/**
	 * Stop reserving the stock held by the user and add it to the general stock level. 
	 * @param stockReservedByUser
	 * @throws Exception
	 */
	void shiftStockFromStockReservedByUserToProduct(StockReservedByUser stockReservedByUser) throws Exception;
	
	List<StockReservedByUser>  getStockReservedByUser(User theUser);
	
	void deleteStockReservedByUser(User theUser);
	
	/**
	 * Applies {@link #shiftStockFromProductToStockReservedByUser(int productId, int quantityToReduce,
			StockReservedByUser stockReservedByUser)} to each LineItem supplied to the method by way of the method parameter lineItems
	 * @param theUser
	 * @throws Exception
	 */
	void shiftStockFromProductToStockReservedByUserForEachLineItem(User theUser, List<LineItem> lineItems)
			throws Exception;
	
	/**
	 * Update the user's line items to reflect the available stock levels. The user will be assigned the maximum possible amount of stock in line
	 * with his/her initial request for every line item. For instance, if the user originally wanted 8 of Product X and only 6 were available, this line item 
	 * would be updated to 6 Product X's instead of 8.
	 * @param theOrder
	 * @param updatedLineItemsAccordingToStock
	 */
	public void assignNewLineItemsAccordingToStock(Order theOrder,
			Map<Product, Map<Integer, Integer>> updatedLineItemsAccordingToStock);

}
