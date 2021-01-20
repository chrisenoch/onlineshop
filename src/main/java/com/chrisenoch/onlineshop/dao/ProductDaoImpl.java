package com.chrisenoch.onlineshop.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chrisenoch.onlineshop.entity.OrderContents;
import com.chrisenoch.onlineshop.entity.Product;

@Repository
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
	
	@Override
	public int reduceStock(int productId, int quantityToReduce) throws Exception {		
		
		if (quantityToReduce < 1) {
			throw new Exception("Invalid quantity entered. You cannot reduce the stock by a number less than one.");
		} //Do a test for this
		
		Session currentSession = sessionFactory.getCurrentSession();
		Product product = currentSession.get(Product.class, productId);
		
		int currentStockQuantity = product.getStock();
		System.out.println("currentStockQuantity " + currentStockQuantity);
		System.out.println(" quantityToReduce " +  quantityToReduce);
		
		
		int newStockQuantity = currentStockQuantity - quantityToReduce;
		System.out.println(" newStockQuantity  " +  newStockQuantity );
		
		if (newStockQuantity < 0) {
			throw new Exception ("You cannot reduce the stock level by an amount higher than "
					+ "the current stock level. " + product.getName() + " with product id: " + 
					product.getId() + ", currently has: " + currentStockQuantity + " items in stock");
		}
		
		product.setStock(newStockQuantity);
		
		return newStockQuantity;
			
	}
	
	@Override
	public int addStock(int productId, int quantityToAdd) throws Exception {		

		Session currentSession = sessionFactory.getCurrentSession();
		Product product = currentSession.get(Product.class, productId);

		int newStock = product.getStock() + quantityToAdd;
		product.setStock(newStock);
		
		return newStock;
			
	}
	
	@Override
	public Map<Product, Integer> getProductsInBasketByMostRecent(List<OrderContents> list){
		Map<Product, Integer> map = new LinkedHashMap<>();
		
		int size = list.size();
		for (int i = 1; i <= list.size(); i++) {
			Product product = list.get(size - i).getProduct();
			Integer quantity = list.get(size - i).getQuantity();
			map.put(product, quantity);
		}
		
		
		return map;
	}

}
