package com.objective.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.objective.payload.AllMenu;
import com.objective.services.MenuService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {

	@Autowired
	MenuService menuService;
	
	@GetMapping
	public AllMenu getList(@RequestParam(defaultValue = "DR") String app) {
		
		return menuService.getList(app);
		
	}
	
	@GetMapping("/cache-clear")
	public String cacheClear(@RequestParam(defaultValue = "DR") String app) {
		
		menuService.clearCache(app);
		return "SUCCESS";
		
	}
}
