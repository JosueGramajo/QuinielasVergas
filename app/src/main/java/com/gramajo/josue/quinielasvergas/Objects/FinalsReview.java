package com.gramajo.josue.quinielasvergas.Objects;

/**
 * Created by josue on 20/06/2018.
 */

public class FinalsReview {
    private FinalsGame game;
    private Integer poolFirstScore;
    private Integer poolSecondScore;
    private boolean poolPenalties;
    private String poolPenaltiesWinner;
    private int points;




    public FinalsGame getGame() {
        return game;
    }

    public void setGame(FinalsGame game) {
        this.game = game;
    }

    public Integer getPoolFirstScore() {
        return poolFirstScore;
    }

    public void setPoolFirstScore(Integer poolFirstScore) {
        this.poolFirstScore = poolFirstScore;
    }

    public Integer getPoolSecondScore() {
        return poolSecondScore;
    }

    public void setPoolSecondScore(Integer poolSecondScore) {
        this.poolSecondScore = poolSecondScore;
    }

    public boolean isPoolPenalties() {
        return poolPenalties;
    }

    public void setPoolPenalties(boolean poolPenalties) {
        this.poolPenalties = poolPenalties;
    }

    public String getPoolPenaltiesWinner() {
        return poolPenaltiesWinner;
    }

    public void setPoolPenaltiesWinner(String poolPenaltiesWinner) {
        this.poolPenaltiesWinner = poolPenaltiesWinner;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
