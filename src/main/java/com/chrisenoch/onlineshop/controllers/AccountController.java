 package com.chrisenoch.onlineshop.controllers;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.chrisenoch.onlineshop.entity.Address;
import com.chrisenoch.onlineshop.entity.User;
import com.chrisenoch.onlineshop.service.AddressService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	private AddressService addressService;
	
	@Autowired
	public AccountController(AddressService addressService) {
		this.addressService = addressService;
	}

	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);	
	} //Learn code: Find another way of doing this?
	
	
	@RequestMapping(value = "/testonly", method = { RequestMethod.GET, RequestMethod.POST })
	public String test(HttpServletRequest request) {
		
		System.out.println("Debugging: Request method: " + request.getMethod());

		return "manageaddresses";		
	}
	
	/**
	 * Method called when user prompted to enter an address at checkout because he/she has no address saved. This ensures that once the user
	 * has added an address he/she is redirected back to checkout.
	 * @param model
	 * @param session
	 * @return
	 */
	@PostMapping("/fromcheckout")
	public String fromCheckout(Model model, HttpSession session) {
		
		int userId = (int)session.getAttribute("userId"); 	
		
		model.addAttribute("fromCheckout", "fromCheckout");	
		
		Address address = new Address();
		model.addAttribute("address", address);
		displayAddressesByDefaultThenByMostRecentlyUsed(userId, model);
		
		return "manageaddresses";
	}
	
	
	/**
	 * Responsible for displaying the user's addresses. Furthermore, depending on the request parameters it may set an
	 * address as default or delete an address.
	 * @param model
	 * @param session
	 * @param request
	 * @param addressIdDel
	 * @param addressIdDefault
	 * @return
	 */
	@GetMapping("/addresses")
	//@RequestMapping(value = "/addresses", method = { RequestMethod.GET, RequestMethod.POST })
	public String manageAddresses(Model model, HttpSession session, HttpServletRequest request
			, @RequestParam("del") Optional<Integer> addressIdDel
			, @RequestParam("default") Optional<Integer> addressIdDefault
			) {	
		
		if (addressIdDel.isPresent()) {
			try {
			Address theAddress = addressService.getAddress(addressIdDel.get());		
				addressService.delete(theAddress);
			} catch (Exception exc) {
				System.out.println(exc);
			}	
		}
		
		int userId = (int)session.getAttribute("userId"); 	
		if (addressIdDefault.isPresent()) {
			addressService.setDefaultAddress(addressIdDefault.get(), userId);

		}
		
		Address address = new Address();
		model.addAttribute("address", address);
		displayAddressesByDefaultThenByMostRecentlyUsed(userId, model);

		return "manageaddresses";
	}
	
	@PostMapping("/addresses")
	public String processForm(
			@Valid @ModelAttribute("address") Address address, 
			BindingResult theBindingResult, HttpSession session) {
		
		System.out.println("Last name: |" + address.getFullName() + "|"); //Clever code: Helps see if have any leading or trailing whitespace.	
		System.out.println("Binding result: " + theBindingResult);
		
		if(theBindingResult.hasErrors()) {
			return "manageaddresses";
		} else {
			System.out.println("noerrrors");
			setUserAndAddressInfo(address, session);
			addressService.save(address);
			return "redirect:/account/addresses";
		}
	}
	
	@PostMapping("/addressescheckout")
	public String processFormFromCheckout(
			@Valid @ModelAttribute("address") Address address, 
			BindingResult theBindingResult, HttpSession session, Model model) {
		
		System.out.println("Last name: |" + address.getFullName() + "|"); //Clever code: Helps see if have any leading or trailing whitespace.	
		System.out.println("Binding result: " + theBindingResult);
		
		if(theBindingResult.hasErrors()) {
			//Readd var which sends user back to checkout page upon successful form submission
			model.addAttribute("fromCheckout", "fromCheckout");
			
			int userId = (int)session.getAttribute("userId"); 
			displayAddressesByDefaultThenByMostRecentlyUsed(userId, model);
			
			
			return "manageaddresses";
		} else {
			setUserAndAddressInfo(address, session);
			addressService.save(address);
			return "redirect:/checkout";
		}
	}
	
	/**
	 * Returns a form where a user can add an address if the edit request parameter is not set. Otherwise it populates the form with an exisiting
	 * address ready for the user to update.
	 * @param model
	 * @param addressId
	 * @return
	 */
	@GetMapping("/updateaddress")
	public String updateAddress(Model model
			, @RequestParam("edit") Optional<Integer> addressId) {
		
		if (addressId.isPresent()) {
			Address address = addressService.getAddress(addressId.get());
			
			model.addAttribute("address", address);
			
		} else {
			Address address = new Address();
			
			model.addAttribute("address", address);	
		}

		return "updateaddress";
	}
	
	@PostMapping("/updateaddress")
	public String processUpdateAddress(@Valid @ModelAttribute("address") Address address, 
			BindingResult theBindingResult, HttpSession session) {
		
		System.out.println("Last name: |" + address.getFullName() + "|"); //Clever code: Helps see if have any leading or trailing whitespace.	
		System.out.println("Binding result: " + theBindingResult);
		
		if(theBindingResult.hasErrors()) {

			return "updateaddress";
		} else {
			setUserAndAddressInfo(address, session);
			addressService.save(address);
			return "redirect:/account/addresses";
		}
	}

	
	private void displayAddressesByDefaultThenByMostRecentlyUsed(int userId, Model model) {
		List<Address> addresses = addressService.getAllAddresses(userId);
		
		//Default address should appear first, followed by address that was most recently used.
		Collections.sort(addresses, new Comparator<Address>(){
			public int compare(Address a1, Address a2) {
				int result = Boolean.compare(a2.getDefaultAddress() , a1.getDefaultAddress());
				if (result == 0) {
					return a2.getDateLastUsed().compareTo(a1.getDateLastUsed());
				} else {
					return result;
				}
			}	
		});
		
		model.addAttribute("addresses", addresses);
	}

	private void setUserAndAddressInfo(Address address, HttpSession session) {
		User theUser = (User)session.getAttribute("user");
		address.setUser(theUser);
		address.setDefaultAddress(false); //Improve code: Give user option to mark added address as default?
		address.setDateLastUsed(LocalDateTime.now());
	}
	

	
}


