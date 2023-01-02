package com.project.myExpenses.web;


import com.project.myExpenses.model.User;
import com.project.myExpenses.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MainController {
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String home() {
		return "index";
	}

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

/*
//	@PostMapping("/")
	public String login(@Valid User user, Errors errors, Model model) {
		if (errors.hasErrors()) {
			return "login";
		} else {
			model.addAttribute("message", "Guest login successful ...");
			return "login";
		}
	}
*/
}
