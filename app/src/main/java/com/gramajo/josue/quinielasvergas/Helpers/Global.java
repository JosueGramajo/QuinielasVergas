package com.gramajo.josue.quinielasvergas.Helpers;

import com.gramajo.josue.quinielasvergas.Objects.Country;
import com.gramajo.josue.quinielasvergas.Objects.Game;
import com.gramajo.josue.quinielasvergas.Objects.Games;
import com.gramajo.josue.quinielasvergas.R;

import java.util.ArrayList;

/**
 * Created by josuegramajo on 6/14/18.
 */

public class Global {
    //Teams
    public static Country alemania = new Country("Alemania", R.drawable.alemania);
    public static Country arabia = new Country("Arabia", R.drawable.arabia);
    public static Country argentina = new Country("Argentina", R.drawable.argentina);
    public static Country australia = new Country("Australia", R.drawable.australia);
    public static Country belgica = new Country("Belgica", R.drawable.belgica);
    public static Country brasil = new Country("Brasil", R.drawable.brasil);
    public static Country colombia = new Country("Colombia", R.drawable.colombia);
    public static Country corea = new Country("Corea", R.drawable.corea);
    public static Country costaRica = new Country("Costa Rica", R.drawable.costa_rica);
    public static Country croacia = new Country("Croacia", R.drawable.croacia);
    public static Country dinamarca = new Country("Dinamarca", R.drawable.dinamarca);
    public static Country egipto = new Country("Egipto", R.drawable.egipto);
    public static Country francia = new Country("Francia", R.drawable.francia);
    public static Country inglaterra = new Country("Inglaterra", R.drawable.inglaterra);
    public static Country iran = new Country("Iran", R.drawable.iran);
    public static Country islandia = new Country("Islandia", R.drawable.islandia);
    public static Country japon = new Country("Japon", R.drawable.japon);
    public static Country marruecos = new Country("Marruecos", R.drawable.marruecos);
    public static Country mexico = new Country("Mexico", R.drawable.mexico);
    public static Country nigeria = new Country("Nigeria", R.drawable.nigeria);
    public static Country panama = new Country("Panama", R.drawable.panama);
    public static Country peru = new Country("Peru", R.drawable.peru);
    public static Country polonia = new Country("Polonia", R.drawable.polonia);
    public static Country portugal = new Country("Portugal", R.drawable.portugal);
    public static Country rusia = new Country("Rusia", R.drawable.rusia);
    public static Country senegal = new Country("Senegal", R.drawable.senegal);
    public static Country serbia = new Country("Serbia", R.drawable.serbia);
    public static Country spain = new Country("España", R.drawable.spain);
    public static Country suecia = new Country("Suecia", R.drawable.suecia);
    public static Country suiza = new Country("Suiza", R.drawable.suiza);
    public static Country tunez = new Country("Tunez", R.drawable.tunez);
    public static Country uruguay = new Country("Uruguay", R.drawable.uruguay);

    //Games int id, Country firstTeam, Country secondTeam, String date, Integer firstTeamScore, Integer secondTeamScore
    public static Game game1 = new Game(1, rusia, arabia, "14 Junio", 5, 0);
    public static Game game2 = new Game(1, egipto, uruguay, "15 Junio", null, null);
    public static Game game3 = new Game(1, marruecos, iran, "15 Junio", null, null);

    public static Games list = new Games();
    ArrayList<Game> daList = new ArrayList<Game>();
    
}
