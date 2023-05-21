package com.chrisenoch.onlineshop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.chrisenoch.onlineshop.entity.Order;
import com.chrisenoch.onlineshop.entity.LineItem;
import com.chrisenoch.onlineshop.entity.Product;
import com.chrisenoch.onlineshop.service.OrderService;
import com.chrisenoch.onlineshop.service.ProductService;


@Repository
@Profile("jpa_criteria")
public class LineItemDaoImpl_JPA_Criteria implements LineItemDao {
		
	private EntityManager entityManager;
	
	private ProductService productService;
	
	private OrderService orderService;
		
	@Autowired
	public LineItemDaoImpl_JPA_Criteria(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public void save(LineItem theLineItems) {

				entityManager.persist(theLineItems);;
		
	}
	
	@Override
	public void delete(LineItem theLineItems) {

		entityManager.remove(theLineItems);
		
	}
	
	@Override
	public List<LineItem> getLineItemsByProductAndOrder(Product theProduct, Order theOrder) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		CriteriaBuilder cb = currentSession.getCriteriaBuilder();
		CriteriaQuery<LineItem> cr = cb.createQuery(LineItem.class);
		Root<LineItem> root = cr.from(LineItem.class);
		
		Predicate equalsProductId = cb.equal(root.get("product"), theProduct);
		Predicate equalsOrderId = cb.equal(root.get("order"), theOrder);
		
		cr.select(root).where(cb.and(equalsProductId , equalsOrderId));
		
		Query<LineItem> query = currentSession.createQuery(cr);
		List<LineItem> theLineItems = query.getResultList(); 
		
		return theLineItems;
		
	}
	
	@Override
	public List<LineItem> getLineItems(Order theOrder) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		CriteriaBuilder cb = currentSession.getCriteriaBuilder();
		CriteriaQuery<LineItem> cr = cb.createQuery(LineItem.class);
		Root<LineItem> root = cr.from(LineItem.class);
		
		cr.select(root).where(cb.equal(root.get("order"), theOrder));
		//cr.select(root).where(cb.equal(root.get("order").get("id"), 123));
		
		//Order by LineItem id
		cr.orderBy(cb.asc(root.get("id")));
		
		Query<LineItem> query = currentSession.createQuery(cr);
		List<LineItem> theLineItems = query.getResultList(); 
		
		return theLineItems;
			
	}
	
	@Override
	public LineItem getLineItem(Product theProduct) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		CriteriaBuilder cb = currentSession.getCriteriaBuilder();
		CriteriaQuery<LineItem> cr = cb.createQuery(LineItem.class);
		Root<LineItem> root = cr.from(LineItem.class);
		
		cr.select(root).where(cb.equal(root.get("product"), theProduct));
		
		Query<LineItem> query = currentSession.createQuery(cr);
		LineItem theLineItems = query.getSingleResult();
	
		return theLineItems;			
	}
	
	
	
	@Override
	public void delete(Product theProduct) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		CriteriaBuilder cb = currentSession.getCriteriaBuilder();
		CriteriaDelete<LineItem> criteriaDelete = cb.createCriteriaDelete(LineItem.class);
		Root<LineItem> root = criteriaDelete.from(LineItem.class);
		criteriaDelete.where(cb.equal(root.get("product"), theProduct));
		 
		currentSession.createQuery(criteriaDelete).executeUpdate();
	
	}

	@Override
	public LineItem getLineItem(int id) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		CriteriaBuilder cb = currentSession.getCriteriaBuilder();
		CriteriaQuery<LineItem> cr = cb.createQuery(LineItem.class);
		Root<LineItem> root = cr.from(LineItem.class);
		
		cr.select(root).where(cb.equal(root.get("id"), id));
		
		Query<LineItem> query = currentSession.createQuery(cr);
		LineItem theLineItems = query.getSingleResult();

			// return the results		
			return theLineItems;
		
	}

}
