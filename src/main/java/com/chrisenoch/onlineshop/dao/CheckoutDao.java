package com.chrisenoch.onlineshop.dao;

import com.chrisenoch.onlineshop.entity.Checkout;

public interface CheckoutDao {
	
	void save(Checkout theCheckout);
	void delete(Checkout theCheckout);

}
