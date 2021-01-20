package com.chrisenoch.onlineshop.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrisenoch.onlineshop.dao.OrderDao;
import com.chrisenoch.onlineshop.entity.Order;
import com.chrisenoch.onlineshop.entity.User;

@Service
public class OrderServiceImpl implements OrderService {
	
	private OrderDao orderDao;
	
	@Autowired	
	public OrderServiceImpl(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	@Transactional
	public void save(Order theOrder) {
		orderDao.save(theOrder);

	}
	
	@Override
	@Transactional
	public void delete(Order theOrder) {
		orderDao.delete(theOrder);

	}
		
	@Override
	@Transactional
	public List<Order> getAllOrders(int userId){
		return orderDao.getAllOrders(userId);
	}
	
	@Override
	@Transactional
	public List<Order> getUnprocessedOrders(int userId) {
		return orderDao.getUnprocessedOrders(userId);
	}
	
	@Override
	@Transactional
	public Order getCorrectOrderSession(HttpSession session, User theUser, int userId) throws Exception{
		return orderDao.getCorrectOrderSession(session, theUser, userId); 
	}
	
	@Override
	@Transactional
	public Order getCorrectOrder(User theUser, int userId) throws Exception{
		return orderDao.getCorrectOrder(theUser, userId); 
	}
	
	@Override
	@Transactional
	public int totalOrderContentsPrice(int orderId) {
		return orderDao.totalOrderContentsPrice(orderId);
	}
	
	@Override
	@Transactional
	public Order getOrder(int orderId) {
		return orderDao.getOrder(orderId);
	}

}
