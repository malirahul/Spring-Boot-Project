package com.spring.boot.application.entity;

import jakarta.persistence.*;
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
import org.springframework.format.annotation.NumberFormat;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "student")
public class StudentEntity implements Serializable {
	private static final long serialVersionUID = 5L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentId;

	@NotNull(message = "First Name shouldn't be null")
	private String firstName;

	@NotNull(message = "Last Name shouldn't be null")
	private String lastName;
	
	@NotNull(message = "Enrollment Should't be null")
	private String enrollment; 

	@Email(message = "invalid email address")
	private String email;

	@NumberFormat
	@Pattern(regexp = "(^$|[0-9]{10})",message = "invalid mobile Number")
	private String mobileNo;

	@Min(18)
	@Max(60)
	private int age;

	@NotBlank
	private String city;

	@NotNull(message = "Password shouldn't be null")
	private String password;

	private String roles;

}
