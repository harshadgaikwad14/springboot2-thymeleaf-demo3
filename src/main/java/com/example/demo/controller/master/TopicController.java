package com.example.demo.controller.master;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopicController {
	
	
	@PreAuthorize("hasAnyAuthority('TOPIC_READ_PRIVILEGE')")
	@GetMapping("/topics")
    public String chapters(){
        return "topics";
    }

}
