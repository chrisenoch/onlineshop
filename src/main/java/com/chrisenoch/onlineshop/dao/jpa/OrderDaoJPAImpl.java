package com.chrisenoch.onlineshop.dao.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.chrisenoch.onlineshop.dao.OrderDao;
import com.chrisenoch.onlineshop.entity.Order;
import com.chrisenoch.onlineshop.entity.LineItem;
import com.chrisenoch.onlineshop.entity.User;

@Repository
@Profile("jpa")
public class OrderDaoJPAImpl implements OrderDao {
	
	EntityManager eF ;

	public OrderDaoJPAImpl(EntityManager eF) {
		this.eF = eF;
	}
	
	@Override
	public void save(Order theOrder) {
				Order order = eF.merge(theOrder);
		
	}
	
	@Override
	public void delete(Order theOrder) {

				eF.remove(theOrder);
		
	}
	
	@Override
	public List<Order> getAllOrders(int userId) throws NoResultException {		
			String sqlQuery = "from Order where user.iD=:userId"; 
			
			TypedQuery<Order> theQuery = 
					eF.createQuery(sqlQuery,Order.class);
			List<Order> theOrders = theQuery.getResultList();
			return theOrders;	
	}
	
	@Override
	public List<Order> getUnprocessedOrders(int userId) {

			String sqlQuery = "from Order where user.iD=:userId  and isProcessed=:false"; 
		
			TypedQuery<Order> theQuery = 
					eF.createQuery(sqlQuery, Order.class);
			
			theQuery.setParameter("userId", userId);
			theQuery.setParameter("false", false); //Debugging. Check don't need to add false as a string.
			
			List<Order> theOrders = theQuery.getResultList();
	
			return theOrders;
	}
	
	
	@Override
	public Order getOrder(int id) {
				Order order = eF.find(Order.class, id);
				return order;
		
	}
	
	

}
