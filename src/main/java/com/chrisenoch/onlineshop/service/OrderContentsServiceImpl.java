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
		return orderContentsDao.checkIfInBasket(theProduct, theOrder);

	}
	
	@Override
	@Transactional
	public List<OrderContents> getOrderContents(Order theOrder){
		return orderContentsDao.getOrderContents(theOrder);
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
