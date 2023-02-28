package com.spring.boot.application.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.spring.boot.application.dto.ErrorDto;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//for custom exception
public class ErrorMessage {
    private Date timestamp;
    private Boolean isError;
    private ErrorDto errorDto;
    private UserDetailsService data;


}
