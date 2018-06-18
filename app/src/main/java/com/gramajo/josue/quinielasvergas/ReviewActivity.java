package com.gramajo.josue.quinielasvergas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.gramajo.josue.quinielasvergas.Adapters.ReviewAdapter;
import com.gramajo.josue.quinielasvergas.Helpers.FirebaseUtils;
import com.gramajo.josue.quinielasvergas.Helpers.Global;
import com.gramajo.josue.quinielasvergas.Helpers.OnMasterPoolEventListener;
import com.gramajo.josue.quinielasvergas.Helpers.OnPoolEventListener;
import com.gramajo.josue.quinielasvergas.Helpers.QuinielaUtils;
import com.gramajo.josue.quinielasvergas.Objects.Game;
import com.gramajo.josue.quinielasvergas.Objects.Games;
import com.gramajo.josue.quinielasvergas.Objects.Review;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * Created by josuegramajo on 6/18/18.
 */

public class ReviewActivity extends AppCompatActivity{

    RecyclerView recyclerView;

    ReviewAdapter adapter;

    TextView user;

    ArrayList<Review> list = new ArrayList<>();

    SpotsDialog dialog;

    ArrayList<Game> masterGames = new ArrayList<>();

    ArrayList<Game> userGames = new ArrayList<>();

    String theUser = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        recyclerView = findViewById(R.id.rv_review);

        Bundle b = this.getIntent().getExtras();
        theUser = b.getString("user");

        user = findViewById(R.id.tv_review_user);

        dialog = (SpotsDialog) new SpotsDialog.Builder().setContext(this).build();

        adapter = new ReviewAdapter(this, list);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.setBackgroundColor(getResources().getColor(R.color.white));

        final FirebaseUtils firebase = new FirebaseUtils();
        firebase.setOnMasterPoolListener(new OnMasterPoolEventListener() {
            @Override
            public void onMasterPoolSuccess(List<Game> games) {
                dialog.dismiss();

                masterGames.clear();

                for(Game g : games){
                    masterGames.add(g);
                }

                dialog.show();
                firebase.getSpecificPool(ReviewActivity.this, theUser);
            }
        });
        firebase.setOnPoolEventListener(new OnPoolEventListener() {
            @Override
            public void onPoolSuccess(Games games) {
                dialog.dismiss();

                userGames.clear();
                for(Game game : games.getGames()){
                    userGames.add(game);
                }

                makeList();
            }
        });

        dialog.show();
        firebase.getMasterPool();
    }

    private void makeList(){
        list.clear();
        Review rev;

        for(Game g : masterGames){
            rev = new Review();
            rev.setGame(g);

            Game userGame = getGameById(g.getId());
            if(userGame != null){
                rev.setPoolFirstScore(userGame.getFirstTeamScore());
                rev.setPoolSecondScore(userGame.getSecondTeamScore());
            }

            rev.setPoints(QuinielaUtils.INSTANCE.getPointsIndividualGame(g, userGame));

            user.setText(theUser + " - " + QuinielaUtils.INSTANCE.getPoints(masterGames, userGames) + " puntos");

            list.add(rev);
        }

        adapter.notifyDataSetChanged();
    }

    private Game getGameById(int id){
        for(Game g : userGames){
            if(g.getId() == id){
                return g;
            }
        }
        return null;
    }
}
