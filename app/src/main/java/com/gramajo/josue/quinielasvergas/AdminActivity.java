package com.gramajo.josue.quinielasvergas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.gramajo.josue.quinielasvergas.Adapters.AdminAdapter;
import com.gramajo.josue.quinielasvergas.Helpers.DateUtils;
import com.gramajo.josue.quinielasvergas.Helpers.FirebaseUtils;
import com.gramajo.josue.quinielasvergas.Helpers.OnAdminAdapterEventListener;
import com.gramajo.josue.quinielasvergas.Helpers.OnCurrentGameEventListener;
import com.gramajo.josue.quinielasvergas.Objects.CurrentGame;
import com.gramajo.josue.quinielasvergas.Objects.FinalsGame;
import com.gramajo.josue.quinielasvergas.Objects.FinalsGames;
import com.gramajo.josue.quinielasvergas.Objects.Game;
import com.gramajo.josue.quinielasvergas.Objects.Games;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * Created by josuegramajo on 6/26/18.
 */

public class AdminActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    AdminAdapter adapter;

    ArrayList<CurrentGame> list = new ArrayList<>();

    SpotsDialog dialog;

    String type = "";

    FinalsGames finalsMasterPool;

    Games masterPool;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        recyclerView = findViewById(R.id.rv_admin);

        adapter = new AdminAdapter(this, list);

        dialog = (SpotsDialog) new SpotsDialog.Builder().setContext(this).build();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.setListener(new OnAdminAdapterEventListener() {
            @Override
            public void onButtonPressed(Game game) {
                for(int i = 0;i < masterPool.getGames().size(); i++){
                    if(masterPool.getGames().get(i).getId() == game.getId()){
                        masterPool.getGames().set(i, game);
                        break;
                    }
                }

                FirebaseUtils.INSTANCE.saveMasterPool(AdminActivity.this, masterPool);
            }

            @Override
            public void onButtonPressed(FinalsGame finalsGame) {
                for(int i = 0;i < finalsMasterPool.getGames().size(); i++){
                    if(finalsMasterPool.getGames().get(i).getId() == finalsGame.getId()){
                        finalsMasterPool.getGames().set(i, finalsGame);
                        break;
                    }
                }

                FirebaseUtils.INSTANCE.saveFinalPool(AdminActivity.this, finalsMasterPool);
            }
        });

        FirebaseUtils firebaseUtils = new FirebaseUtils();
        firebaseUtils.setOnCurrentGameEventListener(new OnCurrentGameEventListener() {
            @Override
            public void onGroupsSuccess(List<Games> games, Games pool) {
                dialog.dismiss();
                masterPool = pool;
                makeGroupsGame(pool);
            }

            @Override
            public void onFinalsSuccess(List<FinalsGames> games, FinalsGames pool) {
                dialog.dismiss();
                finalsMasterPool = pool;
                makeFinalsGame(pool);
            }
        });
        dialog.show();
        firebaseUtils.getCurrentType();
    }
    private void makeGroupsGame(Games pool){
        list.clear();

        type = "groups";

        Date currentDate = DateUtils.INSTANCE.stringToDate(DateUtils.INSTANCE.getCurrentDate());

        for(Game g : pool.getGames()){
            Date gameDate = DateUtils.INSTANCE.stringToDate(g.getDate());

            if(gameDate.equals(currentDate)){

                CurrentGame currentGame = new CurrentGame();

                currentGame.setGroupGame(g);
                currentGame.setType("groups");

                list.add(currentGame);
            }
        }

        adapter.notifyDataSetChanged();
    }
    private void makeFinalsGame(FinalsGames pool){
        list.clear();

        type = "finals";

        Date currentDate = DateUtils.INSTANCE.stringToDate(DateUtils.INSTANCE.getCurrentDate());

        for(FinalsGame g : pool.getGames()){
            Date gameDate = DateUtils.INSTANCE.stringToDate(g.getDate());

            if(gameDate.equals(currentDate)){

                CurrentGame currentGame = new CurrentGame();

                currentGame.setFinalsGame(g);
                currentGame.setType("finals");

                list.add(currentGame);
            }
        }

        adapter.notifyDataSetChanged();
    }

}
