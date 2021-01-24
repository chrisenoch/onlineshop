package com.chrisenoch.onlineshop.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chrisenoch.onlineshop.entity.Order;

@Repository
public class OrderDaoJPAImpl {
	
	EntityManager eF ;

	public OrderDaoJPAImpl(EntityManager eF) {
		this.eF = eF;
	}
	
	@Transactional
	public Order save(Order theOrder) {

				Order order = eF.merge(theOrder);
				return order;
		
	}
	
	@Transactional
	public void delete(Order theOrder) {

				eF.remove(theOrder);
		
	}
	
	@Transactional
	public Order getOrder(int id) {
				Order order = eF.find(Order.class, id);
				return order;
		
	}
	
	public List<Order> getAllOrdersJPA() throws NoResultException {		
			String sqlQuery = "from Order"; 
			
			TypedQuery<Order> theQuery = 
					eF.createQuery(sqlQuery,Order.class);
			List<Order> theOrders = theQuery.getResultList();
			return theOrders;	
	}
	
	
	

}
