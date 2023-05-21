package com.chrisenoch.onlineshop.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.chrisenoch.onlineshop.entity.LineItem;
import com.chrisenoch.onlineshop.entity.Product;

@Repository
@Profile("hibernate")
public class ProductDaoImpl implements ProductDao {
	
	private SessionFactory sessionFactory;

	@Autowired
	public ProductDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(Product theProduct) {
				Session currentSession = sessionFactory.getCurrentSession();

				currentSession.saveOrUpdate(theProduct);
		
	}
	
	@Override
	public void delete(Product theProduct) {
				Session currentSession = sessionFactory.getCurrentSession();

				currentSession.delete(theProduct);
		
	}
	
	@Override
	public List<Product> getAllProducts() throws NoResultException {		
		Session currentSession = sessionFactory.getCurrentSession();

			String sqlQuery = "from Product"; 
			
			Query<Product> theQuery = 
					currentSession.createQuery(sqlQuery,
												Product.class);
			
			List<Product> theAddresses = theQuery.getResultList();
		
			return theAddresses;
			
	}
	
	@Override
	public Product getProduct(int productId) throws NoResultException {		
		Session currentSession = sessionFactory.getCurrentSession();
	
			return currentSession.get(Product.class, productId);
			
	}

}
