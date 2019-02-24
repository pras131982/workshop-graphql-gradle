package com.wesovilabs.workshops.graphql.database.model;

import javax.persistence.*;

@Entity
@Table(name = "actors")
public class ActorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String country;

    private Boolean male;

    public ActorEntity(){

    }

    public ActorEntity(String fullName, String country, Boolean male) {
        this.fullName = fullName;
        this.country = country;
        this.male = male;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Boolean getMale() {
        return male;
    }

    public void setMale(Boolean male) {
        this.male = male;
    }
}
