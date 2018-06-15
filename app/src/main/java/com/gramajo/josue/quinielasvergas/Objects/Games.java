package com.gramajo.josue.quinielasvergas.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by josuegramajo on 6/14/18.
 */

public class Games {
    private List<Game> games;
    private String user;

    public Games(List<Game> games, String user) {
        this.games = games;
        this.user = user;
    }

    public Games() {
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
