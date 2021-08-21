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
    private String movieRating;

    public Movies() {}

    public Movies(int movieId, String movieName, String genre, double movieLength, String movieRating) {
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

    public String getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }

    public boolean compareWithoutMovieId(Movies movie){
        if(movie.getMovieName().equals(movieName)
                && movie.getMovieRating().equals(movieRating)
                && movie.getGenre().equals(genre)
                && movieLength == movie.getMovieLength()){
            return true;
        }
        return false;
    }

    public String toString(){
        return "Movie id: " + movieId
                + "\nMovie name: " + movieName
                + "\nMovie genre: " + genre
                + "\nMovie length: " + movieLength
                + "\nMovie Rating: " + movieRating;
    }

}
