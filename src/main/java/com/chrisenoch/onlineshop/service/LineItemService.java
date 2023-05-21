package com.chrisenoch.onlineshop.service;

import java.util.List;

import com.chrisenoch.onlineshop.entity.Order;
import com.chrisenoch.onlineshop.entity.LineItem;
import com.chrisenoch.onlineshop.entity.Product;

public interface LineItemService {
	
	public void save(LineItem theLineItem);
	public void delete(LineItem theLineItem);
	
	/**
	 * Delete the LineItem for the product specified.
	 * @param theProduct
	 */
	public void delete(Product theProduct);
	
	
	public boolean checkIfInBasket(Product theProduct, Order theOrder);
	public List<LineItem> getLineItems(Order theOrder);
	public LineItem getLineItem(Product theProduct);
	
	public LineItem getLineItem(int lineItemId);
	
	public int totalLineItemsPrice(Order theOrder);
	

}
