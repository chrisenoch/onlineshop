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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="address")
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Column(name="full_name")
	private String fullName;
	
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Column(name="country_region")
	private String country;
	
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Column(name="address_line_1")
	private String addressLine1;
	
	@Size(min=1, message="must be at least one character")
	@Column(name="address_line_2")
	private String addressLine2;
	
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Column(name="city")
	private String city;
	
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Column(name="county_state_region_province")
	private String countyOrState;
	
	@NotNull(message="is required")
	@Pattern(regexp="^[A-Za-z0-9]{5,6}", message="must be 5 or 6 characters/digits") //Improve code: Check this
	@Column(name="postcode")
	private String postCode;
	
	@NotNull(message="is required")
	@Size(min=7, message="is required")
	@Column(name="telephone_number")
	private String telephoneNo;
	
	@Size(min=1, message="must be at least one character")
	@Column(name="extra_delivery_instructions")
	private String extraDeliveryInstructions;
	
	@Column(name="is_default")
	private boolean defaultAddress;
	 
	@Column(name="date_last_used")
	private LocalDateTime dateLastUsed;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountyOrState() {
		return countyOrState;
	}
	public void setCountyOrState(String countyOrState) {
		this.countyOrState = countyOrState;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getTelephoneNo() {
		return telephoneNo;
	}
	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}
	public String getExtraDeliveryInstructions() {
		return extraDeliveryInstructions;
	}
	public void setExtraDeliveryInstructions(String extraDeliveryInstructions) {
		this.extraDeliveryInstructions = extraDeliveryInstructions;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String nameAndSurnames) {
		this.fullName= nameAndSurnames;
	}
	
	public boolean getDefaultAddress() {
		return defaultAddress;
	}
	public void setDefaultAddress(boolean isDefaultAddress) {
		this.defaultAddress = isDefaultAddress;
	}
	
	public LocalDateTime getDateLastUsed() {
		return dateLastUsed;
	}
	public void setDateLastUsed(LocalDateTime dateLastUsed) {
		this.dateLastUsed = dateLastUsed;
	}
	public Address() {
	}
	public Address(User user, @NotNull(message = "is required") @Size(min = 1, message = "is required") String fullName,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String country,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String addressLine1,
			@Size(min = 1, message = "must be at least one character") String addressLine2,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String city,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String countyOrState,
			@Pattern(regexp = "^[A-Za-z0-9]{5,6}", message = "only 5 characters/digits maximum") String postCode,
			@NotNull(message = "is required") @Size(min = 7, message = "is required") String telephoneNo,
			@Size(min = 1, message = "must be at least one character") String extraDeliveryInstructions,
			boolean defaultAddress, LocalDateTime dateLastUsed) {
		this.user = user;
		this.fullName = fullName;
		this.country = country;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.countyOrState = countyOrState;
		this.postCode = postCode;
		this.telephoneNo = telephoneNo;
		this.extraDeliveryInstructions = extraDeliveryInstructions;
		this.defaultAddress = defaultAddress;
		this.dateLastUsed = dateLastUsed;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", user=" + user + ", fullName=" + fullName + ", country=" + country
				+ ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", city=" + city
				+ ", countyOrState=" + countyOrState + ", postCode=" + postCode + ", telephoneNo=" + telephoneNo
				+ ", extraDeliveryInstructions=" + extraDeliveryInstructions + ", defaultAddress=" + defaultAddress
				+ ", dateLastUsed=" + dateLastUsed + "]";
	}
	
	
	
}
