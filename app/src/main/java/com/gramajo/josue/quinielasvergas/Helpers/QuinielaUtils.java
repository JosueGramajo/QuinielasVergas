package com.gramajo.josue.quinielasvergas.Helpers;

import com.gramajo.josue.quinielasvergas.Objects.Game;
import com.gramajo.josue.quinielasvergas.Objects.Games;

import java.util.List;

/**
 * Created by josuegramajo on 6/15/18.
 */

public class QuinielaUtils {
    public static QuinielaUtils INSTANCE = new QuinielaUtils();

    public int getPoints(List<Game> master, List<Game> user){
        int uPoints = 0;

        for(int i = 1;i<master.size() + 1;i++){

            Integer masterFirstScore = null;
            Integer masterSecondScore = null;
            Integer firstScore = null;
            Integer secondScore = null;

            boolean masterActive = true;

            for(Game g : master){
                if(g.getId() == i){
                    masterFirstScore = g.getFirstTeamScore();
                    masterSecondScore = g.getSecondTeamScore();
                    masterActive = g.isActive();
                }
            }
            for(Game g : user){
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
    public int getPointsIndividualGame(Game master, Game user){
        Integer masterFirstScore = master.getFirstTeamScore();
        Integer masterSecondScore = master.getSecondTeamScore();
        Integer firstScore = user.getFirstTeamScore();
        Integer secondScore = user.getSecondTeamScore();

        boolean masterActive = master.isActive();

        if(masterFirstScore == null || masterSecondScore == null || firstScore == null || secondScore == null){
            return 0;
        }

        if(!masterActive){
            return 0;
        }

        if(masterFirstScore == firstScore && masterSecondScore == secondScore){
            return 5;
        }

        if(masterFirstScore > masterSecondScore && firstScore > secondScore){
            return 1;
        }

        if(masterFirstScore < masterSecondScore && firstScore < secondScore){
            return 1;
        }

        if(masterFirstScore == masterSecondScore && firstScore == secondScore){
            return 1;
        }

        return 0;
    }
}
