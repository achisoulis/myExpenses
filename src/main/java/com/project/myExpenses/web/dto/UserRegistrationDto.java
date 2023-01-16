package com.project.myExpenses.web.dto;

import com.project.myExpenses.validator.ValidEmail;
import com.sun.istack.internal.NotNull;
import lombok.Data;
import org.springframework.security.core.parameters.P;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class UserRegistrationDto {


	@NotNull
	@Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{7,29}$",
			message = "username must be of 6 to 12 length with no special characters")
	private String firstName;


	//^[a-zA-Z0-9]
	@NotNull
	@Pattern(regexp = "^[a-zA-Z0-9]{6,12}$",
			message = "username must be of 6 to 12 length with no special characters")
	@NotEmpty
	private String lastName;

	@NotNull()
	@Pattern(regexp = "^[_A-Za-z0-9-+]" +"(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$",
	message = "Email not valid")
	@NotEmpty
	private String email;

	@NotNull
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
			message = "password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
	@NotEmpty
	private String password;
	
	public UserRegistrationDto(){
		
	}
	
	public UserRegistrationDto(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
