package com.chrisenoch.onlineshop.controllers;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chrisenoch.onlineshop.entity.Address;
import com.chrisenoch.onlineshop.entity.Order;
import com.chrisenoch.onlineshop.entity.LineItem;
import com.chrisenoch.onlineshop.entity.Product;
import com.chrisenoch.onlineshop.entity.StockReservedByUser;
import com.chrisenoch.onlineshop.entity.StockUtil;
import com.chrisenoch.onlineshop.entity.User;
import com.chrisenoch.onlineshop.payment.ChargeRequest;
import com.chrisenoch.onlineshop.service.AddressService;
import com.chrisenoch.onlineshop.service.LineItemService;
import com.chrisenoch.onlineshop.service.OrderService;
import com.chrisenoch.onlineshop.service.ProductService;
import com.chrisenoch.onlineshop.service.StockReservedByUserService;

@Controller
public class CheckoutController {
	
	private OrderService orderService;
	private AddressService addressService;
	private LineItemService lineItemsService;
	private ProductService productService;
	private StockReservedByUserService stockReservedByUserService; 
 
    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @Autowired
    public CheckoutController(OrderService orderService, AddressService addressService,
    		LineItemService lineItemsService, ProductService productService,
    		StockReservedByUserService stockReservedByUserService  ) {
		this.orderService = orderService;
		this.addressService = addressService;
		this.lineItemsService = lineItemsService;
		this.productService = productService;
		this.stockReservedByUserService = stockReservedByUserService;
	}

	@GetMapping("/checkout")  
    public String checkout(HttpServletRequest request, Model model, HttpSession session
    		, RedirectAttributes redirectAttributes) throws Exception { 
		
		User theUser = (User)session.getAttribute("user");
		int userId = (int)session.getAttribute("userId");
		Order theOrder = orderService.getCorrectOrder(theUser, userId);
		List<LineItem> lineItems = lineItemsService.getLineItems(theOrder);
		
		
		//Move from StockReservedByUser back to product because the user's basket may have changed since the last time this page was accessed.
		StockUtil stockUtil = new StockUtil(theOrder);	
		List<StockReservedByUser> stockReservedByUser = stockReservedByUserService.getStockReservedByUser(theUser);
		stockReservedByUser.stream().forEach(t -> {
			try {
				stockReservedByUserService.shiftStockFromStockReservedByUserToProduct(t);
			} catch (Exception e) {
				
				e.printStackTrace();
				//to do, log exception
				
			}
		});

		Map<Product, Map<Integer, Integer>> comparedLineItemsWithStock = stockUtil.compareLineItemsWithStock();
		Map<Product, Map<Integer, Integer>> lineItemAndStockDiscrepancies =
				stockUtil.getLineItemAndStockDiscrepancies(comparedLineItemsWithStock); 
		
		//If stock isn't available for all line items, then map should not be empty
		if (!lineItemAndStockDiscrepancies.isEmpty()) {
			//logic for when the stock is less than requested amount
			
			stockReservedByUserService.assignNewLineItemsAccordingToStock(theOrder, lineItemAndStockDiscrepancies);
			
			//Summary of changes made to order-> this is checked for and displayed in basket.jsp page
			redirectAttributes.addFlashAttribute("updatedLineItemsDueToStockShortage", lineItemAndStockDiscrepancies);
			
			return "redirect:/shop/basket";
					
		}  

		//Get total price
		setPaymentAndPriceDetails(session, model, orderService);
		
		//Temporarily hold the selected stock for the user until the user completes/cancels the payment/logs out.
		//For each line item shiftStockFromProductToStockReservedByUser
		 stockReservedByUserService.shiftStockFromProductToStockReservedByUserForEachLineItem(theUser, lineItems);
        
        List<Address> addresses = addressService.getAllAddresses(userId);

        //Get default address if there is one. If not get last address used. If not, do not set model attribute
		//and user automatically redirected to manageaddresses in order to add address
        getPreferredAddress(addresses, addressService, model);
        
        
        return "checkout";
   	
    }

	@PostMapping("/checkout")
    public String checkoutChanged(Model model,
    		HttpSession session, @ModelAttribute("address")Address theAddress) throws Exception { 
		
		int userId = setPaymentAndPriceDetails(session, model, orderService);
		
		int id = theAddress.getId();
		Address addressToAdd = addressService.getAddress(id);
		addressToAdd.setDateLastUsed(LocalDateTime.now());
		addressService.save(addressToAdd); //Save address with updated date so next time user is at checkout getPreferredAddress method can fetch address based on last address used.
		
		model.addAttribute("address", addressToAdd);

        return "checkout";
   	
 	}
    
	private int setPaymentAndPriceDetails (HttpSession session, Model model 
			, OrderService orderService) throws Exception {
		User theUser = (User)session.getAttribute("user");
		int userId = (int)session.getAttribute("userId");
		
		//Get Order - If user visits basket page before shop page, there may not be an order session available
		Order theOrder = orderService.getCorrectOrder(theUser, userId);
		double shippingCost = theOrder.shippingCost(theOrder.getLineItemsTotal());
		
		int totalLineItemsPriceInCents = lineItemsService.totalLineItemsPrice(theOrder); 
		int totalOrderPriceInCents = (int) (totalLineItemsPriceInCents + (shippingCost * 100));
		
		int orderId = theOrder.getId();
		double totalOrderPrice = totalOrderPriceInCents;
		totalOrderPrice = totalOrderPriceInCents /100.0;
		String totalOrderPriceFormatted = String.format("%.2f", totalOrderPrice);

		model.addAttribute("currency", ChargeRequest.Currency.EUR); 
        model.addAttribute("amount", totalOrderPriceInCents); // in cents
        model.addAttribute("amountFormatted", totalOrderPriceFormatted);
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("orderId", orderId); //Add so they can be sent in hidden form to ChargeController to create Checkout object
        model.addAttribute("shippingCost", shippingCost); //Add so they can be sent in hidden form to ChargeController to create Checkout object
        model.addAttribute("lineItems", theOrder.getLineItems().size()); //If null, user gets a message saying basket is empty in checkout.jsp
        
        return userId;
	}
	
	
	 private void getPreferredAddress(List<Address> addresses, AddressService addressService, Model model) {
     	int addressListSize = addresses.size();
         if (addressListSize == 0){
         	return; //User will be instructed to add an address
         } else if (addressListSize == 1) {
         	model.addAttribute("address", addresses.get(0));
         	return;
         } else {
         	//find default address 
         	
         	for (Address address : addresses) {
         		if (address.getDefaultAddress()==true) {
         			model.addAttribute("address", address);
         			return;
         		}
         	}    	
         	
         	//If got to this point, no default address exists, so return the last address that the user used
         	//Collections.sort(addresses, (a1, a2) -> a1.getDateLastUsed().compareTo(a2.getDateLastUsed()));
         	addresses.sort(Comparator.comparing(Address::getDateLastUsed));
         	
         	// find by most recent 
         	model.addAttribute("address", addresses.get(addresses.size() - 1)); //get address by most recent date
         	return;
     	
         }
	
	 }
	 
}



