package com.chrisenoch.onlineshop.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.chrisenoch.onlineshop.entity.User;
import com.chrisenoch.onlineshop.service.UsersService;



@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	
	@Autowired
    private UsersService usersService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		System.out.println("\n\nIn customAuthenticationSuccessHandler\n\n");

		String userName = authentication.getName();
		
		System.out.println("userName=" + userName);

		User theUser = usersService.getUserByUsername(userName);
		
		System.out.println("username retrieved via Authentication : " + theUser);
		
		// now place in the session
		HttpSession session = request.getSession();
		session.setAttribute("user", theUser);
		session.setAttribute("userId", theUser.getiD());
		
		// forward to home page
		System.out.println("\n\nAbout to leave customAuthenticationSuccessHandler\n\n");
		
		response.sendRedirect(request.getContextPath() + "/showProfilePage");
			
	}

}
