package com.example.demo.controller.master;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StateController {
	
	@PreAuthorize("hasAnyAuthority('STATE_READ_PRIVILEGE')")
	@GetMapping("/states")
    public String states(){
        return "states";
    }

}
