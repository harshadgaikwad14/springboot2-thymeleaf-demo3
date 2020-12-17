package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {

    @GetMapping("index")
    public String index(){
    	
    	System.out.println("********** AdminController - index");
    	final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String n = auth.getName();
		String r = auth.getAuthorities().toString();

		System.out.println("the value of username is " + n);
		System.out.println("the value of role is  " + r);
        return "admin/index";
    }
}
