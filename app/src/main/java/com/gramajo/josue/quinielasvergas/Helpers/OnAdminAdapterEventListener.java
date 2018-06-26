package com.gramajo.josue.quinielasvergas.Helpers;

import com.gramajo.josue.quinielasvergas.Objects.FinalsGame;
import com.gramajo.josue.quinielasvergas.Objects.FinalsGames;
import com.gramajo.josue.quinielasvergas.Objects.Game;
import com.gramajo.josue.quinielasvergas.Objects.Games;

/**
 * Created by josuegramajo on 6/26/18.
 */

public interface OnAdminAdapterEventListener {
    void onButtonPressed(Game game);
    void onButtonPressed(FinalsGame finalsGame);
}
