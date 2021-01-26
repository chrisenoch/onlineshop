package com.chrisenoch.onlineshop.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.chrisenoch.onlineshop.dao.CheckoutDao;
import com.chrisenoch.onlineshop.dao.OrderDao;
import com.chrisenoch.onlineshop.entity.Checkout;

@Repository
@Profile("jpa")
public class CheckoutDaoJPAImpl implements CheckoutDao {
	
	EntityManager eF ;

	public CheckoutDaoJPAImpl(EntityManager eF) {
		this.eF = eF;
	}

	@Override
	public void save(Checkout theCheckout) {			
				//Set isProcesseed to true in Order table so if user continues shopping, the items will be associated with a new order.
				int orderId = theCheckout.getOrder().getId();
				
				String sqlQuery = "update Order set isProcessed=true where id=:orderId"; 
				
				Query theQuery = 
						eF.createQuery(sqlQuery);
				
				theQuery.setParameter("orderId", orderId);
				theQuery.executeUpdate();

				//Save checkout information
				eF.merge(theCheckout);
				
	}
	
	@Override
	public void delete(Checkout theCheckout) {
				eF.remove(theCheckout);
		
	}
	
}
