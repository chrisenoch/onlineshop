package com.chrisenoch.onlineshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrisenoch.onlineshop.dao.OrderContentsDao;
import com.chrisenoch.onlineshop.entity.Order;
import com.chrisenoch.onlineshop.entity.OrderContents;
import com.chrisenoch.onlineshop.entity.Product;

@Service
public class OrderContentsServiceImpl implements OrderContentsService {
	
	private OrderContentsDao orderContentsDao;
	
	@Autowired	
	public OrderContentsServiceImpl(OrderContentsDao orderContentsDao) {
		this.orderContentsDao = orderContentsDao;
	}

	@Override
	@Transactional
	public void save(OrderContents theOrderContents) {
		orderContentsDao.save(theOrderContents);

	}
	
	@Override
	@Transactional
	public void delete(OrderContents theOrderContents) {
		orderContentsDao.delete(theOrderContents);

	}
	
	@Override
	@Transactional
	public boolean checkIfInBasket(Product theProduct, Order theOrder) {
		List<OrderContents> orderContents = orderContentsDao.getOrderContentsByProductAndOrder(theProduct, theOrder);
		return orderContents.size() > 0? true : false;

	}
	
	@Override
	@Transactional
	public List<OrderContents> getOrderContents(Order theOrder){
		return orderContentsDao.getOrderContents(theOrder);
	}
	
	@Override
	@Transactional
	public int totalOrderContentsPrice(Order theOrder) {
		List<OrderContents> list = orderContentsDao.getOrderContents(theOrder);
		
		int sum = list.stream().mapToInt(x -> x.getProduct().getPrice() * x.getQuantity()).sum();
		
		return sum; //return price in cents 
		
		
	}
	
	
	@Override
	@Transactional
	public void delete(Product theProduct) {
		orderContentsDao.delete(theProduct);
	}

	@Override
	@Transactional
	public OrderContents getOrderContent(Product theProduct) {

		return orderContentsDao.getOrderContent(theProduct);
	}
	
	@Override
	@Transactional
	public OrderContents getOrderContent(int id) {
		return orderContentsDao.getOrderContent(id);
	}
	
	


}
