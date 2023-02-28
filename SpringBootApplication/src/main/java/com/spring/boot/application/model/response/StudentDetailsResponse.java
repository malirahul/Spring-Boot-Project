package com.spring.boot.application.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetailsResponse implements Serializable {
	private static final long serialVersionUID = 3L;
	private String firstName;
	private String lastName;
	private String enrollment;
	private String email;
	private String mobileNo;
	private int age;
	private String city;
	
	

}
