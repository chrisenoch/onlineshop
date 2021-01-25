package com.chrisenoch.onlineshop.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.chrisenoch.onlineshop.entity.Order;
import com.chrisenoch.onlineshop.entity.OrderContents;
import com.chrisenoch.onlineshop.entity.Product;


@Repository
@Profile("hibernate")
public class OrderContentsDaoImpl implements OrderContentsDao {
	
	private SessionFactory sessionFactory;

	@Autowired
	public OrderContentsDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(OrderContents theOrderContents) {
				Session currentSession = sessionFactory.getCurrentSession();

				currentSession.saveOrUpdate(theOrderContents);
		
	}
	
	@Override
	public void delete(OrderContents theOrderContents) {
				Session currentSession = sessionFactory.getCurrentSession();

				currentSession.delete(theOrderContents);
		
	}
	
	@Override
	public List<OrderContents>  getOrderContentsByproductAndOrder(Product theProduct, Order theOrder) {
		
		int productId = theProduct.getId();
		int orderId = theOrder.getId();
		
		Session currentSession = sessionFactory.getCurrentSession();

			String sqlQuery = "from OrderContents where product.id=:productId and order.id=:orderId"; 
			
			Query<OrderContents> theQuery = 
					currentSession.createQuery(sqlQuery,
												OrderContents.class);
			
			theQuery.setParameter("productId", productId);
			theQuery.setParameter("orderId", orderId);
			
			List<OrderContents> theOrderContents = theQuery.getResultList();
		
			return theOrderContents;
			
			//return theOrderContents.size() > 0? true : false;
			 
	}
	
	@Override
	public List<OrderContents> getOrderContents(Order theOrder) {
		
		System.out.println("Inside getOrderContents hibernate");
		int orderId = theOrder.getId();
		
		Session currentSession = sessionFactory.getCurrentSession();

			String sqlQuery = "from OrderContents where order.id=:orderId order by id"; 
			
			Query<OrderContents> theQuery = 
					currentSession.createQuery(sqlQuery,
												OrderContents.class);
			
			theQuery.setParameter("orderId", orderId);
			List<OrderContents> list = theQuery.getResultList();
	
			return list;			
	}
	
	@Override
	public OrderContents getOrderContent(Product theProduct) {
		int productId = theProduct.getId();
		
		Session currentSession = sessionFactory.getCurrentSession();

			String sqlQuery = "from OrderContents where product.id=:productId"; 
			
			Query<OrderContents> theQuery = 
					currentSession.createQuery(sqlQuery,
												OrderContents.class);
			
			theQuery.setParameter("productId", productId);
			OrderContents orderContents = theQuery.getSingleResult();
	
			return orderContents;			
	}
	
	
	
	
	@Override
	public void delete(Product theProduct) {
		int productId = theProduct.getId();
		
		Session currentSession = sessionFactory.getCurrentSession();
		System.out.println("productId here: " + productId);

			Query theQuery = 
					currentSession.createQuery("delete from OrderContents where product.id = :productId");
			
			theQuery.setParameter("productId", productId);
			
			theQuery.executeUpdate(); 		
	}

	@Override
	public OrderContents getOrderContent(int id) {
		
		Session currentSession = sessionFactory.getCurrentSession();
	
			return currentSession.get(OrderContents.class, id);
		
	}
	


}
