package com.gramajo.josue.quinielasvergas;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gramajo.josue.quinielasvergas.Adapters.FinalsAdapter;
import com.gramajo.josue.quinielasvergas.Adapters.FinalsReviewAdapter;
import com.gramajo.josue.quinielasvergas.Helpers.FirebaseUtils;
import com.gramajo.josue.quinielasvergas.Helpers.Global;
import com.gramajo.josue.quinielasvergas.Helpers.OnFinalsPoolEventListener;
import com.gramajo.josue.quinielasvergas.Helpers.OnFinalsReviewEventListener;
import com.gramajo.josue.quinielasvergas.Helpers.QuinielaUtils;
import com.gramajo.josue.quinielasvergas.Objects.FinalsGame;
import com.gramajo.josue.quinielasvergas.Objects.FinalsGames;
import com.gramajo.josue.quinielasvergas.Objects.FinalsReview;
import com.gramajo.josue.quinielasvergas.Objects.Game;
import com.gramajo.josue.quinielasvergas.Objects.Review;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

/**
 * Created by josue on 20/06/2018.
 */

public class FinalsReviewActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    FinalsReviewAdapter adapter;

    ArrayList<FinalsReview> list = new ArrayList<>();

    FinalsGames finalsGames;
    FinalsGames poolGames;

    SpotsDialog dialog;

    String theUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finals_review);

        recyclerView = findViewById(R.id.rv_finals_review);

        adapter = new FinalsReviewAdapter(this, list);

        dialog = (SpotsDialog) new SpotsDialog.Builder().setContext(this).build();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.setBackgroundColor(getResources().getColor(R.color.white));

        Bundle b = this.getIntent().getExtras();
        theUser = b.getString("user");

        final FirebaseUtils firebase = new FirebaseUtils();
        firebase.setOnFinalsReviewEventListener(new OnFinalsReviewEventListener() {
            @Override
            public void onFinalsReviewSuccess(FinalsGames games) {
                dialog.dismiss();
                finalsGames = games;

                makeList();
            }
        });

        firebase.setOnFinalsPoolEventListener(new OnFinalsPoolEventListener() {
            @Override
            public void onFinalsPoolSuccess(FinalsGames games, boolean empty) {
                dialog.dismiss();
                poolGames = games;
                dialog.show();
                firebase.getFinalsPoolForReview();
            }
        });
        dialog.show();
        firebase.getSpecificFinalsPool(this, theUser);
    }

    private void makeList(){
        list.clear();
        FinalsReview rev;

        for(FinalsGame g : finalsGames.getGames()){
            rev = new FinalsReview();
            rev.setGame(g);

            FinalsGame userGame = getGameById(g.getId());
            if(userGame != null){
                rev.setPoolFirstScore(userGame.getFirstTeamScore());
                rev.setPoolSecondScore(userGame.getSecondTeamScore());
                rev.setPoolPenalties(userGame.isPenalties());
                rev.setPoolPenaltiesWinner(userGame.getPenaltiesWinner());
            }

            rev.setPoints(QuinielaUtils.INSTANCE.getPointsIndividualFinalsGame(g, userGame));

            list.add(rev);
        }

        adapter.notifyDataSetChanged();
    }

    private FinalsGame getGameById(int id){
        for(FinalsGame g : poolGames.getGames()){
            if(g.getId() == id){
                return g;
            }
        }
        return null;
    }
}
