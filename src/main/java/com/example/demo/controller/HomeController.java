package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	@GetMapping("index")
	public String index() {
		return "index";
	}

	@GetMapping("login")
	public String login() {
		return "login";
	}

	@GetMapping("adminonly")
	public String adminonly() {
		return "index";
	}

	@GetMapping("/access-denied")
	public String accessDenied() {
		return "error/access-denied";
	}
}
