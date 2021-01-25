package com.chrisenoch.onlineshop.dao;

import java.util.List;

import com.chrisenoch.onlineshop.entity.Order;
import com.chrisenoch.onlineshop.entity.OrderContents;
import com.chrisenoch.onlineshop.entity.Product;

 public interface OrderContentsDao {
	
	 void save(OrderContents theOrderContents);
	 void delete(OrderContents theOrderContents);
	 void delete(Product theProduct);
	 public List<OrderContents>  getOrderContentsByProductAndOrder(Product theProduct, Order theOrder); 
	 List<OrderContents> getOrderContents(Order theOrder);
	 OrderContents getOrderContent(Product theProduct);
	 OrderContents getOrderContent(int id);

	

}
