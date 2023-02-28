package com.spring.boot.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.spring.boot.application.entity.StudentEntity;
import com.spring.boot.application.repository.StudentRepository;

import java.util.Optional;
@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<StudentEntity>studentEntity = studentRepository.findByEmail(email);
        return studentEntity.map(UserInfoDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("student not found "+email));
    }
}
