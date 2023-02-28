package com.spring.boot.application.repository;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.application.entity.StudentEntity;

import java.util.Optional;
@EnableJpaRepositories
@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Long>{
    Optional<StudentEntity>findByEmail(String email);



    StudentEntity findByStudentId(String enrollment);


}
