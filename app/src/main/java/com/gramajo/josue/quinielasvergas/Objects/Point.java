package com.gramajo.josue.quinielasvergas.Objects;

/**
 * Created by josuegramajo on 6/15/18.
 */

public class Point {
    private int points;
    private String user;

    public Point() {
    }

    public Point(int points, String user) {
        this.points = points;
        this.user = user;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
