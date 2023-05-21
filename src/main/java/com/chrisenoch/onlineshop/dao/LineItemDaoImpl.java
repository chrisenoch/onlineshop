package com.chrisenoch.onlineshop.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.chrisenoch.onlineshop.entity.Order;
import com.chrisenoch.onlineshop.entity.LineItem;
import com.chrisenoch.onlineshop.entity.Product;


@Repository
@Profile("hibernate")
public class LineItemDaoImpl implements LineItemDao {
	
	private SessionFactory sessionFactory;

	@Autowired
	public LineItemDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(LineItem theLineItems) {
				Session currentSession = sessionFactory.getCurrentSession();

				currentSession.saveOrUpdate(theLineItems);
		
	}
	
	@Override
	public void delete(LineItem theLineItems) {
				Session currentSession = sessionFactory.getCurrentSession();

				currentSession.delete(theLineItems);
		
	}
	
	@Override
	public List<LineItem>  getLineItemsByProductAndOrder(Product theProduct, Order theOrder) {
		
		int productId = theProduct.getId();
		int orderId = theOrder.getId();
		
		Session currentSession = sessionFactory.getCurrentSession();

			String sqlQuery = "from LineItem where product.id=:productId and order.id=:orderId"; 
			
			Query<LineItem> theQuery = 
					currentSession.createQuery(sqlQuery,
												LineItem.class);
			
			theQuery.setParameter("productId", productId);
			theQuery.setParameter("orderId", orderId);
			
			List<LineItem> theLineItems = theQuery.getResultList();
		
			return theLineItems;
			 
	}
	
	@Override
	public List<LineItem> getLineItems(Order theOrder) {
		
		int orderId = theOrder.getId();
		
		Session currentSession = sessionFactory.getCurrentSession();

			String sqlQuery = "from LineItem where order.id=:orderId order by id"; 
			
			Query<LineItem> theQuery = 
					currentSession.createQuery(sqlQuery,
												LineItem.class);
			
			theQuery.setParameter("orderId", orderId);
			List<LineItem> list = theQuery.getResultList();
	
			return list;			
	}
	
	@Override
	public LineItem getLineItem(Product theProduct) {
		int productId = theProduct.getId();
		
		Session currentSession = sessionFactory.getCurrentSession();

			String sqlQuery = "from LineItem where product.id=:productId"; 
			
			Query<LineItem> theQuery = 
					currentSession.createQuery(sqlQuery,
												LineItem.class);
			
			theQuery.setParameter("productId", productId);
			LineItem lineItems = theQuery.getSingleResult();
	
			return lineItems;			
	}
	
	
	
	
	@Override
	public void delete(Product theProduct) {
		int productId = theProduct.getId();
		
		Session currentSession = sessionFactory.getCurrentSession();

			Query theQuery = 
					currentSession.createQuery("delete from LineItem where product.id = :productId");
			
			theQuery.setParameter("productId", productId);
			
			theQuery.executeUpdate(); 		
	}

	@Override
	public LineItem getLineItem(int lineItemId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
	
			return currentSession.get(LineItem.class, lineItemId);
		
	}
	


}
