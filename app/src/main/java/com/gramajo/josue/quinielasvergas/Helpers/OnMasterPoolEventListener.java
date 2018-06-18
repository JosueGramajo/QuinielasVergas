package com.gramajo.josue.quinielasvergas.Helpers;

import com.gramajo.josue.quinielasvergas.Objects.Game;

import java.util.List;

/**
 * Created by josue on 16/06/2018.
 */

public interface OnMasterPoolEventListener {
    void onMasterPoolSuccess(List<Game> games);
}
