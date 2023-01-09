package com.project.myExpenses.web;


import com.project.myExpenses.model.User;
import com.project.myExpenses.validator.EmailVConstraintvalidator;
import com.project.myExpenses.validator.ValidEmail;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.project.myExpenses.service.UserService;
import com.project.myExpenses.web.dto.UserRegistrationDto;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {


	private UserService userService;
	private EmailVConstraintvalidator emailValidation;


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
//    @ModelAttribute("user")
	public String registerUserAccount(@ModelAttribute("user")
			@Valid UserRegistrationDto registrationDto, Errors errors, Model model) {

		registrationDto.getEmail();

		//		Pattern pattern_email;
//		Matcher matcher_email;
//		Pattern pattern_password;
//		Matcher matcher_password;
//
//		Pattern pattern_username;
//		Matcher matcher_username;
//
//
//		final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]" + "(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
//		// digit + lowercase char + uppercase char + punctuation + symbol
//		 final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
//
//		pattern_email = Pattern.compile(EMAIL_PATTERN);
//		matcher_email = pattern_email.matcher(registrationDto.getEmail());
//		if (matcher_email.matches() == true) {
//			userService.save(registrationDto);
//
//			return "redirect:/registration?success"; }
//		 else
//
//			return "redirect:/registration?fail";

		if (null != errors && errors.getErrorCount() > 0) {

			return "registration";
		} else {
			model.addAttribute("successMsg", "Details saved successfully!!");
			return "redirect:/registration?success";
		}
	}
}


//	// handler method to handle user registration form submit request
//	@PostMapping("/register/save")
//	public String registration(@Valid @ModelAttribute("user") UserRegistrationDto userRegistrationDto,
//							   BindingResult result,
//							   Model model) {
//		User existingUser = userService.findUserByEmail(userRegistrationDto.getEmail());
//
//		if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
//			result.rejectValue("email", null,
//					"There is already an account registered with the same email");
//		}
//
//		if (result.hasErrors()) {
//			model.addAttribute("user", userRegistrationDto());
//			return "/register";
//		}
//
//
//
//		userService.save(userRegistrationDto());
//		return "redirect:/register?success";
//	}



