package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.LoginData;
import com.example.demo.entities.Songs;
import com.example.demo.entities.Users;
import com.example.demo.services.SongsService;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;
@CrossOrigin("*")
@RestController
public class UsersController 
{
	@Autowired
	UsersService userv;
	
	@Autowired
	SongsService songserv;

	@PostMapping("/register")
	public String addUser(@RequestBody Users user) {
		boolean userstatus = userv.emailExists(user.getEmail());
		if(userstatus == false) {
			userv.addUser(user);
			return "login";
		}
		else
		{
			return "loginfail";
		}
	}

	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> validateUser(@RequestBody LoginData data, HttpSession session) {
	    String email = data.getEmail();
	    String role = userv.getRole(email);

	    if (userv.validateUser(email, data.getPassword())) {
	        session.setAttribute("email", email);
	        Map<String, String> response = new HashMap<>();
	        response.put("role", role);
	        response.put("email", email);
	        return ResponseEntity.ok(response);
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
	}

	
	
	@PostMapping("/exploreSongs")
	public String exploreSongs(@RequestBody LoginData data) {
			Users user = userv.getUser(data.getEmail());
			
			boolean userStatus = user.isPremium();
			if(userStatus == true) {
				return "displaysongs";
			}
			else {
				return "payment";
			}
	}
}
















