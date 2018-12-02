package com.tdp2.group152.controllers;

import com.tdp2.group152.exceptions.ApiErrorModel;
import com.tdp2.group152.exceptions.ResourceNotAvailableException;
import com.tdp2.group152.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorModel> handleException(Exception e) {
        ApiErrorModel error = new ApiErrorModel(500, e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorModel> handleException(ResourceNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotAvailableException.class)
    public ResponseEntity<ApiErrorModel> handleException(ResourceNotAvailableException e) {
        ApiErrorModel error = new ApiErrorModel(409, e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
