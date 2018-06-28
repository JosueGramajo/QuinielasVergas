package com.gramajo.josue.quinielasvergas.Helpers;

import com.gramajo.josue.quinielasvergas.Objects.FinalsGame;
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

        for(Game m : master){

            Integer masterFirstScore = m.getFirstTeamScore();
            Integer masterSecondScore = m.getSecondTeamScore();
            boolean masterActive = m.isActive();

            Integer firstScore = null;
            Integer secondScore = null;

            for(Game g : user){
                if(g.getId() == m.getId()){
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

    public int getFinalsPoints(List<FinalsGame> master, List<FinalsGame> user){
        int uPoints = 0;

        for(FinalsGame m : master){

            Integer masterFirstScore = m.getFirstTeamScore();
            Integer masterSecondScore = m.getSecondTeamScore();
            boolean masterPenalties = m.isPenalties();
            String masterPenaltieWinner = m.getPenaltiesWinner();

            Integer firstScore = null;
            Integer secondScore = null;
            boolean penalties = false;
            String penaltieWinner = "";

            for(FinalsGame g : user){
                if(g.getId() == m.getId()){
                    firstScore = g.getFirstTeamScore();
                    secondScore = g.getSecondTeamScore();
                    penalties = g.isPenalties();
                    penaltieWinner = g.getPenaltiesWinner();
                }
            }

            if(masterFirstScore == null || masterSecondScore == null || firstScore == null || secondScore == null){
                continue;
            }

            if(penaltieWinner.equals(masterPenaltieWinner) && penalties && (masterFirstScore == firstScore && masterSecondScore == secondScore)){
                uPoints = uPoints + 6;
                continue;
            }

            if(penaltieWinner.equals(masterPenaltieWinner) && penalties){
                uPoints = uPoints + 3;
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

    public int getPointsIndividualFinalsGame(FinalsGame master, FinalsGame user){
        Integer masterFirstScore = master.getFirstTeamScore();
        Integer masterSecondScore = master.getSecondTeamScore();
        boolean masterPenalties = master.isPenalties();
        String masterPenaltieWinner = master.getPenaltiesWinner();

        Integer firstScore = user.getFirstTeamScore();
        Integer secondScore = user.getSecondTeamScore();
        boolean penalties = user.isPenalties();
        String penaltieWinner = user.getPenaltiesWinner();

        if(masterFirstScore == null || masterSecondScore == null || firstScore == null || secondScore == null){
            return 0;
        }

        if(penaltieWinner.equals(masterPenaltieWinner) && penalties && (masterFirstScore == firstScore && masterSecondScore == secondScore)){
            return 6;
        }

        if(penaltieWinner.equals(masterPenaltieWinner) && penalties){
            return 3;
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
