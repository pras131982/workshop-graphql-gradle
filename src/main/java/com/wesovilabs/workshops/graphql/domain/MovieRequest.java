package com.wesovilabs.workshops.graphql.domain;

public class MovieRequest {

    private String title;

    private int year;

    private String genre;

    private Float budget;

    private String thriller;

    private Long directorId;

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

    public Long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
    }
}
