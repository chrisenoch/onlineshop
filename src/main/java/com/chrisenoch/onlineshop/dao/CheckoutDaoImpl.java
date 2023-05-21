package com.chrisenoch.onlineshop.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.chrisenoch.onlineshop.entity.Checkout;

@Repository
@Profile("hibernate")
public class CheckoutDaoImpl implements CheckoutDao {
	
	private SessionFactory sessionFactory;

	@Autowired
	public CheckoutDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(Checkout theCheckout) {
				Session currentSession = sessionFactory.getCurrentSession();
				
				//Set isProcesseed to true in Order table so if user continues shopping, the items will be associated with a new order.
				int orderId = theCheckout.getOrder().getId();			
				String sqlQuery = "update Order set isProcessed=true where id=:orderId"; 
				
				Query theQuery = 
						currentSession.createQuery(sqlQuery);
				
				theQuery.setParameter("orderId", orderId);
				theQuery.executeUpdate();

				//Save checkout information
				currentSession.saveOrUpdate(theCheckout);
				
	}
	
	@Override
	public void delete(Checkout theCheckout) {
				Session currentSession = sessionFactory.getCurrentSession();

				currentSession.delete(theCheckout);
		
	}
	
}
