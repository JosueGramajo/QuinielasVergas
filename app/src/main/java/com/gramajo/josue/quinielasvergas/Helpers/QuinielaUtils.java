package com.gramajo.josue.quinielasvergas.Helpers;

import com.gramajo.josue.quinielasvergas.Objects.Game;
import com.gramajo.josue.quinielasvergas.Objects.Games;

/**
 * Created by josuegramajo on 6/15/18.
 */

public class QuinielaUtils {
    public static QuinielaUtils INSTANCE = new QuinielaUtils();

    public int getPoints(Games master, Games user){
        int uPoints = 0;

        for(int i = 1;i<master.getGames().size() + 1;i++){

            Integer masterFirstScore = null;
            Integer masterSecondScore = null;
            Integer firstScore = null;
            Integer secondScore = null;

            boolean masterActive = true;

            for(Game g : master.getGames()){
                if(g.getId() == i){
                    masterFirstScore = g.getFirstTeamScore();
                    masterSecondScore = g.getSecondTeamScore();
                    masterActive = g.isActive();
                }
            }
            for(Game g : user.getGames()){
                if(g.getId() == i){
                    firstScore = g.getFirstTeamScore();
                    secondScore = g.getSecondTeamScore();
                }
            }

            if(masterFirstScore == null || masterSecondScore == null || firstScore == null || secondScore == null){
                continue;
            }

            if(!masterActive){
                continue;
            }

            if(masterFirstScore == firstScore && masterSecondScore == secondScore){
                uPoints = uPoints + 5;
                continue;
            }

            if(masterFirstScore > masterSecondScore && firstScore > secondScore){
                uPoints = uPoints + 1;
                continue;
            }

            if(masterFirstScore < masterSecondScore && firstScore < secondScore){
                uPoints = uPoints + 1;
                continue;
            }

            if(masterFirstScore == masterSecondScore && firstScore == secondScore){
                uPoints = uPoints + 1;
                continue;
            }
        }
        return uPoints;
    }
}
