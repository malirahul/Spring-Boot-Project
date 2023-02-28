package com.spring.boot.application.model.request;

import jakarta.persistence.Access;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import org.springframework.format.annotation.NumberFormat;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDetailModelRequest implements Serializable {
	private static final long serialVersionUID = 2L;
	

	@NotBlank(message = "First Name shouldn't be null")
	private String firstName;

	@NotBlank(message = "Last Name shouldn't be null")
	private String lastName;
	

	@NotBlank(message = "invalid email address")
	private String email;

	@NumberFormat
	@Pattern(regexp = "(^$|[0-9]{10})",message = "invalid mobile Number")
	private String mobileNo;

	@Min(18)
	@Max(60)
	private int age;

	@NotBlank
	private String city;

	@NotBlank(message = "Password shouldn't be null")
	private String password;

	private String roles;
}
