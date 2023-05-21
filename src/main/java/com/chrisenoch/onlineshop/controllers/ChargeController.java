package com.chrisenoch.onlineshop.controllers;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chrisenoch.onlineshop.entity.Address;
import com.chrisenoch.onlineshop.entity.Checkout;
import com.chrisenoch.onlineshop.entity.DeliveryInformation;
import com.chrisenoch.onlineshop.entity.Order;
import com.chrisenoch.onlineshop.entity.StockReservedByUser;
import com.chrisenoch.onlineshop.entity.User;
import com.chrisenoch.onlineshop.payment.ChargeRequest;
import com.chrisenoch.onlineshop.payment.ChargeRequest.Currency;
import com.chrisenoch.onlineshop.service.AddressService;
import com.chrisenoch.onlineshop.service.CheckoutService;
import com.chrisenoch.onlineshop.service.OrderService;
import com.chrisenoch.onlineshop.service.StockReservedByUserService;
import com.chrisenoch.onlineshop.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@Controller
public class ChargeController {
 
    private StripeService paymentsService;
    private AddressService addressService;
    private OrderService orderService;
    private CheckoutService checkoutService;
    private StockReservedByUserService stockReservedByUserService; 

    @Autowired
    public ChargeController(AddressService addressService, OrderService orderService
    		, CheckoutService checkoutService, StripeService paymentsService,
    		StockReservedByUserService stockReservedByUserService) {
		this.addressService = addressService;
		this.orderService = orderService;
		this.checkoutService = checkoutService;
		this.paymentsService = paymentsService;
		this.stockReservedByUserService = stockReservedByUserService; 
	}
    
	/**
	 * Displays the result of a user payment.
	 * @param theModel
	 * @return the result of the payment action if undertaken or redirected to /shop if accessed directly.
	 */
	@GetMapping("/charge")
    public String chargeGet(Model theModel) {
		if (theModel.getAttribute("paymentSuccess") != null) {
			return "result";
		}
		else {
			//If page accessed directly do not show payment success/payment error page
			return "redirect:/shop";
		}
	}
    
	/**
	 * Processes the user payment using Stripe integration and saves the purchase information in the database.
	 * In case of success, the success message is displayed. In case of payment failure, the user is informed. In case the payment succeeds but there is an error upon saving the user's purchase
	 * , the user is instructed to contact the website administrator.
	 * @param chargeRequest
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @param session
	 * @return Redirects to the page where success or failure message is rendered.
	 * @throws StripeException
	 */
	@PostMapping("/charge")
    public String charge(ChargeRequest chargeRequest, Model model ,RedirectAttributes redirectAttributes
    		, HttpServletRequest request, HttpSession session) throws StripeException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(Currency.EUR);
        Charge charge = paymentsService.charge(chargeRequest);
        
        int amount = chargeRequest.getAmount();        
      
     if (charge.getStatus().equals("succeeded")){
    	 	
    	 try {
     	 	int addressId = Integer.parseInt(request.getParameter("addressId"));
     	 	int orderId = Integer.parseInt(request.getParameter("orderId"));
     	 	double shippingCost = Double.parseDouble(request.getParameter("shippingCost"));
     	 	
     	    LocalDateTime shipDate = getNextWorkingDay();
     	    
     	    //Instantiate objects to be used in Checkout constructor
     	    Address theAddress = addressService.getAddress(addressId);	
     	    Order theOrder = orderService.getOrder(orderId);
     	    LocalDateTime predictedArrivalDate = shipDate.plusDays(3);   
     	    DeliveryInformation theDeliveryInformation = new DeliveryInformation
     	    		(shipDate, predictedArrivalDate, false, theAddress);
     	      
     	    
     	    Checkout checkout = new Checkout(amount,shippingCost, theOrder, theDeliveryInformation, charge.getId());
     	    
     	    //Save Checkout object (which also sets isProcessed to true in orders table).
     	    checkoutService.save(checkout);
     	    
     	    //delete stock reserved for this user
     	    User theUser = (User) session.getAttribute("user");
     	    stockReservedByUserService.deleteStockReservedByUser(theUser);
	  
     	    redirectAttributes.addFlashAttribute("paymentSuccess", "paymentSucceeded");
     	    
     	   return "redirect:/charge"; 
     	   
    	 } catch (Exception exc){
    		 redirectAttributes.addFlashAttribute("paymentSuccess", "paymentSucceededButCheckoutNotSaved");
    		 System.out.println(exc.getMessage());
    		 System.out.println(exc.getStackTrace());
    		 //To do: Notify admin. If a user has paid and order does not get stored in database, user will not receive order
    		 //To do: Email the user id and the order details from Stripe.

    		 return "redirect:/charge"; 
    	 } 
 
    	    
     } else {
    	 redirectAttributes.addFlashAttribute("paymentSuccess", "paymentFailed");
       	 	return "redirect:/charge"; 
     }    
  }
 
    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex, HttpSession session) throws Exception {
       model.addAttribute("paymentSuccess", "paymentFailed");
       User theUser = (User)session.getAttribute("user");

		//retrieve stockReservedByUser 
		List<StockReservedByUser> stockReservedByUser = stockReservedByUserService.getStockReservedByUser(theUser);
		
		//loop through, update stock, and delete the StockReservedByUser objects
		for (StockReservedByUser stock : stockReservedByUser) {
			stockReservedByUserService.shiftStockFromStockReservedByUserToProduct(stock);
		
		}

        return "result";
    }
    
	private static LocalDateTime getNextWorkingDay() {
    	LocalDateTime localDateTime = LocalDateTime.now();
    	LocalTime localTime = LocalTime.of(10, 00);
    	if ((localDateTime.getDayOfWeek() != DayOfWeek.SATURDAY 
    			|| localDateTime.getDayOfWeek() != DayOfWeek.SUNDAY) && 
    			localDateTime.toLocalTime().compareTo(localTime) < 0) {
    	return LocalDateTime.now();
    	
    	} else if (localDateTime.getDayOfWeek() != DayOfWeek.FRIDAY 
    			|| localDateTime.getDayOfWeek() != DayOfWeek.SATURDAY) {

    		
    		return localDateTime.plusDays(1);//return tomorrow
    		
    	} else {
    		return localDateTime.with(TemporalAdjusters.next(DayOfWeek.MONDAY)); //date of coming Monday
    	}  	
    }
       
}