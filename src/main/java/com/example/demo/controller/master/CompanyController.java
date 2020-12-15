package com.example.demo.controller.master;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompanyController {
	
	@PreAuthorize("hasAnyAuthority('COMPANY_READ_PRIVILEGE')")
	@GetMapping("/companies")
    public String companies(){
        return "companies";
    }

}
