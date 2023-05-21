package com.chrisenoch.onlineshop.dao.jpa;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.chrisenoch.onlineshop.dao.ProductDao;
import com.chrisenoch.onlineshop.entity.LineItem;
import com.chrisenoch.onlineshop.entity.Product;

@Repository
@Profile("jpa")
public class ProductDaoJPAImpl implements ProductDao {

	EntityManager eF ;

	public ProductDaoJPAImpl(EntityManager eF) {
		this.eF = eF;
	}

	@Override
	public void save(Product theProduct) {
				eF.merge(theProduct);
		
	}
	
	@Override
	public void delete(Product theProduct) {
				eF.remove(theProduct);
		
	}
	
	@Override
	public List<Product> getAllProducts() throws NoResultException {		
			String sqlQuery = "from Product"; 
			
			TypedQuery<Product> theQuery = 
					eF.createQuery(sqlQuery,
												Product.class);
			
			List<Product> theAddresses = theQuery.getResultList();
		
			return theAddresses;
			
	}
	
	@Override
	public Product getProduct(int productId) throws NoResultException {		
			return eF.find(Product.class, productId);
			
	}
	
	


}
