package com.onurcasun.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController {

    @GetMapping("/v1/person")
    public Person1 version1(){
        return new Person1("Bob Dylan");
    }

    @GetMapping("/v2/person")
    public Person2 version2(){
        return new Person2(new Name("Bob", "Dylan"));
    }

    
}
