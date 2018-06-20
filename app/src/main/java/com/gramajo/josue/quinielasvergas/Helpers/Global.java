package com.gramajo.josue.quinielasvergas.Helpers;

import com.gramajo.josue.quinielasvergas.Objects.Country;
import com.gramajo.josue.quinielasvergas.Objects.FinalsGame;
import com.gramajo.josue.quinielasvergas.Objects.Game;
import com.gramajo.josue.quinielasvergas.Objects.Games;

import java.util.ArrayList;

/**
 * Created by josuegramajo on 6/14/18.
 */

public class Global {
    public static String globalUser = null;

    //Teams
    public static Country alemania = new Country("Alemania", "alemania");
    public static Country arabia = new Country("Arabia", "arabia");
    public static Country argentina = new Country("Argentina", "argentina");
    public static Country australia = new Country("Australia", "australia");
    public static Country belgica = new Country("Belgica", "belgica");
    public static Country brasil = new Country("Brasil", "brasil");
    public static Country colombia = new Country("Colombia", "colombia");
    public static Country corea = new Country("Corea", "corea");
    public static Country costaRica = new Country("Costa Rica", "costa_rica");
    public static Country croacia = new Country("Croacia", "croacia");
    public static Country dinamarca = new Country("Dinamarca", "dinamarca");
    public static Country egipto = new Country("Egipto", "egipto");
    public static Country francia = new Country("Francia", "francia");
    public static Country inglaterra = new Country("Inglaterra", "inglaterra");
    public static Country iran = new Country("Iran", "iran");
    public static Country islandia = new Country("Islandia", "islandia");
    public static Country japon = new Country("Japon", "japon");
    public static Country marruecos = new Country("Marruecos", "marruecos");
    public static Country mexico = new Country("Mexico", "mexico");
    public static Country nigeria = new Country("Nigeria", "nigeria");
    public static Country panama = new Country("Panama", "panama");
    public static Country peru = new Country("Peru", "peru");
    public static Country polonia = new Country("Polonia", "polonia");
    public static Country portugal = new Country("Portugal", "portugal");
    public static Country rusia = new Country("Rusia", "rusia");
    public static Country senegal = new Country("Senegal", "senegal");
    public static Country serbia = new Country("Serbia", "serbia");
    public static Country spain = new Country("España", "spain");
    public static Country suecia = new Country("Suecia", "suecia");
    public static Country suiza = new Country("Suiza", "suiza");
    public static Country tunez = new Country("Tunez", "tunez");
    public static Country uruguay = new Country("Uruguay", "uruguay");
    public static Country undefined = new Country("Por definir", "undefined");

    //Games
    public static Game game1 = new Game(1, rusia, arabia, "14/06/2018", null, null, false);
    public static Game game2 = new Game(2, egipto, uruguay, "15/06/2018", null, null, false);
    public static Game game3 = new Game(3, marruecos, iran, "15/06/2018", null, null, false);
    public static Game game4 = new Game(4, portugal, spain, "15/06/2018", null, null, false);
    public static Game game5 = new Game(5, francia, australia, "16/06/2018", null, null, true);
    public static Game game6 = new Game(6, argentina, islandia, "16/06/2018", null, null, true);
    public static Game game7 = new Game(7, peru, dinamarca, "16/06/2018", null, null, true);
    public static Game game8 = new Game(8, croacia, nigeria, "16/06/2018", null, null, true);
    public static Game game9 = new Game(9, costaRica, serbia, "17/06/2018", null, null, true);
    public static Game game10 = new Game(10, alemania, mexico, "17/06/2018", null, null, true);
    public static Game game11 = new Game(11, brasil, suiza, "17/06/2018", null, null, true);
    public static Game game12 = new Game(12, suecia, corea, "18/06/2018", null, null, true);
    public static Game game13 = new Game(13, belgica, panama, "18/06/2018", null, null, true);
    public static Game game14 = new Game(14, tunez, inglaterra, "18/06/2018", null, null, true);
    public static Game game15 = new Game(15, colombia, japon, "19/06/2018", null, null, true);
    public static Game game16 = new Game(16, polonia, senegal, "19/06/2018", null, null, true);
    public static Game game17 = new Game(17, rusia, egipto, "19/06/2018", null, null, true);
    public static Game game18 = new Game(18, portugal, marruecos, "20/06/2018", null, null, true);
    public static Game game19 = new Game(19, uruguay, arabia, "20/06/2018", null, null, true);
    public static Game game20 = new Game(20, iran, spain, "20/06/2018", null, null, true);
    public static Game game21 = new Game(21, dinamarca, australia, "21/06/2018", null, null, true);
    public static Game game22 = new Game(22, francia, peru, "21/06/2018", null, null, true);
    public static Game game23 = new Game(23, argentina, croacia, "21/06/2018", null, null, true);
    public static Game game24 = new Game(24, brasil, costaRica, "22/06/2018", null, null, true);
    public static Game game25 = new Game(25, nigeria, islandia, "22/06/2018", null, null, true);
    public static Game game26 = new Game(26, serbia, suiza, "22/06/2018", null, null, true);
    public static Game game27 = new Game(27, belgica, tunez, "23/06/2018", null, null, true);
    public static Game game28 = new Game(28, corea, mexico, "23/06/2018", null, null, true);
    public static Game game29 = new Game(29, alemania, suecia, "23/06/2018", null, null, true);
    public static Game game30 = new Game(30, inglaterra, panama, "24/06/2018", null, null, true);
    public static Game game31 = new Game(31, japon, senegal, "24/06/2018", null, null, true);
    public static Game game32 = new Game(32, polonia, colombia, "24/06/2018", null, null, true);
    public static Game game33 = new Game(33, uruguay, rusia, "25/06/2018", null, null, true);
    public static Game game34 = new Game(34, arabia, egipto, "25/06/2018", null, null, true);
    public static Game game35 = new Game(35, iran, portugal, "25/06/2018", null, null, true);
    public static Game game36 = new Game(36, spain, marruecos, "25/06/2018", null, null, true);
    public static Game game37 = new Game(37, dinamarca, francia, "26/06/2018", null, null, true);
    public static Game game38 = new Game(38, australia, peru, "26/06/2018", null, null, true);
    public static Game game39 = new Game(39, nigeria, argentina, "26/06/2018", null, null, true);
    public static Game game40 = new Game(40, islandia, croacia, "26/06/2018", null, null, true);
    public static Game game41 = new Game(41, mexico, suecia, "27/06/2018", null, null, true);
    public static Game game42 = new Game(42, corea, alemania, "27/06/2018", null, null, true);
    public static Game game43 = new Game(43, serbia, brasil, "27/06/2018", null, null, true);
    public static Game game44 = new Game(44, suiza, costaRica, "27/06/2018", null, null, true);
    public static Game game45 = new Game(45, japon, polonia, "28/06/2018", null, null, true);
    public static Game game46 = new Game(46, senegal, colombia, "28/06/2018", null, null, true);
    public static Game game47 = new Game(47, panama, tunez, "28/06/2018", null, null, true);
    public static Game game48 = new Game(48, inglaterra, belgica, "28/06/2018", null, null, true);

