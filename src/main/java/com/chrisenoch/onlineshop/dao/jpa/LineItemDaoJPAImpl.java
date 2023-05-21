package com.chrisenoch.onlineshop.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.chrisenoch.onlineshop.dao.LineItemDao;
import com.chrisenoch.onlineshop.entity.Order;
import com.chrisenoch.onlineshop.entity.LineItem;
import com.chrisenoch.onlineshop.entity.Product;


@Repository
@Profile("jpa")
public class LineItemDaoJPAImpl implements LineItemDao {
	EntityManager eF ;

	public LineItemDaoJPAImpl(EntityManager eF) {
		this.eF = eF;
	}

	@Override
	public void save(LineItem theLineItems) {
				eF.merge(theLineItems);
		
	}
	
	@Override
	public void delete(LineItem theLineItems) {
				eF.remove(theLineItems);
		
	}
	
	@Override
	public List<LineItem> getLineItemsByProductAndOrder(Product theProduct, Order theOrder) {
		
		int productId = theProduct.getId();
		int orderId = theOrder.getId();

			String sqlQuery = "from LineItem where product.id=:productId and order.id=:orderId"; 
			
			TypedQuery<LineItem> theQuery = 
					eF.createQuery(sqlQuery,
												LineItem.class);
			
			theQuery.setParameter("productId", productId);
			theQuery.setParameter("orderId", orderId);
			
			List<LineItem> theLineItems = theQuery.getResultList();
		
			return theLineItems;
			 
	}
	
	@Override
	public List<LineItem> getLineItems(Order theOrder) {
		
		int orderId = theOrder.getId();

			String sqlQuery = "from LineItem where order.id=:orderId order by id"; 
			
			TypedQuery<LineItem> theQuery = 
					eF.createQuery(sqlQuery, LineItem.class);
			
			theQuery.setParameter("orderId", orderId);
			List<LineItem> list = theQuery.getResultList();
	
			return list;			
	}
	
	@Override
	public LineItem getLineItem(Product theProduct) {
		int productId = theProduct.getId();

			String sqlQuery = "from LineItem where product.id=:productId"; 
			
			TypedQuery<LineItem> theQuery = 
					eF.createQuery(sqlQuery, LineItem.class);
			
			theQuery.setParameter("productId", productId);
			LineItem lineItems = theQuery.getSingleResult();
	
			return lineItems;			
	}
	
	@Override
	public void delete(Product theProduct) {
		int productId = theProduct.getId();

			Query theQuery = 
					eF.createQuery("delete from LineItem where product.id = :productId");
			
			theQuery.setParameter("productId", productId);
			
			theQuery.executeUpdate(); 		
	}

	@Override
	public LineItem getLineItem(int id) {
			return eF.find(LineItem.class, id);
		
	}
	


}
