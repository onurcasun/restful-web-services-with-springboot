package com.onurcasun.rest.webservices.restfulwebservices;

public class HelloWorldBean {

    private String message;

    public HelloWorldBean(String message) {
        this.message = message;
    }   

    public String getMessage(){
        return message;
    }
    
    @Override
    public String toString() {
        return String.format("HelloWorldBean [message=%s]", message);
    }

}