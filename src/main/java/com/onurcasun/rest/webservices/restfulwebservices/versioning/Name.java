package com.onurcasun.rest.webservices.restfulwebservices.versioning;

public class Name {
    private String name;
    private String lastname;

    public Name() {
        super();
    }

    public Name(String name, String lastname) {
        super();
        this.name = name;
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setName(String name) {
        this.name = name;
    }
}
