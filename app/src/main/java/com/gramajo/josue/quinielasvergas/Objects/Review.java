package com.gramajo.josue.quinielasvergas.Objects;

/**
 * Created by josuegramajo on 6/18/18.
 */

public class Review {
    Game game;
    Integer poolFirstScore;
    Integer poolSecondScore;
    int points;

    public Review() {

    }

    public Review(Game game, Integer poolFirstScore, Integer poolSecondScore, int points) {
        this.game = game;
        this.poolFirstScore = poolFirstScore;
        this.poolSecondScore = poolSecondScore;
        this.points = points;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
