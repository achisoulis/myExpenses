package com.project.myExpenses.web;


import com.project.myExpenses.model.User;
import com.project.myExpenses.web.dto.ExpenseDto;
import com.project.myExpenses.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MainController {
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login/{id}")
	public String login_post(@PathVariable( value = "id") long id
							 ) {
		return "login/{id}";
	}
	
	@GetMapping("/index")
	public String home() {
		return "index";
	}

	@GetMapping("/about")
	public String about() {
		return "about";
	}


	@GetMapping("calculate_total")
	public  String calculate_total() { return "calculate_total";
	}


	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}



	@ModelAttribute("expense")
	public ExpenseDto saveExpense_post() {
		return new ExpenseDto();
	}



}
