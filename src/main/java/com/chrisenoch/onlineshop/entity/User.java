package com.chrisenoch.onlineshop.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity(name="Users")
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int iD;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@Column(name="enabled")
	private byte enabled;
	
	@Column(name="picture_URL")
	private String pictureURL;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
	private List<Address> addresses;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade= {CascadeType.DETACH, 
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}) 
	private List<Order> orders;
	

	public User() {
	}

	public User(String firstName, String lastName, String username, String password, String email, byte enabled,
			String pictureURL, Collection<Role> roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.pictureURL = pictureURL;
		this.roles = roles;
	}
	

	public User(String firstName, String lastName, String username, String password, String email, byte enabled) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
	}



	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {

		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public byte getEnabled() {
		return enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

	public String getPictureURL() {
		return pictureURL;
	}

	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [iD=" + iD + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", email=" + email + ", pictureURL=" + pictureURL + "]";
	}
	
	

	

	

	
	

	
	
	
	
	

}
