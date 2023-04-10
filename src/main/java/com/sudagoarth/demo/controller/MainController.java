package com.sudagoarth.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/home")
	public String home(Model theModel) {
		theModel.addAttribute("home", "Home Page");
		return "home";
	}
}








