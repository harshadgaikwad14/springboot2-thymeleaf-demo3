package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("index")
	public String index(Principal principal) {
		System.out.println("********** HomeController - index");
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String n = auth.getName();
		String r = auth.getAuthorities().toString();

		System.out.println("the value of username is " + n);
		System.out.println("the value of role is  " + r);
		return "index";
	}

	@GetMapping("login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String getRegister(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "register";
	}

	@PostMapping("/register")
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {

		// userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "register";
		}

		System.out.println("userForm : " + userForm.getUserType());

		String role = "STUDENT";
		String privilges = "STUDENT_READ_PRIVILEGE";
		if ("Teacher".equals(userForm.getUserType())) {
			role = "TEACHER";
			privilges ="TEACHER_READ_PRIVILEGE";
		} else if ("Admin".equals(userForm.getUserType())) {
			role = "SUPERADMIN";
			privilges ="STUDENT_READ_PRIVILEGE,TEACHER_READ_PRIVILEGE";
		}

		User user = new User(userForm.getUsername(), passwordEncoder.encode(userForm.getPassword()), role, privilges);
		this.userRepository.save(user);

		// securityService.autoLogin(userForm.getUsername(),
		// passwordEncoder.encode(userForm.getPassword()));

		return "redirect:/login";
	}

	@GetMapping("adminonly")
	public String adminonly() {
		System.out.println("********** HomeController - adminonly");
		return "index";
	}

	@PreAuthorize("hasAnyAuthority('READ_PRIVILEGE')")
	@GetMapping("read")
	public String read() {
		return "read";
	}

	@PreAuthorize("hasAnyAuthority('UPDATE_PRIVILEGE')")
	@GetMapping("update")
	public String write() {
		return "update";
	}

	@PreAuthorize("hasAnyAuthority('CREATE_PRIVILEGE')")
	@GetMapping("create")
	public String create() {
		return "create";
	}

	@PreAuthorize("hasAnyAuthority('DELETE_PRIVILEGE')")
	@GetMapping("delete")
	public String delete() {
		return "delete";
	}

	@GetMapping("/access-denied")
	public String accessDenied() {
		return "error/access-denied";
	}
}
