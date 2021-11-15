package com.app.hallselector.model;

import android.media.Rating;

import java.util.Date;

public class Rating_Record {
    private String username;
    private int score;
    private String comment;
    private String building;

    @Override
    public String toString() {
        return "Rating_Record{" +
                "username='" + username + '\'' +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                ", building='" + building + '\'' +
                ", date=" + date +
                '}';
    }

    private Date date;

    public Rating_Record(String username, int score, String comment, String building, Date date) {
        this.username = username;
        this.score = score;
        this.comment = comment;
        this.building = building;
        this.date = date;
    }

    public Rating_Record(String username, int score, String comment, String building) {
        this.username = username;
        this.score = score;
        this.comment = comment;
        this.building = building;
        this.date = new Date();  //auto generated
    }

    public Rating_Record(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
