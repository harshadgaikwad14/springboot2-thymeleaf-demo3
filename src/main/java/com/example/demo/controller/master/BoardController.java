package com.example.demo.controller.master;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	@PreAuthorize("hasAnyAuthority('BOARD_READ_PRIVILEGE')")
	@GetMapping("/boards")
    public String getBoards(){
        return "master/board/boards";
    }

}
