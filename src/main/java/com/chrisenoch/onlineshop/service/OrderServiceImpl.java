package com.chrisenoch.onlineshop.service;

import java.util.Date;
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
	public Order getCorrectOrder(User theUser, int userId) throws Exception{	
		List<Order> fetchedOrders = orderDao.getUnprocessedOrders(userId);
		
		Order theOrder;
		if (fetchedOrders.size() == 0) {
			theOrder = new Order();
			
			theOrder.setUser(theUser);
			theOrder.setOrderDate(new Date());
			theOrder.setProcessed(false);
			
			save(theOrder);
			
			
		} else if (fetchedOrders.size() == 1) {
			//Add fetchedOrder to current session
			theOrder = fetchedOrders.get(0);
			
		} else {
			//An unprocessed order represents the user's basket contents. Each time a purchase is made isProcessed is set to true. 
			//So there should only ever be a max of one unprocessed orders in the database for each user at any point in time.
			throw new Exception();

		}

	return theOrder;	
	}
	
	
	@Override
	@Transactional
	public Order getOrder(int orderId) {
		return orderDao.getOrder(orderId);
	}

}
