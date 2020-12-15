package com.example.demo.controller.master;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeacherController {
	
	
	@PreAuthorize("hasAnyAuthority('TEACHER_READ_PRIVILEGE')")
	@GetMapping("/teachers")
    public String teachers(){
        return "institute/teacher/teachers";
    }

}
