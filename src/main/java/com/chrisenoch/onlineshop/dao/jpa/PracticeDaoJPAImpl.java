package com.chrisenoch.onlineshop.dao.jpa;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chrisenoch.onlineshop.entity.Checkout;
import com.chrisenoch.onlineshop.entity.DeliveryInformation;
import com.chrisenoch.onlineshop.entity.Order;

@Repository
public class PracticeDaoJPAImpl {
	EntityManager eF ;

	public PracticeDaoJPAImpl(EntityManager eF) {
		this.eF = eF;
	}
	
	@Transactional
	public void save() {
				Checkout theCheckout = new Checkout();
				eF.merge(theCheckout);
		
	}
	
	@Transactional
	public void saveCheckoutAndOrder() {
		Order order = new Order();
		Checkout theCheckout = new Checkout();
		theCheckout.setOrder(order);
		
		Checkout checkout = eF.merge(theCheckout);

}
	
	@Transactional
	public void saveallcheckout() {
		Order order = new Order();
		int price = 1200;
		double shippingCost = 12.0;
		DeliveryInformation dI = new DeliveryInformation();
		String stripeChargeId = "12345";
		
		Checkout theCheckout = new Checkout();
		theCheckout.setOrder(order);
		theCheckout.setDeliveryInformation(dI);
		theCheckout.setShippingCost(shippingCost);
		theCheckout.setStripeChargeId(stripeChargeId);
		theCheckout.setTotalprice(price);
		
		Checkout checkout = eF.merge(theCheckout);

}
	
	public void delete(Checkout theCheckout) {

				eF.remove(theCheckout);
		
	}
	

}
