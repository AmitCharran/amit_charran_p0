package com.revature.model;

import com.revature.orm.annotations.Column;
import com.revature.orm.annotations.Entity;
import com.revature.orm.annotations.Id;

@Entity(tableName = "tvShows")
public class TvShows {
    @Id(columnName = "tvId")
    private int tvId;
    @Column(columnName = "tvShowName")
    private String tvShowName;
    @Column(columnName = "genre")
    private String genre;
    @Column(columnName = "length")
    private double length;

    public TvShows() {}

    public TvShows(int tvId, String tvShowName, String genre, double length) {
        this.tvId = tvId;
        this.tvShowName = tvShowName;
        this.genre = genre;
        this.length = length;
    }

    public int getTvId() {
        return tvId;
    }

    public void setTvId(int tvId) {
        this.tvId = tvId;
    }

    public String getTvShowName() {
        return tvShowName;
    }

    public void setTvShowName(String tvShowName) {
        this.tvShowName = tvShowName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public boolean compareWithoutTvShowId(TvShows tvShows){
        if(tvShows.getTvShowName().equals(tvShowName)
                && tvShows.getGenre().equals(genre)
                && length == tvShows.getLength()){
            return true;
        }
        return false;
    }
}
