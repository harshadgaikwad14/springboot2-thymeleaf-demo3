package com.example.demo.controller.master;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SubTopicController {
	
	
	@PreAuthorize("hasAnyAuthority('SUBTOPIC_READ_PRIVILEGE')")
	@GetMapping("/sub-topics")
    public String subtopics(){
        return "subtopics";
    }

}
