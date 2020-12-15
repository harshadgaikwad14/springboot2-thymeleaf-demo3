package com.example.demo.controller.master;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SubjectController {
	
	
	@PreAuthorize("hasAnyAuthority('SUBJECT_READ_PRIVILEGE')")
	@GetMapping("/subjects")
    public String cities(){
        return "subjects";
    }

}
