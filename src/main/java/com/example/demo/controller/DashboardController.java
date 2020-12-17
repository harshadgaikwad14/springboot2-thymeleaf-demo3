package com.example.demo.controller;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
	
	
	 @GetMapping("/dashboard")
    public String index(){
    	
    	System.out.println("********** AdminController - index");
    	final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String n = auth.getName();
		String r = auth.getAuthorities().toString();

		System.out.println("the value of username is " + n);
		System.out.println("the value of role is  " + r);
		
		if(r.contains("ROLE_STUDENT"))
		{
			return "redirect:/student-dashbaord";
		}
		else if(r.contains("ROLE_TEACHER"))
		{
			return "redirect:/teacher-dashbaord";
		}

		
        return "index";
    }
}
