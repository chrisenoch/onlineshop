package com.chrisenoch.onlineshop.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chrisenoch.onlineshop.entity.User;
import com.chrisenoch.onlineshop.service.UsersService;

@Controller
public class AdminController {
	
	//load member data
	private UsersService usersService;
	
	@Autowired// Learn code: Since we only have one constructor, Autowired is optional
	public AdminController(UsersService theUsersService) {
		usersService = theUsersService;
	}
	
	@GetMapping("/accessleveltests")
	public String showAccessLevelTests() {
		
		return "accessleveltests";
	}

	@GetMapping("/leaders")
	public String showLeaders() {
		
		return "leaders";
	}
	
	@GetMapping("/systems")
	public String showSystems() {
		
		return "systems";
	}
	
	@GetMapping("/members") 
	public String showMembers(@RequestParam(required = false)Integer page, Model model) {
		
		if (page == null) {
			return "redirect:/members?page=1";
		}
		
		double total = usersService.getTotalMembers(); //This is double to ensure division calculation is correct. If not, int division will chop off decimal value.
		int recordsPerPage = 5;	//refactor this	
		
		int startIndex = (page - 1) * recordsPerPage;
		
		List<User> users = usersService.getMembers(startIndex, recordsPerPage);
		int numOfPages = (int)Math.ceil(total/recordsPerPage); 

		model.addAttribute("members", users);
		model.addAttribute("total", total);
		model.addAttribute("numOfPages", numOfPages );
			
		return "members";
	}

}
