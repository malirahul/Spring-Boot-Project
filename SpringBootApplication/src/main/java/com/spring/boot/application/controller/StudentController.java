package com.spring.boot.application.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.spring.boot.application.dto.AuthRequest;
import com.spring.boot.application.dto.StudentDto;
import com.spring.boot.application.entity.StudentEntity;
import com.spring.boot.application.exceptions.StudentServiceException;
import com.spring.boot.application.model.request.StudentDetailModelRequest;
import com.spring.boot.application.model.response.ErrorMessages;
import com.spring.boot.application.model.response.StudentDetailsResponse;
import com.spring.boot.application.repository.StudentRepository;
import com.spring.boot.application.service.JwtService;
import com.spring.boot.application.service.StudentService;
import com.spring.boot.application.shared.Utils;

import ch.qos.logback.classic.pattern.Util;
import jakarta.validation.Valid;

import java.util.List;
@RequestMapping()
@RestController
public class StudentController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	StudentService studentService;

	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	JwtService jwtService;
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	Utils utils;

	@PostMapping(value = "/student")
	public StudentDetailsResponse postStudentDetail(@Valid @RequestBody StudentDetailModelRequest studentDetailModelRequest)
	{
		StudentDetailsResponse returnValue = new StudentDetailsResponse();

		if(studentDetailModelRequest.getFirstName().isEmpty())
		{
			throw new StudentServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		}

		StudentDto studentDto = new StudentDto();
		StudentEntity student = new StudentEntity();

		BeanUtils.copyProperties(studentDetailModelRequest,studentDto);

		studentDto.setPassword(encoder.encode(studentDto.getPassword()));

		String enrollment = utils.generateUserId(10);

		studentDto.setEnrollment(enrollment);
		BeanUtils.copyProperties(studentDto, student);

		StudentEntity saved = studentRepository.save(student);

		BeanUtils.copyProperties(saved, returnValue);
		return returnValue;
	}

	@GetMapping("/students")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<StudentDetailsResponse>getAllDetails(){
		return studentService.getAllDetails();
	}

	@GetMapping("/student/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public StudentDetailsResponse getDetailById(@PathVariable (value = "id") Long id) {
		StudentDetailsResponse returnValue ;
		returnValue = studentService.getDetailById(id);
		return returnValue;
	}
	@PutMapping("/student/{id}")
	public StudentDetailsResponse updateDetail(@PathVariable String enrollment, @RequestBody StudentDetailModelRequest
														   studentDetailModelRequest){
		StudentDetailsResponse returnValue = new StudentDetailsResponse();
		StudentDto studentDto = new StudentDto();
		BeanUtils.copyProperties(studentDetailModelRequest, studentDto);

		StudentDto updateDetail = studentService.updateDetail(enrollment, studentDto);
		BeanUtils.copyProperties(updateDetail, returnValue);
		return returnValue;
	}

	@DeleteMapping("/student/{id}")
	public void deleteStudentById(@PathVariable(value = "id")Long id){
		studentService.deleteStudentById(id);
	}

	@PostMapping("/login")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest){
		Authentication authentication
				= authenticationManager
				.authenticate(new
						UsernamePasswordAuthenticationToken(
								authRequest.getEmail(),
						authRequest.getPassword()));

		if(authentication.isAuthenticated()){
			return jwtService.generateToken(authRequest.getEmail());
		}
		else {
			throw new UsernameNotFoundException("invalid student request");
		}
		//this allows to all to get the token,
		// but this is not a correct way
		// so, need to authenticate the user
		//return jwtService.generateToken(authRequest.getEmail());

	}
}
