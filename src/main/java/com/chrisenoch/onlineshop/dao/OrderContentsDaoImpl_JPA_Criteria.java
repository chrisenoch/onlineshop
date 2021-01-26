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
import com.chrisenoch.onlineshop.entity.OrderContents;
import com.chrisenoch.onlineshop.entity.Product;
import com.chrisenoch.onlineshop.service.OrderService;
import com.chrisenoch.onlineshop.service.ProductService;


@Repository
@Profile("jpa_criteria")
public class OrderContentsDaoImpl_JPA_Criteria implements OrderContentsDao {
		
	private EntityManager entityManager;
	
	private ProductService productService;
	
	private OrderService orderService;
		
	@Autowired
	public OrderContentsDaoImpl_JPA_Criteria(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public void save(OrderContents theOrderContents) {

				entityManager.persist(theOrderContents);;
		
	}
	
	@Override
	public void delete(OrderContents theOrderContents) {

		entityManager.remove(theOrderContents);
		
	}
	
	@Override
	public List<OrderContents> getOrderContentsByProductAndOrder(Product theProduct, Order theOrder) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		CriteriaBuilder cb = currentSession.getCriteriaBuilder();
		CriteriaQuery<OrderContents> cr = cb.createQuery(OrderContents.class);
		Root<OrderContents> root = cr.from(OrderContents.class);
		
		Predicate equalsProductId = cb.equal(root.get("product"), theProduct);
		Predicate equalsOrderId = cb.equal(root.get("order"), theOrder);
		
		cr.select(root).where(cb.and(equalsProductId , equalsOrderId));
		
		Query<OrderContents> query = currentSession.createQuery(cr);
		List<OrderContents> theOrderContents = query.getResultList(); 
		
		return theOrderContents;
		
	}
	
	@Override
	public List<OrderContents> getOrderContents(Order theOrder) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		CriteriaBuilder cb = currentSession.getCriteriaBuilder();
		CriteriaQuery<OrderContents> cr = cb.createQuery(OrderContents.class);
		Root<OrderContents> root = cr.from(OrderContents.class);
		
		cr.select(root).where(cb.equal(root.get("order"), theOrder));
		//cr.select(root).where(cb.equal(root.get("order").get("id"), 123));
		
		//Order by OrderContents id
		cr.orderBy(cb.asc(root.get("id")));
		
		Query<OrderContents> query = currentSession.createQuery(cr);
		List<OrderContents> theOrderContents = query.getResultList(); 
		
		return theOrderContents;
			
	}
	
	@Override
	public OrderContents getOrderContent(Product theProduct) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		CriteriaBuilder cb = currentSession.getCriteriaBuilder();
		CriteriaQuery<OrderContents> cr = cb.createQuery(OrderContents.class);
		Root<OrderContents> root = cr.from(OrderContents.class);
		
		cr.select(root).where(cb.equal(root.get("product"), theProduct));
		
		Query<OrderContents> query = currentSession.createQuery(cr);
		OrderContents theOrderContents = query.getSingleResult();
	
		return theOrderContents;			
	}
	
	
	
	@Override
	public void delete(Product theProduct) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		CriteriaBuilder cb = currentSession.getCriteriaBuilder();
		CriteriaDelete<OrderContents> criteriaDelete = cb.createCriteriaDelete(OrderContents.class);
		Root<OrderContents> root = criteriaDelete.from(OrderContents.class);
		criteriaDelete.where(cb.equal(root.get("product"), theProduct));
		 
		currentSession.createQuery(criteriaDelete).executeUpdate();
	
	}

	@Override
	public OrderContents getOrderContent(int id) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		CriteriaBuilder cb = currentSession.getCriteriaBuilder();
		CriteriaQuery<OrderContents> cr = cb.createQuery(OrderContents.class);
		Root<OrderContents> root = cr.from(OrderContents.class);
		
		cr.select(root).where(cb.equal(root.get("id"), id));
		
		Query<OrderContents> query = currentSession.createQuery(cr);
		OrderContents theOrderContents = query.getSingleResult();

			// return the results		
			return theOrderContents;
		
	}

}
