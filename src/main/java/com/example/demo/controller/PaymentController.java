package com.example.demo.controller;

import java.net.http.HttpRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.LoginData;
import com.example.demo.entities.PaymentDTO;
import com.example.demo.entities.Users;
import com.example.demo.services.UsersService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import jakarta.servlet.http.HttpSession;
@CrossOrigin("*")
@RestController
public class PaymentController {
	@Autowired
	UsersService service;
	
	@PostMapping("/createOrder")
	@ResponseBody
	public String createOrder() {
		Order order = null;
		try {
			RazorpayClient razorpay = new RazorpayClient("rzp_test_3HOkvAEOeNz4YC", 
					"HFJFoLO7BydjSlZMKifxATBQ");

			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount",50000);
			orderRequest.put("currency","INR");
			orderRequest.put("receipt", "receipt#1");
			JSONObject notes = new JSONObject();
			notes.put("notes_key_1","Tea, Earl Grey, Hot");
			orderRequest.put("notes",notes);

		
			order = razorpay.orders.create(orderRequest);
		}
		catch(Exception e) {
			System.out.println("exception while crteating order");
		}
		return order.toString();
	}
	
	@PostMapping("/verify")
	@ResponseBody
	public boolean verifyPayment(@RequestBody PaymentDTO paymentDTO) {
	    try {
	        // Initialize Razorpay client with your API key and secret
	        @SuppressWarnings("unused")
			RazorpayClient razorpayClient = new RazorpayClient("rzp_test_3HOkvAEOeNz4YC", 
					"HFJFoLO7BydjSlZMKifxATBQ");
	        // Create a signature verification data string
	        String verificationData = paymentDTO.getOrderId() + "|" + paymentDTO.getPaymentId();

	        // Use Razorpay's utility function to verify the signature
	        boolean isValidSignature = Utils.verifySignature(verificationData, paymentDTO.getSignature(), 
	        		"HFJFoLO7BydjSlZMKifxATBQ");

	        return isValidSignature;
	    } catch (RazorpayException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	
	@PostMapping("/payment-success")
	public String paymentSuccess(@RequestBody LoginData data) {
	    // Retrieve the user by email
	    Users user = service.getUser(data.getEmail());
	    
	    // Check if the user exists
	    if (user != null) {
	        // Set premium status to true
	        user.setPremium(true);
	        // Update the user's account
	        service.updateUser(user);
	        // Redirect to the login page or any other page as needed
	        return "login";
	    } else {
	        // Handle the case where the user is not found
	        // You can log an error message or return an appropriate response
	        return "User not found";
	    }
	}


	
	
	
	//payment-failure -> redirect to login 
	@GetMapping("payment-failure")
	public String paymentFailure(){
		//payment-error page
		return "login";
	}
	
}
