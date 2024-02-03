package com.ayi.final2.controller;

import com.ayi.final2.dto.ErrorValidationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> fallaValidacion (MethodArgumentNotValidException ex ){
        HashMap<String, String> mistakes = new HashMap<>();
        ex.getFieldErrors().forEach(field->mistakes.put(field.getField(), field.getDefaultMessage()));
        return new ResponseEntity<>(new ErrorValidationDto(400, mistakes), HttpStatus.BAD_REQUEST);
    }
}
