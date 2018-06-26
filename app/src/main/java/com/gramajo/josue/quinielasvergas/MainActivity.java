package com.gramajo.josue.quinielasvergas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.gramajo.josue.quinielasvergas.Adapters.CurrentGameAdapter;
import com.gramajo.josue.quinielasvergas.Adapters.PrevReviewAdapter;
import com.gramajo.josue.quinielasvergas.Helpers.DateUtils;
import com.gramajo.josue.quinielasvergas.Helpers.FirebaseUtils;
import com.gramajo.josue.quinielasvergas.Helpers.Global;
import com.gramajo.josue.quinielasvergas.Helpers.OnCurrentGameEventListener;
import com.gramajo.josue.quinielasvergas.Objects.CurrentGame;
import com.gramajo.josue.quinielasvergas.Objects.FinalsGame;
import com.gramajo.josue.quinielasvergas.Objects.FinalsGames;
import com.gramajo.josue.quinielasvergas.Objects.Game;
import com.gramajo.josue.quinielasvergas.Objects.Games;
import com.gramajo.josue.quinielasvergas.Objects.Review;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    RecyclerView recyclerView;

    CurrentGameAdapter adapter;

    ArrayList<CurrentGame> list = new ArrayList<>();

    SpotsDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dialog = (SpotsDialog) new SpotsDialog.Builder().setContext(this).build();

        recyclerView = findViewById(R.id.rv_current);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        startActivityForResult(new Intent(this, LoginActivity.class), 1);

        adapter = new CurrentGameAdapter(this, list);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        FirebaseUtils firebaseUtils = new FirebaseUtils();
        firebaseUtils.setOnCurrentGameEventListener(new OnCurrentGameEventListener() {
            @Override
            public void onGroupsSuccess(List<Games> games, Games pool) {
                dialog.dismiss();
                try{
                    makeGroupsGame(games, pool);
                }catch (Exception ex){

                }
            }

            @Override
            public void onFinalsSuccess(List<FinalsGames> games, FinalsGames pool) {
                dialog.dismiss();
                try{
                    makeFinalsGame(games, pool);
                }catch (Exception ex){

                }
            }
        });
        dialog.show();
        firebaseUtils.getCurrentType();
    }

    private void makeGroupsGame(List<Games> games, Games pool) throws Exception{
        list.clear();

        Date currentDate = DateUtils.INSTANCE.stringToDate(DateUtils.INSTANCE.getCurrentDate());

        for(Game g : pool.getGames()){
            Date gameDate = DateUtils.INSTANCE.stringToDate(g.getDate());

            if(gameDate.equals(currentDate)){

                CurrentGame currentGame = new CurrentGame();

                currentGame.setGroupGame(g);
                currentGame.setType("groups");

                for(Games games2 : games){
                    for(Game userGame : games2.getGames()){
                        if(userGame.getId() == g.getId()){
                            if(userGame.getFirstTeamScore() != null && userGame.getSecondTeamScore() != null){
                                currentGame.addUserResult("<b>" + games2.getUser() + ":</b>  "
                                        + userGame.getFirstTeam().getName() + " "
                                        + userGame.getFirstTeamScore() + " - "
                                        + userGame.getSecondTeamScore() + " "
                                        + userGame.getSecondTeam().getName());
                            }else{
                                currentGame.addUserResult("<b>" + games2.getUser() + ":</b>  "
                                        + userGame.getFirstTeam().getName()
                                        + " X - X "
                                        + userGame.getSecondTeam().getName());
                            }

                        }
                    }
                }

                list.add(currentGame);
            }
        }

        adapter.notifyDataSetChanged();
    }

    private void makeFinalsGame(List<FinalsGames> games, FinalsGames pool) throws Exception{
        list.clear();

        Date currentDate = DateUtils.INSTANCE.stringToDate(DateUtils.INSTANCE.getCurrentDate());

        for(FinalsGame g : pool.getGames()){
            Date gameDate = DateUtils.INSTANCE.stringToDate(g.getDate());

            if(gameDate.equals(currentDate)){

                CurrentGame currentGame = new CurrentGame();

                currentGame.setFinalsGame(g);
                currentGame.setType("finals");

                for(FinalsGames games2 : games){
                    for(FinalsGame userGame : games2.getGames()){
                        if(userGame.getId() == g.getId()){
                            if(userGame.getFirstTeamScore() != null && userGame.getSecondTeamScore() != null){
                                String s = "<b>" + games2.getUser() + ":</b>  "
                                        + userGame.getFirstTeam().getName() + " "
                                        + userGame.getFirstTeamScore() + " - "
                                        + userGame.getSecondTeamScore() + " "
                                        + userGame.getSecondTeam().getName();

                                if(userGame.isPenalties()){
                                    s = s + " ("+ userGame.getPenaltiesWinner() +" gana en penales)";
                                }

                                currentGame.addUserResult(s);
                            }else{
                                currentGame.addUserResult("<b>" + games2.getUser() + ":</b>  "
                                        + userGame.getFirstTeam().getName()
                                        + " X - X "
                                        + userGame.getSecondTeam().getName());
                            }

                        }
                    }
                }

                list.add(currentGame);
            }
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "ño", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_quiniela) {
            startActivity(new Intent(this, QuinielaActivity.class));
        }else if (id == R.id.nav_quiniela_final) {
            startActivity(new Intent(this, FinalsActivity.class));
        }else if (id == R.id.nav_ranking) {
            startActivity(new Intent(this, RankingActivity.class));
        } else if (id == R.id.nav_logout){
            Global.globalUser = null;
            startActivity(new Intent(this, LoginActivity.class));
        } else if(id == R.id.nav_results){
            startActivity(new Intent(this, GlobalResultsActivity.class));
        } else if(id == R.id.nav_selections){
            startActivity(new Intent(this, PrevReviewActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
