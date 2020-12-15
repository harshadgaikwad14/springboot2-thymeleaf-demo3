package com.example.demo.controller.master;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AcademicYearController {
	
	
	@PreAuthorize("hasAnyAuthority('ACADEMICYEAR_READ_PRIVILEGE')")
	@GetMapping("/academic-years")
    public String academicYears(){
        return "master/academicyear/academicyears";
    }

}
