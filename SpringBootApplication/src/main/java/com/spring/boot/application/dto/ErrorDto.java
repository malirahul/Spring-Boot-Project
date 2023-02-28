package com.spring.boot.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;

import static org.springframework.util.Assert.notEmpty;
import static org.springframework.util.Assert.notNull;

@Data
public class ErrorDto {
    private final int status;
    private final String error;
    private final String message;
    private List<String> field;

    public ErrorDto(HttpStatus httpStatus, String message) {
        status = httpStatus.value();
        error = httpStatus.getReasonPhrase();
        this.message = message;
    }

}

