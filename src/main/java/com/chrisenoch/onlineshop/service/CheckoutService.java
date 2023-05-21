package com.chrisenoch.onlineshop.service;

import com.chrisenoch.onlineshop.entity.Checkout;

public interface CheckoutService {
	
	public void save(Checkout theCheckout);
	public void delete(Checkout theCheckout);
}
