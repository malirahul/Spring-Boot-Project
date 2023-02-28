package com.spring.boot.application.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.spring.boot.application.dto.ErrorDto;
import com.spring.boot.application.model.response.ErrorMessage;

import jakarta.validation.ConstraintViolation;

import java.util.*;
import java.util.stream.Collectors;
@RestControllerAdvice
public class AppExceptionHandler {
	
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleException(MethodArgumentNotValidException ex) {

        ErrorDto dto = new ErrorDto(HttpStatus.BAD_REQUEST, "Validation error");

        dto.setField(ex.getBindingResult().getAllErrors().stream()
            .map(err -> err.unwrap(ConstraintViolation.class))
            .map(err -> String.format("'%s' %s", err.getPropertyPath(), err.getMessage()))
            .collect(Collectors.toList()));

        ErrorMessage errorMessage = new ErrorMessage(new Date(),true, dto, null);
        return new ResponseEntity<>(errorMessage, new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
        
	}
	
	

    //specific exception handling
    @ExceptionHandler(value = {StudentServiceException.class})
    public ResponseEntity<Object> handleUserServiceException(StudentServiceException ex, WebRequest request)
    {
        ErrorDto errorDto = new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server Error");

        //provide custom exception
        ErrorMessage errorMessage = new ErrorMessage(new Date(),true, errorDto, null);
        return new ResponseEntity<>(errorMessage, new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //for all other exception like NullPointerException
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherException(Exception ex, WebRequest request)
    {
        ErrorDto errorDto = new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR,"Internal server Error");
        ErrorMessage errorMessage = new ErrorMessage(new Date(),true, errorDto, null);
        return new ResponseEntity<>(errorMessage, new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
