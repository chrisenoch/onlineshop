package com.chrisenoch.onlineshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrisenoch.onlineshop.dao.LineItemDao;
import com.chrisenoch.onlineshop.entity.Order;
import com.chrisenoch.onlineshop.entity.LineItem;
import com.chrisenoch.onlineshop.entity.Product;

@Service
public class LineItemsServiceImpl implements LineItemService {
	
	private LineItemDao lineItemDao;
	
	@Autowired	
	public LineItemsServiceImpl(LineItemDao lineItemDao) {
		this.lineItemDao = lineItemDao;
	}

	@Override
	@Transactional
	public void save(LineItem theLineItem) {
		lineItemDao.save(theLineItem);

	}
	
	@Override
	@Transactional
	public void delete(LineItem theLineItem) {
		lineItemDao.delete(theLineItem);

	}
	
	@Override
	@Transactional
	public boolean checkIfInBasket(Product theProduct, Order theOrder) {
		List<LineItem> lineItems = lineItemDao.getLineItemsByProductAndOrder(theProduct, theOrder);
		return lineItems.size() > 0? true : false;

	}
	
	@Override
	@Transactional
	public List<LineItem> getLineItems(Order theOrder){
		return lineItemDao.getLineItems(theOrder);
	}
	
	@Override
	@Transactional
	public int totalLineItemsPrice(Order theOrder) {
		List<LineItem> list = lineItemDao.getLineItems(theOrder);
		
		int sum = list.stream().mapToInt(x -> x.getProduct().getPrice() * x.getQuantity()).sum();
		
		return sum; //return price in cents 
		
		
	}
	
	
	@Override
	@Transactional
	public void delete(Product theProduct) {
		lineItemDao.delete(theProduct);
	}

	@Override
	@Transactional
	public LineItem getLineItem(Product theProduct) {

		return lineItemDao.getLineItem(theProduct);
	}
	
	@Override
	@Transactional
	public LineItem getLineItem(int id) {
		return lineItemDao.getLineItem(id);
	}
	
	


}
