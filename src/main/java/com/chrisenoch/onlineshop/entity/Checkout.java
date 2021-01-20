package com.chrisenoch.onlineshop.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name="OrderCalculations")
@Table(name="checkout")
public class Checkout {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="total_price")
	private int totalprice;
	
	@Column(name="shipping_cost")
	private double shippingCost;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="order_id")
	private Order order;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="delivery_information_id")
	private DeliveryInformation deliveryInformation;
	
	
	@Column(name="stripe_charge_id")
	private String stripeChargeId; 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	public double getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	

	public DeliveryInformation getDeliveryInformation() {
		return deliveryInformation;
	}

	public void setDeliveryInformation(DeliveryInformation deliveryInformation) {
		this.deliveryInformation = deliveryInformation;
	}

	public String getStripeChargeId() {
		return stripeChargeId;
	}

	public void setStripeChargeId(String stripeChargeId) {
		this.stripeChargeId = stripeChargeId;
	}

	public Checkout() {		
	}

	public Checkout(int totalprice, double shippingCost, Order order, DeliveryInformation deliveryInformation,
			String stripeChargeId) {
		this.totalprice = totalprice;
		this.shippingCost = shippingCost;
		this.order = order;
		this.deliveryInformation = deliveryInformation;
		this.stripeChargeId = stripeChargeId;
	}

}
