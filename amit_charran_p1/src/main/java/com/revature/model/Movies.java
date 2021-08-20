package com.revature.model;

import com.revature.orm.annotations.Column;
import com.revature.orm.annotations.Entity;
import com.revature.orm.annotations.Id;

@Entity(tableName = "movies")
public class Movies {
    @Id(columnName = "id")
    private int movieId;

    @Column(columnName = "movieName")
    private String movieName;

    @Column(columnName = "genre")
    private String genre;

    @Column(columnName = "movieLength")
    private double movieLength;

    @Column(columnName = "movieRating")
    private MovieRating movieRating;

    public Movies() {}

    public Movies(int movieId, String movieName, String genre, double movieLength, MovieRating movieRating) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.genre = genre;
        this.movieLength = movieLength;
        this.movieRating = movieRating;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(double movieLength) {
        this.movieLength = movieLength;
    }

    public MovieRating getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(MovieRating movieRating) {
        this.movieRating = movieRating;
    }
}
