package com.wesovilabs.workshops.graphql.domain;

import java.util.List;

public class Movie {

    private Long id;

    private String title;

    private int year;

    private String genre;

    private Float budget;

    private String thriller;

    private Director director;

    private List<Actor> actors;

    public Movie(Long id, String title, int year, String genre, Float budget, String thriller) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.budget = budget;
        this.thriller = thriller;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Float getBudget() {
        return budget;
    }

    public void setBudget(Float budget) {
        this.budget = budget;
    }

    public String getThriller() {
        return thriller;
    }

    public void setThriller(String thriller) {
        this.thriller = thriller;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}
