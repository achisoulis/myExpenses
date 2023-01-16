package com.project.myExpenses.web;


import com.project.myExpenses.model.User;
import com.project.myExpenses.validator.EmailVConstraintvalidator;
import com.project.myExpenses.validator.ValidEmail;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.project.myExpenses.service.UserService;
import com.project.myExpenses.web.dto.UserRegistrationDto;

import javax.naming.Binding;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {


	private UserService userService;


	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}


	@PostMapping
	// Registration form of User
	public String registerUserAccount(@ModelAttribute("user")
										  @Valid UserRegistrationDto registrationDto,
									  Errors errors,
									  Model model,
									  BindingResult result)
	{
//		if (registrationDto.getEmail().isEmpty())
//		{	result.rejectValue("email" , null,"Email Cannot be Empty");
//		return  "registration"; }
//
//
//		if (registrationDto.getEmail().isEmpty())
//		{	result.rejectValue("firstName" , null,"First Name Cannot be Empty");
//		return  "registration"; }
//
//		if (registrationDto.getEmail().isEmpty())
//		{	result.rejectValue("lastName" , null,"Last Name Cannot be Empty");
//		return  "registration"; }
//
//
//		if (registrationDto.getEmail().isEmpty())
//		{	result.rejectValue("password" , null,"Password Cannot be Empty");
//		return  "registration"; }


		 User existingUser = userService.findUserByEmail(registrationDto.getEmail());

		if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
			result.rejectValue("email", null,
					"There is already an account registered with the same email");
			return  "registration";
		}


		if (null != errors && errors.getErrorCount() > 0) {

			return "registration";
		} else
		{
			userService.save(registrationDto);
//			model.addAttribute("successMsg", "Details saved successfully!!");

			return "success";
		}


	}
}