    //Octavos
    public static FinalsGame final1 = new FinalsGame(49, undefined, undefined, "30/06/2018","08:00", null, null, false, "", "$Octavos");
    public static FinalsGame final2 = new FinalsGame(50, undefined, undefined, "30/06/2018","12:00", null, null, false, "", "Octavos");
    public static FinalsGame final3 = new FinalsGame(51, undefined, undefined, "01/07/2018","08:00", null, null, false, "", "Octavos");
    public static FinalsGame final4 = new FinalsGame(52, undefined, undefined, "01/07/2018","12:00", null, null, false, "", "Octavos");
    public static FinalsGame final5 = new FinalsGame(53, undefined, undefined, "02/07/2018","08:00", null, null, false, "", "Octavos");
    public static FinalsGame final6 = new FinalsGame(54, undefined, undefined, "02/07/2018","12:00", null, null, false, "", "Octavos");
    public static FinalsGame final7 = new FinalsGame(55, undefined, undefined, "03/07/2018","08:00", null, null, false, "", "Octavos");
    public static FinalsGame final8 = new FinalsGame(56, undefined, undefined, "03/07/2018","12:00", null, null, false, "", "Octavos");

    //Cuartos
    public static FinalsGame final9 = new FinalsGame(57, undefined, undefined, "06/07/2018","08:00", null, null, false, "", "$Cuartos");
    public static FinalsGame final10 = new FinalsGame(58, undefined, undefined, "06/07/2018","12:00", null, null, false, "", "Cuartos");
    public static FinalsGame final11 = new FinalsGame(59, undefined, undefined, "07/07/2018","08:00", null, null, false, "", "Cuartos");
    public static FinalsGame final12 = new FinalsGame(60, undefined, undefined, "07/07/2018","12:00", null, null, false, "", "Cuartos");

    //Semis
    public static FinalsGame final13 = new FinalsGame(61, undefined, undefined, "10/07/2018","12:00", null, null, false, "", "$Semifinales");
    public static FinalsGame final14 = new FinalsGame(62, undefined, undefined, "11/07/2018","12:00", null, null, false, "", "Semifinales");

    //Tercer lugar
    public static FinalsGame final15 = new FinalsGame(63, undefined, undefined, "14/07/2018","08:00", null, null, false, "", "$Tercer Lugar");

    //Final
    public static FinalsGame final16 = new FinalsGame(64, undefined, undefined, "15/07/2018","09:00", null, null, false, "", "$Final");

    public static Games list = new Games();

    public static ArrayList<Game> initList(){
        ArrayList<Game> daList = new ArrayList<Game>();

        daList.add(game1);
        daList.add(game2);
        daList.add(game3);
        daList.add(game4);
        daList.add(game5);
        daList.add(game6);
        daList.add(game7);
        daList.add(game8);
        daList.add(game9);
        daList.add(game10);
        daList.add(game11);
        daList.add(game12);
        daList.add(game13);
        daList.add(game14);
        daList.add(game15);
        daList.add(game16);
        daList.add(game17);
        daList.add(game18);
        daList.add(game19);
        daList.add(game20);
        daList.add(game21);
        daList.add(game22);
        daList.add(game23);
        daList.add(game24);
        daList.add(game25);
        daList.add(game26);
        daList.add(game27);
        daList.add(game28);
        daList.add(game29);
        daList.add(game30);
        daList.add(game31);
        daList.add(game32);
        daList.add(game33);
        daList.add(game34);
        daList.add(game35);
        daList.add(game36);
        daList.add(game37);
        daList.add(game38);
        daList.add(game39);
        daList.add(game40);
        daList.add(game41);
        daList.add(game42);
        daList.add(game43);
        daList.add(game44);
        daList.add(game45);
        daList.add(game46);
        daList.add(game47);
        daList.add(game48);

        return  daList;
    }

    public static ArrayList<FinalsGame> initFinalsList(){
        ArrayList<FinalsGame> list = new ArrayList<FinalsGame>();

        list.add(final1);
        list.add(final2);
        list.add(final3);
        list.add(final4);
        list.add(final5);
        list.add(final6);
        list.add(final7);
        list.add(final8);
        list.add(final9);
        list.add(final10);
        list.add(final11);
        list.add(final12);
        list.add(final13);
        list.add(final14);
        list.add(final15);
        list.add(final16);

        return list;
    }
    
}
