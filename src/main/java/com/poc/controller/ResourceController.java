package com.poc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Une classe pour exposer ou tester les API REST avec un rôle

@RestController
@RequestMapping(value = "/api/v1")
public class ResourceController {
	
	// Test autorisation USER
	@RequestMapping("/hellouser")
	public String getUser()
	{
		return "Hello User";
	}
	
	// Test autorisation ADMIN
	@RequestMapping("/helloadmin")
	public String getAdmin()
	{
		return "Hello Admin";
	}

}
