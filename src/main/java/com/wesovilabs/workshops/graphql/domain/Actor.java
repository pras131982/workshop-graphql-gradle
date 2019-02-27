package com.wesovilabs.workshops.graphql.domain;

public class Actor {

    private Integer id;

    private String fullName;

    private String country;

    private String gender;

    public Actor(Integer id, String fullName, String country, String gender) {
        this.id = id;
        this.fullName = fullName;
        this.country = country;
        this.gender = gender;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
