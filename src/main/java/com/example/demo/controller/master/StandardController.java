package com.example.demo.controller.master;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StandardController {
	
	@PreAuthorize("hasAnyAuthority('STANDARD_READ_PRIVILEGE')")
	@GetMapping("/standards")
    public String standards(){
        return "standards";
    }

}
