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
        return buildResponseEntity(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {

        return buildResponseEntity(ex, request, HttpStatus.NOT_FOUND);            
    }

    private ResponseEntity<Object> buildResponseEntity(Exception ex, WebRequest request, HttpStatus status) {
        ExceptionResponse exceptionResponse = createExceptionResponse(ex.getMessage(), request.getDescription(false));        
        ResponseEntity<Object> responseEntity = createResponseEntity(exceptionResponse, status);
        return responseEntity;
    }
    
    private ExceptionResponse createExceptionResponse(String message, String description) {
        return new ExceptionResponse(new Date(), message, description);
    }

    private ResponseEntity<Object> createResponseEntity(ExceptionResponse exceptionResponse, HttpStatus errorHttpStatus ) {
        return new ResponseEntity<Object>(exceptionResponse, errorHttpStatus);
    }

    
}
