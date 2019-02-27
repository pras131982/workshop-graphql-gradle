package com.wesovilabs.workshops.graphql.domain;

public class Director {

    private Integer id;

    private String fullName;

    private String country;

    public Director(Integer id) {
        this.id = id;
    }

    public Director(Integer id, String fullName, String country) {
        this.id = id;
        this.fullName = fullName;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
