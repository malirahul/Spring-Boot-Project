package com.spring.boot.application.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.spring.boot.application.dto.StudentDto;
import com.spring.boot.application.model.response.StudentDetailsResponse;

import java.util.List;

public interface StudentService extends UserDetailsService{

    List<StudentDetailsResponse> getAllDetails();

    StudentDetailsResponse getDetailById(Long id);

    void deleteStudentById(Long id);

    StudentDto updateDetail(String enrollment, StudentDto studentDto);
    
    //StduentDto getStudent(String email);


}
