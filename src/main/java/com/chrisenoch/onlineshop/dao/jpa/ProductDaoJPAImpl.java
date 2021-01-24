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
import com.chrisenoch.onlineshop.entity.OrderContents;
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
	
	@Override
	public int reduceStock(int productId, int quantityToReduce) throws Exception {		
		
		if (quantityToReduce < 1) {
			throw new Exception("Invalid quantity entered. You cannot reduce the stock by a number less than one.");
		} //Do a test for this
		
		Product product = eF.find(Product.class, productId);
		
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

		Product product = eF.find(Product.class, productId);

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
