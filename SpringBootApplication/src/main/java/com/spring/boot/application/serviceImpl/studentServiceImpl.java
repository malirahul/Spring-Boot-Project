package com.spring.boot.application.serviceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.boot.application.dto.StudentDto;
import com.spring.boot.application.entity.StudentEntity;
import com.spring.boot.application.model.response.StudentDetailsResponse;
import com.spring.boot.application.repository.StudentRepository;
import com.spring.boot.application.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class studentServiceImpl implements StudentService{
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public List<StudentDetailsResponse> getAllDetails() {
		List<StudentDetailsResponse> responseList  = new ArrayList<>();
		Iterable<StudentEntity>studentEntity = studentRepository.findAll();
		for (StudentEntity student : studentEntity) {
			StudentDetailsResponse studentDetailsResponse = new StudentDetailsResponse();
			BeanUtils.copyProperties(student, studentDetailsResponse);
			responseList.add(studentDetailsResponse);
		}
		for (int i=0;i<responseList.size();i++){
			System.out.println(responseList.get(i));
		}
		return responseList;
	}

	@Override
	public StudentDetailsResponse getDetailById(Long id) {
		StudentDetailsResponse returnValue = new StudentDetailsResponse();
		System.out.println(id);
		Optional<StudentEntity> studentEntity = studentRepository.findById(id);

		BeanUtils.copyProperties(studentEntity.get(), returnValue);
		return returnValue;
	}

	@Override
	public void deleteStudentById(Long id) {
		StudentDetailsResponse response = new StudentDetailsResponse();
		studentRepository.deleteById(id);
	}

	@Override
	public StudentDto updateDetail(String enrollment, StudentDto studentDto) {
		StudentDto returnValue = new StudentDto();
		StudentEntity student = studentRepository.findByStudentId(enrollment);
		if(student==null) {
			throw new UsernameNotFoundException(enrollment);
		}
		student.setFirstName(student.getFirstName());
		student.setLastName(student.getLastName());
		StudentEntity updated = studentRepository.save(student);
		BeanUtils.copyProperties(updated,returnValue);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}
}
