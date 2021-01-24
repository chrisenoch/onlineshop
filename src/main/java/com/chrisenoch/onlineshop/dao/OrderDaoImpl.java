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
import com.chrisenoch.onlineshop.entity.OrderContents;
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

			//String sqlQuery = "from Order where user.iD=" + userId + " and isProcessed=" + false; 
			String sqlQuery = "from Order where user.iD=:userId  and isProcessed=:false"; 
		
			Query<Order> theQuery = 
					currentSession.createQuery(sqlQuery,
												Order.class);
			
			theQuery.setParameter("userId", userId);
			theQuery.setParameter("false", false); //Debugging. Check don't need to add false as a string.
			
			List<Order> theOrders = theQuery.getResultList();
	
			return theOrders;
	}
	
	
	@Override
	public Order getCorrectOrder(User theUser, int userId) throws Exception {
		//Ensure that order_contents are added to the correct order.
		Order theOrder;
		System.out.println("enter getcorrect order");

			List<Order> fetchedOrders = getUnprocessedOrders(userId); 
			System.out.println("Retrieved order successful: " + fetchedOrders);
			
			if (fetchedOrders.size() == 0) {
				System.out.println("debug, fetchedorders == 0");
				theOrder = new Order();
				
				theOrder.setUser(theUser);
				theOrder.setOrderDate(new Date());
				theOrder.setProcessed(false);
				
				save(theOrder);
				System.out.println("Order saved");
				
				
			} else if (fetchedOrders.size() == 1) {
				System.out.println("debug, fetchedorders == 1");
				//Add fetchedOrder to current session
				theOrder = fetchedOrders.get(0);
				
			} else {
				//An unprocessed order represents the user's basket contents. Each time a purchase is made isProcessed is set to true. 
				//So there should only ever be a max of one unprocessed orders in the database for each user at any point in time.
				throw new Exception();

			}

		
		return theOrder;
	}
	
	@Override
	public int totalOrderContentsPrice(int orderId) {
		Session currentSession = sessionFactory.getCurrentSession();

			String sqlQuery = "from OrderContents where order.id=:orderId"; 
			
			Query<OrderContents> theQuery = 
					currentSession.createQuery(sqlQuery,
												OrderContents.class);
			
			
			theQuery.setParameter("orderId", orderId);
			List<OrderContents> list = theQuery.getResultList();
						
			int sum = list.stream().mapToInt(x -> x.getProduct().getPrice() * x.getQuantity()).sum();

			// return the results		
			return sum; //return price in cents 
		
	}
	
	@Override
	public Order getOrder(int orderId) {
		Session currentSession = sessionFactory.getCurrentSession();

					
			return currentSession.get(Order.class, orderId);
		
	}

}
