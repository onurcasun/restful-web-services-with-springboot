package com.onurcasun.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController {

    @GetMapping("/v1/person")
    public PersonV1 version1(){
        return new PersonV1("Bob Dylan");
    }

    @GetMapping("/v2/person")
    public PersonV2 version2(){
        return new PersonV2(new Name("Bob", "Dylan"));
    }

    
}
