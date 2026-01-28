package com.fabian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fabian.service.IDepasService;

@Controller
public class HomeController {
	
	@Autowired
	private IDepasService ServiceDepa;
	
	@GetMapping("/")
	public String mostarDepas(Model model) {
		model.addAttribute("depas",ServiceDepa.buscarTodas());
		
		return "home";
	}
	
	

	
	
	
}
