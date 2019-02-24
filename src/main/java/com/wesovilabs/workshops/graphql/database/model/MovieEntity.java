package com.wesovilabs.workshops.graphql.database.model;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "release_year")
    private int releaseYear;

    private String genre;

    private Float budget;

    private String thriller;

    @Column(name = "director_id")
    private Long directorId;

    public MovieEntity(){

    }

    public MovieEntity(String title, int releaseYear, String genre, Float budget, String thriller, Long directorId) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.budget = budget;
        this.thriller = thriller;
        this.directorId = directorId;
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

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
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
