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
	private List<LineItem> lineItems = new ArrayList<>();
	
	public void addLineItem(LineItem lineItem) {
		lineItem.setOrder(this);
		lineItems.add(lineItem);
	}
	
	public void removeLineItem (LineItem lineItem) {
		lineItem.setOrder(null);
		lineItems.remove(lineItem);
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
	
	public List<LineItem> getLineItems() {
		return lineItems;
	}
	
	public void setLineItems(List<LineItem> lineItems) {
		for (LineItem oC : lineItems) {
			oC.setOrder(this);
		}
		this.lineItems = lineItems;
	}
	
	public boolean isProcessed() {
		return isProcessed;
	}

	public void setProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}
	
	public double shippingCost(int quantityOfLineItems) {
		double shippingCost = quantityOfLineItems == 0? 0 : 3.0 + (quantityOfLineItems -1) * 0.50;
		return shippingCost;
	}
	
	public int getLineItemsTotal() {
		int total = 0;
		
		if (lineItems == null) {
			return total;
		}
		
		total = lineItems.stream().mapToInt(x -> x.getQuantity()).sum();

		return total;
	}
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", user=" + user + ", orderDate=" + orderDate + ", isProcessed=" + isProcessed
				+ ", lineItems=" + lineItems + "]";
	}

	public Order() {
	}
	
}
