package com.example.demo.controller.master;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChapterController {
	
	
	@PreAuthorize("hasAnyAuthority('CHAPTER_READ_PRIVILEGE')")
	@GetMapping("/chapters")
    public String chapters(){
        return "chapters";
    }

}
