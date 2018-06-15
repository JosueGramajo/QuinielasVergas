package com.gramajo.josue.quinielasvergas.Objects;

/**
 * Created by josuegramajo on 6/14/18.
 */

public class Game {
    private  int id;
    private Country firstTeam;
    private Country secondTeam;
    private String date;
    private Integer firstTeamScore;
    private Integer secondTeamScore;
    private boolean active;

    public Game(int id, Country firstTeam, Country secondTeam, String date, Integer firstTeamScore, Integer secondTeamScore, boolean active) {
        this.id = id;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.date = date;
        this.firstTeamScore = firstTeamScore;
        this.secondTeamScore = secondTeamScore;
        this.active = active;
    }

    public Game(){}

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Country getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(Country firstTeam) {
        this.firstTeam = firstTeam;
    }

    public Country getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(Country secondTeam) {
        this.secondTeam = secondTeam;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getFirstTeamScore() {
        return firstTeamScore;
    }

    public void setFirstTeamScore(Integer firstTeamScore) {
        this.firstTeamScore = firstTeamScore;
    }

    public Integer getSecondTeamScore() {
        return secondTeamScore;
    }

    public void setSecondTeamScore(Integer secondTeamScore) {
        this.secondTeamScore = secondTeamScore;
    }
}
