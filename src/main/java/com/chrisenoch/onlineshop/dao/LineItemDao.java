package com.chrisenoch.onlineshop.dao;

import java.util.List;

import com.chrisenoch.onlineshop.entity.Order;
import com.chrisenoch.onlineshop.entity.LineItem;
import com.chrisenoch.onlineshop.entity.Product;

 public interface LineItemDao {
	
	 void save(LineItem theLineItem);
	 void delete(LineItem theLineItem);
	 void delete(Product theProduct);
	 public List<LineItem>  getLineItemsByProductAndOrder(Product theProduct, Order theOrder); 
	 List<LineItem> getLineItems(Order theOrder);
	 LineItem getLineItem(Product theProduct);
	 LineItem getLineItem(int id);

	

}
