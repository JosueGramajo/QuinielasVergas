package com.gramajo.josue.quinielasvergas.Objects;

import java.util.ArrayList;

/**
 * Created by josuegramajo on 6/26/18.
 */

public class AdminGame {
    private Games groupGames;
    private FinalsGames finalsGame;
    private String type;

    public AdminGame() {
    }

    public AdminGame(Games groupGames, FinalsGames finalsGame, String type) {
        this.groupGames = groupGames;
        this.finalsGame = finalsGame;
        this.type = type;
    }

    public Games getGroupGames() {
        return groupGames;
    }

    public void setGroupGames(Games groupGames) {
        this.groupGames = groupGames;
    }

    public FinalsGames getFinalsGame() {
        return finalsGame;
    }

    public void setFinalsGame(FinalsGames finalsGame) {
        this.finalsGame = finalsGame;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
