package com.onurcasun.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController {

    // Versioning v1 : uri versioning
    @GetMapping("/v1/person")
    public PersonV1 version1() {
        return new PersonV1("Bob Dylan");
    }

    @GetMapping("/v2/person")
    public PersonV2 version2() {
        return new PersonV2(new Name("Bob", "Dylan"));
    }

    // Versioning v2 with Params.
    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 versionWParam1() {
        return new PersonV1("Bob Dylan");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 versionWParam2() {
        return new PersonV2(new Name("Bob", "Dylan"));
    }

    // Versioning v3 with Header
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 versionWHeader1() {
        return new PersonV1("Bob Dylan");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 versionWHeader2() {
        return new PersonV2(new Name("Bob", "Dylan"));
    }


    // Versioning v4 with Produces
    @GetMapping(value = "/person/produces", produces = "application/org.onurcasun.app-v1+json")
    public PersonV1 versionWProduces1() {
        return new PersonV1("Bob Dylan");
    }

    @GetMapping(value = "/person/produces", produces = "application/org.onurcasun.app-v2+json")
    public PersonV2 versionWProduces2() {
        return new PersonV2(new Name("Bob", "Dylan"));
    }

}
