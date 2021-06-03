package com.onurcasun.rest.webservices.restfulwebservices.exception;

import java.util.Date;

import com.onurcasun.rest.webservices.restfulwebservices.user.UserNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ResponseEntity<Object> responseEntity = buildResponseEntity(ex.getMessage(), request, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ResponseEntity<Object> responseEntity = buildResponseEntity(ex.getMessage(), request, UserNotFoundException.httpStatus);
        return responseEntity;        
    }

    private ResponseEntity<Object> buildResponseEntity(String exMessage, WebRequest request, HttpStatus httpStatus) {
        ExceptionResponse exceptionResponse = ExceptionResponse.create(new Date(), exMessage, request.getDescription(false));
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(exceptionResponse, httpStatus);
        return responseEntity;
    }
}
