package com.project.myExpenses.web;


import com.project.myExpenses.model.User;
import com.project.myExpenses.validator.EmailVConstraintvalidator;
import com.project.myExpenses.validator.ValidEmail;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import com.project.myExpenses.service.UserService;
import com.project.myExpenses.web.dto.UserRegistrationDto;
import javax.validation.Valid;


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
			model.addAttribute("successMsg", "You have registered successfully!!");

			return "success";
		}


	}
}



