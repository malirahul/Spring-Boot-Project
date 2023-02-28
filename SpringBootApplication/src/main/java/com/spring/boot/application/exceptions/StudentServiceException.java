package com.spring.boot.application.exceptions;

public class StudentServiceException extends RuntimeException{
    private static final long serialVersionUID = 1L;
// used as general exception
    public StudentServiceException(String message) {
        super(message);
    }
}
