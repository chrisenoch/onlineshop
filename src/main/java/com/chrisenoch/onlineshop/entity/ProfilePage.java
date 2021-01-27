package com.chrisenoch.onlineshop.entity;

import org.springframework.web.multipart.MultipartFile;

public class ProfilePage {
	
	private int iD;
	
	private User user;

	private MultipartFile profileImage;

	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MultipartFile getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(MultipartFile profileImage) {
		this.profileImage = profileImage;
	}

	public ProfilePage(int iD, User user, MultipartFile profileImage) {
		this.iD = iD;
		this.user = user;
		this.profileImage = profileImage;
	}
	
	public ProfilePage() {  //Improve code: Take out no arg constructor. This makes it hard for a profile to be constructed which is not associated with a user
	}
	
	
	
}
