package com.example.demo.controller.master;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentsController {
	
	
	@PreAuthorize("hasAnyAuthority('STUDENT_READ_PRIVILEGE')")
	@GetMapping("/students")
    public String students(){
        return "institute/student/students";
    }
	
	@PreAuthorize("hasAnyAuthority('STUDENT_READ_PRIVILEGE')")
	@GetMapping("/student-dashbaord")
    public String studentDashboard(){
        return "studentDashboard";
    }

}
