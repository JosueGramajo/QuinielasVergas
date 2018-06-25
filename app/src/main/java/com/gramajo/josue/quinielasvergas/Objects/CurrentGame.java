package com.gramajo.josue.quinielasvergas.Objects;

import java.util.ArrayList;

/**
 * Created by josuegramajo on 6/25/18.
 */

public class CurrentGame {
    private Game groupGame;
    private FinalsGame finalsGame;
    private ArrayList<String> userResults;
    private String type;

    public CurrentGame() {
    }

    public CurrentGame(Game groupGame, FinalsGame finalsGame, ArrayList<String> userResults) {
        this.groupGame = groupGame;
        this.finalsGame = finalsGame;
        this.userResults = userResults;
    }

    public void addUserResult(String result){
        if(userResults == null) userResults = new ArrayList<>();
        userResults.add(result);
    }

    public Game getGroupGame() {
        return groupGame;
    }

    public void setGroupGame(Game groupGame) {
        this.groupGame = groupGame;
    }

    public FinalsGame getFinalsGame() {
        return finalsGame;
    }

    public void setFinalsGame(FinalsGame finalsGame) {
        this.finalsGame = finalsGame;
    }

    public ArrayList<String> getUserResults() {
        return userResults;
    }

    public void setUserResults(ArrayList<String> userResults) {
        this.userResults = userResults;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
