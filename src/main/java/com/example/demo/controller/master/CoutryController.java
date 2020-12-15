package com.example.demo.controller.master;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoutryController {
	
	@PreAuthorize("hasAnyAuthority('COUNTRY_READ_PRIVILEGE')")
	@GetMapping("/countries")
    public String countries(){
        return "countries";
    }

}
