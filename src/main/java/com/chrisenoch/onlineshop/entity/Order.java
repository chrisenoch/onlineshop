package com.chrisenoch.onlineshop.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="Order")
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user; //Improve code: shoud this be user or user_id?
	
	@Column(name="order_date")
	private Date orderDate;
	
	@Column(name="is_processed")
	private boolean isProcessed;
		
	@OneToMany(mappedBy="order", fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
	private List<OrderContents> orderContents = new ArrayList<>();
	
	public void addOrderContents(OrderContents oC) {
		oC.setOrder(this);
		orderContents.add(oC);
	}
	
	public void removeOrderContents(OrderContents oC) {
		oC.setOrder(null);
		orderContents.remove(oC);
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	

	public Date getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public List<OrderContents> getOrderContents() {
		return orderContents;
	}
	
	public void setOrderContents(List<OrderContents> orderContents) {
		for (OrderContents oC : orderContents) {
			oC.setOrder(this);
		}
		this.orderContents = orderContents;
	}
	
	public boolean isProcessed() {
		return isProcessed;
	}

	public void setProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}
	
	//Should this be int or souble?
	public double shippingCost(int quantityOfOrderContents) {
		System.out.println("Quantity of orderContents: " + quantityOfOrderContents);
		System.out.println("entered shippingCost method - Order");
		double shippingCost = quantityOfOrderContents == 0? 0 : 3.0 + (quantityOfOrderContents -1) * 0.50;
		System.out.println("leaving shippingCost method" + shippingCost);
		return shippingCost;
	}
	
	public int getOrderContentsTotal() {
		int total = 0;
		
		if (orderContents == null) {
			return total;
		}
		
		total = orderContents.stream().mapToInt(x -> x.getQuantity()).sum();
		//Optional<OrderContents> totalTest = orderContents.stream().max(Comparator.comparing(x->x.getQuantity()));
		
		/*
		 * for (OrderContents oC : orderContents) {
			total += oC.getQuantity();
		}
		 */
		return total;
	}
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", user=" + user + ", orderDate=" + orderDate + ", isProcessed=" + isProcessed
				+ ", orderContents=" + orderContents + "]";
	}

	public Order() {
	}
	
}
