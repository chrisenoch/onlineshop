 package com.chrisenoch.onlineshop.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="DeliveryInformation")
@Table(name="delivery_information")
public class DeliveryInformation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="ship_date")
	private LocalDateTime shipDate;
	
	@Column(name="predicted_arrival_date")
	private LocalDateTime predictedArrivalDate;
	
	@Column(name="is_delivered")
	private Boolean isDelivered;
	
	@ManyToOne
	@JoinColumn(name="address_id")
	private Address address;

	public DeliveryInformation(LocalDateTime shipDate, LocalDateTime predictedArrivalDate, Boolean isDelivered, Address address) {
		this.shipDate = shipDate;
		this.predictedArrivalDate = predictedArrivalDate;
		this.isDelivered = isDelivered;
		this.address = address;
	}

	public DeliveryInformation() {
	}
	
	

}
