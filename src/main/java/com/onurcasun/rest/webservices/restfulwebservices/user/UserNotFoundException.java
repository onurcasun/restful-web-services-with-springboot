package com.onurcasun.rest.webservices.restfulwebservices.user;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException {      
        public final static HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        public UserNotFoundException(String message) {
            super(message);
        }
}
