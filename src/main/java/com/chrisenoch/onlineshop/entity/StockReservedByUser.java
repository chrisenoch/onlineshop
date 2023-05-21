package com.chrisenoch.onlineshop.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Represents the quantity of a single product that has been reserved by a user. That is to say, it represents a line item reserved by a user.
 * @author chris
 *
 */
@Entity
public class StockReservedByUser {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private User user;
	
	private int quantity;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public StockReservedByUser(Product product, int quantity, User user) {
		this.product = product;
		this.quantity = quantity;
		this.user = user;
	}
	
	public StockReservedByUser() {

	}
	
	
	
	
	
	
}
