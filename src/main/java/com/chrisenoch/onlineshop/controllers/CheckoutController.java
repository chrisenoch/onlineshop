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
import com.chrisenoch.onlineshop.entity.OrderContents;
import com.chrisenoch.onlineshop.entity.Product;
import com.chrisenoch.onlineshop.entity.StockReservedByUser;
import com.chrisenoch.onlineshop.entity.StockUtil;
import com.chrisenoch.onlineshop.entity.User;
import com.chrisenoch.onlineshop.payment.ChargeRequest;
import com.chrisenoch.onlineshop.service.AddressService;
import com.chrisenoch.onlineshop.service.OrderContentsService;
import com.chrisenoch.onlineshop.service.OrderService;
import com.chrisenoch.onlineshop.service.ProductService;
import com.chrisenoch.onlineshop.service.StockReservedByUserService;

@Controller
public class CheckoutController {
	
	private OrderService orderService;
	private AddressService addressService;
	private OrderContentsService orderContentsService;
	private ProductService productService;
	private StockReservedByUserService stockReservedByUserService; 
 
    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @Autowired
    public CheckoutController(OrderService orderService, AddressService addressService,
    		OrderContentsService orderContentsService, ProductService productService,
    		StockReservedByUserService stockReservedByUserService  ) {
		this.orderService = orderService;
		this.addressService = addressService;
		this.orderContentsService = orderContentsService;
		this.productService = productService;
		this.stockReservedByUserService = stockReservedByUserService;
	}

	@GetMapping("/checkout")  
    public String checkout(HttpServletRequest request, Model model, HttpSession session
    		, RedirectAttributes redirectAttributes) throws Exception { 
		
		User theUser = (User)session.getAttribute("user");
		int userId = (int)session.getAttribute("userId");
		Order theOrder = orderService.getCorrectOrder(theUser, userId);
		List<OrderContents> orderContents = orderContentsService.getOrderContents(theOrder);
		
		StockUtil stockUtil = new StockUtil(theOrder);	
		List<StockReservedByUser> stockReservedByUsers = stockReservedByUserService.getStockReservedByUser(theUser);
		stockReservedByUsers.stream().forEach(t -> {
			try {
				stockReservedByUserService.shiftStockFromStockReservedByUserToProduct(t);
			} catch (Exception e) {
				
				e.printStackTrace();
				//to do, log exception
				
			}
		});

		Map<Product, Map<Integer, Integer>> comparedOrderContentsWithStock = stockUtil.compareOrderContentsWithStock();
		Map<Product, Map<Integer, Integer>> updatedOrderContentsAccordingToStock =
				stockUtil.updateOrderContentsAccordingToStock(comparedOrderContentsWithStock); 
		
		//If stock isn't available for all orderContents, then map should not be empty
		if (!updatedOrderContentsAccordingToStock.isEmpty()) {
			//logic for when the stock is less than requested amount
			
			assignNewOrderContentsAccordingToStock(theOrder, updatedOrderContentsAccordingToStock);
			
			//Summary of changes made to order-> this is checked for and displayed in jsp page
			redirectAttributes.addFlashAttribute("updatedOrderContentsDueToStockShortage", updatedOrderContentsAccordingToStock);
			
			return "redirect:shop/basket";
					
		}  
		
		System.out.println("debugging is orderContents null no probs stock " + theOrder.getOrderContents());
		
		//Get total price
		setPaymentAndPriceDetails(session, model, orderService);
		
		//Temporarily hold the selected stock for the user until the user completes/cancels the payment	
		//For each OrderContents shiftStockFromProductToStockReservedByUser
		 stockReservedByUserService.shiftStockFromProductToStockReservedByUserByOrderContents(theUser, orderContents);
        
        //Get default address if there is one. If not get last address used. If not, do not set model attribute
		//and user automatically redirected to manageaddresses in order to add address
        List<Address> addresses = addressService.getAllAddresses(userId);

        getPreferredAddress(addresses, addressService, model);
        
        return "checkout";
   	
    }
       // model.addAttribute("currency", ChargeRequest.Currency.EUR);

	@PostMapping("/checkoutchanged")
    public String checkoutChanged(Model model,
    		HttpSession session, @ModelAttribute("address")Address theAddress) throws Exception { 
		 	
		System.out.println("debug checkoutcontroller checkoutchanged");
		
		int userId = setPaymentAndPriceDetails(session, model, orderService);
		
		//get address by addressid
		int id = theAddress.getId();
		Address addressToAdd = addressService.getAddress(id);
		addressToAdd.setDateLastUsed(LocalDateTime.now());
		addressService.save(addressToAdd); //save address with updated date so next time user is at checkout getPreferredAddress method can fetch address based on last address used.
		
		model.addAttribute("address", addressToAdd);

        return "checkout";
   	
 	}
    
	private int setPaymentAndPriceDetails (HttpSession session, Model model //Improve code: Rename method
			, OrderService orderService) throws Exception {
		User theUser = (User)session.getAttribute("user");
		int userId = (int)session.getAttribute("userId");
		Order theOrder = orderService.getCorrectOrder(theUser, userId);
		double shippingCost = theOrder.shippingCost(theOrder.getOrderContentsTotal());
		//Get order id from session - If user visits basket page before shop page, there may not be an order session available
		
		int totalOrderContentsPriceInCents = orderContentsService.totalOrderContentsPrice(theOrder); 
		int totalOrderPriceInCents = (int) (totalOrderContentsPriceInCents + (shippingCost * 100));
		
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
        model.addAttribute("orderContents", theOrder.getOrderContents().size()); //If null, user gets a message saying basket is empty in checkout.jsp
        
        return userId;
	}
	
	
	private void assignNewOrderContentsAccordingToStock(Order theOrder,
			Map<Product, Map<Integer, Integer>> updatedOrderContentsAccordingToStock) {
		theOrder.getOrderContents().clear(); //remove associations so orderContents won't be resaved by cascade
		
		//only delete orderContents if they have stock problems			
		for (Entry<Product, Map<Integer, Integer>>  map : updatedOrderContentsAccordingToStock.entrySet()) {
			orderContentsService.delete(map.getKey());
		}	

		//loop over map and add new order contents to database		
		for ( Entry<Product, Map<Integer, Integer>> map : updatedOrderContentsAccordingToStock.entrySet()) {
			Product product = (Product) map.getKey();
			
			for ( Entry<Integer, Integer> map2 : map.getValue().entrySet()) {
				//If available stock is zero, do not save new OrderContents in database
				if (map2.getValue() < 1) {
					continue;
				}
				
				OrderContents oC = new OrderContents(theOrder, product, map2.getValue());
				orderContentsService.save(oC);
				System.out.println("debugging is orderContents null inside loop" + theOrder.getOrderContents());
			}				
		}
		
		//Reassign orderContents to Order object
		List<OrderContents> updatedOrderContents = orderContentsService.getOrderContents(theOrder);
		theOrder.setOrderContents(updatedOrderContents);
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



