package com.gramajo.josue.quinielasvergas.Objects;

/**
 * Created by josuegramajo on 6/20/18.
 */

public class FinalsGame {
    private int id;
    private Country firstTeam;
    private Country secondTeam;
    private String date;
    private String hour;
    private Integer firstTeamScore;
    private Integer secondTeamScore;
    private boolean penalties;
    private String penaltiesWinner;
    private String gameType;

    public FinalsGame(){

    }

    public FinalsGame(int id, Country firstTeam, Country secondTeam, String date, String hour, Integer firstTeamScore, Integer secondTeamScore, boolean penalties, String penaltiesWinner, String gameType) {
        this.id = id;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.date = date;
        this.hour = hour;
        this.firstTeamScore = firstTeamScore;
        this.secondTeamScore = secondTeamScore;
        this.penalties = penalties;
        this.penaltiesWinner = penaltiesWinner;
        this.gameType = gameType;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
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

    public boolean isPenalties() {
        return penalties;
    }

    public void setPenalties(boolean penalties) {
        this.penalties = penalties;
    }

    public String getPenaltiesWinner() {
        return penaltiesWinner;
    }

    public void setPenaltiesWinner(String penaltiesWinner) {
        this.penaltiesWinner = penaltiesWinner;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }
}
