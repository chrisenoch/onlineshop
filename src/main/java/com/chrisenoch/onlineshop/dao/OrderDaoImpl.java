package com.chrisenoch.onlineshop.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.chrisenoch.onlineshop.entity.Order;
import com.chrisenoch.onlineshop.entity.LineItem;
import com.chrisenoch.onlineshop.entity.User;

@Repository
@Profile("hibernate")
public class OrderDaoImpl implements OrderDao {
	
	private SessionFactory sessionFactory;

	@Autowired
	public OrderDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(Order theOrder) {
				Session currentSession = sessionFactory.getCurrentSession();

				currentSession.saveOrUpdate(theOrder);
		
	}
	
	@Override
	public void delete(Order theOrder) {
				Session currentSession = sessionFactory.getCurrentSession();

				currentSession.delete(theOrder);
		
	}
	
	@Override
	public List<Order> getAllOrders(int userId) throws NoResultException {		
		Session currentSession = sessionFactory.getCurrentSession();
			
			String sqlQuery = "from Order where user.iD=:userId"; 
			
			Query<Order> theQuery = 
					currentSession.createQuery(sqlQuery,
												Order.class);
			
			theQuery.setParameter("userId", userId);
			List<Order> theOrders = theQuery.getResultList();
	
			return theOrders;

			
	}

	@Override
	public List<Order> getUnprocessedOrders(int userId) {
		
		Session currentSession = sessionFactory.getCurrentSession();

			String sqlQuery = "from Order where user.iD=:userId  and isProcessed=:false"; 
		
			Query<Order> theQuery = 
					currentSession.createQuery(sqlQuery,
												Order.class);
			
			theQuery.setParameter("userId", userId);
			theQuery.setParameter("false", false); 
			
			List<Order> theOrders = theQuery.getResultList();
	
			return theOrders;
	}
	 
	
	@Override
	public Order getOrder(int orderId) {
		Session currentSession = sessionFactory.getCurrentSession();

					
			return currentSession.get(Order.class, orderId);
		
	}

}
