package com.chrisenoch.onlineshop.service;

import java.util.List;

import com.chrisenoch.onlineshop.entity.Order;
import com.chrisenoch.onlineshop.entity.OrderContents;
import com.chrisenoch.onlineshop.entity.Product;

public interface OrderContentsService {
	
	public void save(OrderContents theOrderContents);
	public void delete(OrderContents theOrderContents);
	public void delete(Product theProduct);
	public boolean checkIfInBasket(Product theProduct, Order theOrder);
	public List<OrderContents> getOrderContents(Order theOrder);
	public OrderContents getOrderContent(Product theProduct);
	public OrderContents getOrderContent(int id);
	public int totalOrderContentsPrice(Order theOrder);
	

}
