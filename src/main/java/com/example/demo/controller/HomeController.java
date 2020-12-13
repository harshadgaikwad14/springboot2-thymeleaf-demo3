package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	@GetMapping("index")
	public String index() {
		return "index";
	}

	@GetMapping("login")
	public String login() {
		return "login";
	}

	@GetMapping("adminonly")
	public String adminonly() {
		return "index";
	}

	@PreAuthorize("hasAnyAuthority('ACCESS_READ')")
	@GetMapping("read")
	public String read() {
		return "read";
	}
	@PreAuthorize("hasAnyAuthority('ACCESS_WRITE')")
	@GetMapping("write")
	public String write() {
		return "write";
	}
	@PreAuthorize("hasAnyAuthority('ACCESS_DELETE')")
	@GetMapping("delete")
	public String delete() {
		return "delete";
	}

	@GetMapping("/access-denied")
	public String accessDenied() {
		return "error/access-denied";
	}
}
