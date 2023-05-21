package com.chrisenoch.onlineshop.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.chrisenoch.onlineshop.entity.ProfilePage;
import com.chrisenoch.onlineshop.entity.User;
import com.chrisenoch.onlineshop.registration.user.RegistrationUser;

public interface UsersService extends UserDetailsService {
	
	public List<User> getMembers(int pageStart, int recordsPerPage);
	
	public int getTotalMembers();
	
	public User getUserByUsername(String username);
	
	public void saveProfilePicture(User theUser, String pictureURL);
	
	public void save(RegistrationUser regUser);
	
	public void delete(User theUser);
	
	public User getUserByEmail(String email);

	void uploadProfilePicture(ProfilePage profilePage, HttpServletRequest request, User theUser, String fileLocation); 
}
