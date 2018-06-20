package com.gramajo.josue.quinielasvergas.Objects;

import java.util.List;

/**
 * Created by josuegramajo on 6/20/18.
 */

public class FinalsGames {
    private List<FinalsGame> games;
    private String user;

    public FinalsGames(){

    }

    public FinalsGames(List<FinalsGame> games, String user) {
        this.games = games;
        this.user = user;
    }

    public List<FinalsGame> getGames() {
        return games;
    }

    public void setGames(List<FinalsGame> games) {
        this.games = games;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
