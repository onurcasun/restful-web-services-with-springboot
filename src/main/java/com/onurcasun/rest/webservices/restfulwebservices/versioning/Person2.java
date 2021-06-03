package com.onurcasun.rest.webservices.restfulwebservices.versioning;

public class Person2 {
    private Name name;
    
    public Person2(Name name) {
        this.setName(name);
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
}
