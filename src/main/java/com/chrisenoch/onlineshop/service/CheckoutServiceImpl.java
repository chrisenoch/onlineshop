package com.chrisenoch.onlineshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrisenoch.onlineshop.dao.CheckoutDao;
import com.chrisenoch.onlineshop.entity.Checkout;

@Service
public class CheckoutServiceImpl implements CheckoutService {
	
	private CheckoutDao checkoutDao;
	
	@Autowired	
	public CheckoutServiceImpl(CheckoutDao checkoutDao) {
		this.checkoutDao = checkoutDao;
	}

	@Override
	@Transactional
	public void save(Checkout theCheckout) {
		checkoutDao.save(theCheckout);

	}
	
	@Override
	@Transactional
	public void delete(Checkout theCheckout) {
		checkoutDao.delete(theCheckout);

	}


}
