package com.chrisenoch.onlineshop.entity;

import java.text.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Represents the product details along with the stock available for this product.
 * @author chris
 *
 */
@Entity(name="Product")
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="brand")
	private String brand;
	
	@Column(name="price")
	private int price; //price stored as value * 100 in database. getPriceFormatted method converts price to real value and formats it correctly.
	
	@Column(name="stock")
	private int stock;
	
	@Column(name="picture_url")
	private String pictureUrl;

	@Column(name="category")
	private String category;
	
	@Column(name="description")
	private String description;
	
	//to be used when retrieving price via JSTL for example
	public String getPriceFormatted() {
		System.out.println("inside getter");
		//System.out.println(priceFormatted);
		
		NumberFormat nF = NumberFormat.getInstance();
		System.out.println("numberformat: "  + nF.toString());
		nF.setMinimumFractionDigits(2);
        nF.setMaximumFractionDigits(2);   
        
        double priceFormatted = price;
		priceFormatted =  priceFormatted/100;
		String f =  nF.format(priceFormatted);
		System.out.println("class price fomatted no arg " + f);
		return f;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getPrice() {
		return price; 
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
		
	}
	
	
	
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Product() {
	}
	
	public Product(String name, String brand, int price, int stock, String pictureUrl, 
			String category, String description) {
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.stock = stock;
		this.pictureUrl = pictureUrl;
		this.category = category;
		this.description = description;	
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", brand=" + brand + ", price=" + price + ", stock=" + stock
				+ ", pictureUrl=" + pictureUrl + ", category=" + category + ", description=" + description + "]";
	}
	
	
	
	

}
