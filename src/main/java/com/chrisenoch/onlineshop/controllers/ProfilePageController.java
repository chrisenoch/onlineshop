package com.chrisenoch.onlineshop.controllers;

import java.io.File;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chrisenoch.onlineshop.entity.ProfilePage;
import com.chrisenoch.onlineshop.entity.User;
import com.chrisenoch.onlineshop.service.UsersService;


@Controller
@RequestMapping("/showProfilePage")
public class ProfilePageController {
	
	//load user data
	private UsersService usersService;
		
		@Autowired
		public ProfilePageController(UsersService theUsersService) {
			usersService = theUsersService;
		}
	
	
	@GetMapping({"", "/{username}"})
	public String showProfilePage(Model model, RedirectAttributes redirectAttributes, 
			HttpSession session, @PathVariable("username") Optional<String> username) {
		System.out.println("Inside get method"); 
			
		User theUser;
		
		 if (username.isPresent()) {
			 String fetchedUser = username.get();
	
				try {
					theUser = usersService.getUserByUsername(fetchedUser);
					System.out.println("user email " + theUser.getEmail());
						
				} catch (NoResultException exc) {
					redirectAttributes.addFlashAttribute("nouserfound", "nouserfound");
					System.out.println("noresult exception found here");
					return "redirect:/showProfilePage/searcherror";
					
				} catch (Exception exc) {
					System.out.println("Error trying to retrieve user from database by username" + exc.getMessage() + " " + 
					exc.getStackTrace());
					return "redirect:/showProfilePage/searcherror";
				}
				
				//test if user entered as path var is current logged in user. If so, display more options on profile page (upload picture option)
				User currentUser = (User)session.getAttribute("user");
				System.out.println("owner test " + currentUser + " " + theUser.getUsername() );
				if (theUser.getUsername().equals(currentUser.getUsername()) ) {
					return "redirect:/showProfilePage";
					//model.addAttribute("isProfileOwner", true);
				}
				//check if owner is same as pathvar
				//add model attruibute is owner if this is the case
				
		
	    } else {
	    	//get user id from the session	
	    	theUser = (User)session.getAttribute("user");
	    	
	    	model.addAttribute("isProfileOwner", true);
	    }
		
		//pass user object to view page
		 model.addAttribute("user", theUser);
			
		 ProfilePage profile= new ProfilePage();
		 model.addAttribute("newProfilePicture", profile);

		return "profile-page";
		
	}
	
	@PostMapping
	public String processImageUpload(@ModelAttribute("newProfilePicture") ProfilePage newProfilePicture,  BindingResult result, 
			HttpServletRequest request, HttpSession session) {
		
		  User theUser = (User)session.getAttribute("user"); 
		
		String[] suppressedFields = result.getSuppressedFields();
		   if (suppressedFields.length > 0) {
		      throw new RuntimeException("Attempting to bind disallowed fields: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
		   }
		 
		usersService.uploadProfilePicture(newProfilePicture, request, theUser
				, "C:\\Users\\chris\\pictures\\onlineshop\\profile\\" + theUser.getiD() + ".png");
		
		System.out.println("Inside post method");
		System.out.println("test method for Chris " + newProfilePicture.getTest());
	
	   //redirect so that refreshing page does not resubmit values
	   return "redirect:/showProfilePage";
	}
	
	@GetMapping("/searcherror")
	public String showErrorPage(Model theModel) {
		if (theModel.getAttribute("nouserfound") != null) {
			return "errorpage_usernotfound";
		} else {
			return "errorpage_general";
		}	
	}
	
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
	   binder.setAllowedFields("profileImage", "language"); //Improve code - this is wrong
	   //binder.setAllowedFields("profileImage",
   //"productImage"); //Improve code - this is wrong
	}
	

}