package com.chrisenoch.onlineshop.security.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.chrisenoch.onlineshop.entity.StockReservedByUser;
import com.chrisenoch.onlineshop.entity.User;
import com.chrisenoch.onlineshop.service.StockReservedByUserService;
import com.chrisenoch.onlineshop.service.UsersService;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
	
	@Autowired
	private StockReservedByUserService stockReservedByUserService;
	@Autowired
	private UsersService usersService;
	

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		
		//get user from session
		String userName = authentication.getName();
		User user = usersService.getUserByUsername(userName);
	
		List<StockReservedByUser> stockReservedByUser = stockReservedByUserService.getStockReservedByUser(user);
		
		for ( StockReservedByUser sRBU: stockReservedByUser ) {	
			try {
				stockReservedByUserService.shiftStockFromStockReservedByUserToProduct(sRBU);
			} catch (Exception e) {
				e.printStackTrace();	
				//To do
				//Log error message here.
			}	
		}
		
		response.sendRedirect(request.getContextPath() + "/showProfilePage");
		
		

	}

}
