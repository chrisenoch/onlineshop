package com.chrisenoch.onlineshop.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
	
	@GetMapping("/") 
	public String sayHello(HttpSession session){
		return "redirect:/showProfilePage";
	}

}
