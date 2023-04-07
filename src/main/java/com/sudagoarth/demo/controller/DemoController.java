package com.sudagoarth.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
	
	@GetMapping("/home")
	public String sayHello(Model theModel) {
		
		theModel.addAttribute("name", "Asim Abdelgadir");
		
		return "home";
	}
}








