package com.project.myExpenses.service;

import com.project.myExpenses.model.User;
import com.project.myExpenses.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{


	User findUserByEmail(String email);

	boolean emailExists(String email);

	Iterable<User> findAllUsers();

	User save(UserRegistrationDto registrationDto);


}
