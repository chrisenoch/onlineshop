package com.chrisenoch.onlineshop.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.chrisenoch.onlineshop.dao.OrderContentsDao;
import com.chrisenoch.onlineshop.entity.Order;
import com.chrisenoch.onlineshop.entity.OrderContents;
import com.chrisenoch.onlineshop.entity.Product;


@Repository
@Profile("jpa")
public class OrderContentsDaoJPAImpl implements OrderContentsDao {
	EntityManager eF ;

	public OrderContentsDaoJPAImpl(EntityManager eF) {
		this.eF = eF;
	}

	@Override
	public void save(OrderContents theOrderContents) {
				eF.merge(theOrderContents);
		
	}
	
	@Override
	public void delete(OrderContents theOrderContents) {
				eF.remove(theOrderContents);
		
	}
	
	@Override
	public List<OrderContents> getOrderContentsByProductAndOrder(Product theProduct, Order theOrder) {
		
		int productId = theProduct.getId();
		int orderId = theOrder.getId();

			String sqlQuery = "from OrderContents where product.id=:productId and order.id=:orderId"; 
			
			TypedQuery<OrderContents> theQuery = 
					eF.createQuery(sqlQuery,
												OrderContents.class);
			
			theQuery.setParameter("productId", productId);
			theQuery.setParameter("orderId", orderId);
			
			List<OrderContents> theOrderContents = theQuery.getResultList();
		
			return theOrderContents;
			 
	}
	
	@Override
	public List<OrderContents> getOrderContents(Order theOrder) {
		
		int orderId = theOrder.getId();

			String sqlQuery = "from OrderContents where order.id=:orderId order by id"; 
			
			TypedQuery<OrderContents> theQuery = 
					eF.createQuery(sqlQuery, OrderContents.class);
			
			theQuery.setParameter("orderId", orderId);
			List<OrderContents> list = theQuery.getResultList();
	
			return list;			
	}
	
	@Override
	public OrderContents getOrderContent(Product theProduct) {
		int productId = theProduct.getId();

			String sqlQuery = "from OrderContents where product.id=:productId"; 
			
			TypedQuery<OrderContents> theQuery = 
					eF.createQuery(sqlQuery, OrderContents.class);
			
			theQuery.setParameter("productId", productId);
			OrderContents orderContents = theQuery.getSingleResult();
	
			return orderContents;			
	}
	
	@Override
	public void delete(Product theProduct) {
		int productId = theProduct.getId();

			Query theQuery = 
					eF.createQuery("delete from OrderContents where product.id = :productId");
			
			theQuery.setParameter("productId", productId);
			
			theQuery.executeUpdate(); 		
	}

	@Override
	public OrderContents getOrderContent(int id) {
			return eF.find(OrderContents.class, id);
		
	}
	


}
