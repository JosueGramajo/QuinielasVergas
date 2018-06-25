package com.gramajo.josue.quinielasvergas.Helpers;

import com.gramajo.josue.quinielasvergas.Objects.FinalsGames;
import com.gramajo.josue.quinielasvergas.Objects.Games;

import java.util.List;

/**
 * Created by josuegramajo on 6/25/18.
 */

public interface OnCurrentGameEventListener {
    void onGroupsSuccess(List<Games> games, Games pool);
    void onFinalsSuccess(List<FinalsGames> games, FinalsGames pool);
}
