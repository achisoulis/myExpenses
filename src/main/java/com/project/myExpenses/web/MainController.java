package com.project.myExpenses.web;


import com.project.myExpenses.model.User;
import com.project.myExpenses.web.dto.ExpenseDto;
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

	@PostMapping("/login")
	public String login_post() {
		return "login";
	}
	
	@GetMapping("/index")
	public String home() {
		return "index";
	}

	@GetMapping("/about")
	public String about() {
		return "about";
	}

//	@GetMapping("/expenses")
//	public  String listStudent() { return  "expenses";}
//
//	@PostMapping("/expenses")
//	public  String saveExpense() { return  "expenses";}


//	@GetMapping("/expenses/new")
//	public  String createExpenseForm() { return  "create_expense";}
//
//
//	@PostMapping("/expenses/new")
//	public  String expensesNew_post() { return  "create_expense";}
//
//	@GetMapping("/calendar")
//	public  String get_calendar() { return "calendar";}
//
//	@PostMapping("/calendar")
//	public  String post_calendar() { return "calendar";}

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

//	@ModelAttribute("expense")
//	public ExpenseDto saveExpense_post() {
//		return new ExpenseDto();

//	@ModelAttribute("expense")
//	public ExpenseDto saveExpense() {
//		return new ExpenseDto();
//	}

	@ModelAttribute("expense")
	public ExpenseDto saveExpense_post() {
		return new ExpenseDto();
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
