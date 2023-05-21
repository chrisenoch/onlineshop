package com.chrisenoch.onlineshop.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chrisenoch.onlineshop.entity.User;
import com.chrisenoch.onlineshop.registration.user.RegistrationUser;
import com.chrisenoch.onlineshop.service.UsersService;


@Controller
@RequestMapping("/register")
public class RegistrationController {
	
    private UsersService userService;
    
    @Autowired
    public RegistrationController(UsersService userService) {
		this.userService = userService;
	}

	private Logger logger = Logger.getLogger(getClass().getName());
    
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {
		
		if (theModel.getAttribute("registrationError") != null){
			System.out.println("Debugging: Errors sent");
		}
		
		theModel.addAttribute("regUser", new RegistrationUser());
		
		return "registration-form";
	}
	

	/**
	 * Processes the registration request, validates the input, ensures the user name and email do not already exist
	 * and saves the user in the database if no errors are present.
	 * @param regUser - DTO with validation rules used for the registration
	 * @param theBindingResult
	 * @param theModel
	 * @param redirectAttributes
	 * @return a confirmation page upon success or the registration page indicating the errors.
	 */
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("regUser") RegistrationUser regUser, 
				BindingResult theBindingResult, 
				Model theModel, RedirectAttributes redirectAttributes) {
		
		String username = regUser.getUserName();
		logger.info("Processing registration form for: " + username);
		
		// form validation
		 if (theBindingResult.hasErrors()){
				 
			 return "registration-form";
	        }
	 
		// check the database if user already exists
		 List<String> registrationErrors = new ArrayList<>();
		 boolean errorExists = false;
		 User existing;
		 try {
			   existing = userService.getUserByUsername(username);
		 } catch(Exception exc){
			 existing = null;
		 }
 
        if (existing != null){
        	theModel.addAttribute("regUser", regUser);
        	registrationErrors.add("*User name already exists.");
			//theModel.addAttribute("registrationError", "User name already exists.");
			logger.warning("Username already exists.");
			errorExists = true;
        }
        
        String email = regUser.getEmail();
        try {
        	existing = userService.getUserByEmail(email);
        	registrationErrors.add("*Email already exists"); //If get to here, email already exists so an error is added.
        } catch (Exception exc) {
        	existing = null;
        }

        if (existing != null || errorExists) {
        	theModel.addAttribute("regUser", regUser);
        	//registrationErrors.add("*Email already exists");
        	//theModel.addAttribute("registrationError", registrationErrors);
        	redirectAttributes.addFlashAttribute("registrationError", registrationErrors);
        	logger.warning("Email already exists.");
        	errorExists = true;
        	return "redirect:/register/showRegistrationForm";
        }
        
        
     // create user account        						
        userService.save(regUser);
        
        logger.info("Successfully created user: " + username);
        
        return "registration-confirmation";		
	}
	
	@GetMapping("/processRegistrationForm")
	public String processRegistrationForm() {
		return "redirect:/register/showRegistrationForm";
	}
}
