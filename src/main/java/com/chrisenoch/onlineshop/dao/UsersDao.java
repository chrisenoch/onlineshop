package com.chrisenoch.onlineshop.dao;

import java.util.List;

import com.chrisenoch.onlineshop.entity.User;


 public interface UsersDao {
	
	 List<User> getMembers(int pageStart, int recordsPerPage);
	 int getTotalMembers();
	 User getUserByUsername(String username);
	 void saveProfilePicture(User theUser, String pictureURL);
	 void save(User theUser); 
	 void delete(User theUser);
	 User getUserByEmail(String email);  

}
