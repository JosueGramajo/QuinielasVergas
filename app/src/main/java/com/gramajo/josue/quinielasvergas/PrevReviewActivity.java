package com.gramajo.josue.quinielasvergas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gramajo.josue.quinielasvergas.Adapters.PrevReviewAdapter;
import com.gramajo.josue.quinielasvergas.Helpers.FirebaseUtils;
import com.gramajo.josue.quinielasvergas.Helpers.OnRankingEventListener;
import com.gramajo.josue.quinielasvergas.Helpers.OnUserEventListener;
import com.gramajo.josue.quinielasvergas.Objects.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import dmax.dialog.SpotsDialog;

/**
 * Created by josuegramajo on 6/18/18.
 */

public class PrevReviewActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    PrevReviewAdapter adapter;

    ArrayList<String> list = new ArrayList<>();

    SpotsDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prev_review);

        recyclerView = findViewById(R.id.rv_prev_review);

        dialog = (SpotsDialog) new SpotsDialog.Builder().setContext(this).build();

        adapter = new PrevReviewAdapter(this, list);
        adapter.setListener(new OnUserEventListener() {
            @Override
            public void onUserSelected(String user) {
                moveToReview(user);
            }
        });

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setBackgroundColor(getResources().getColor(R.color.white));
        recyclerView.setAdapter(adapter);

        FirebaseUtils firebase = new FirebaseUtils();
        firebase.setOnRankingEventListener(new OnRankingEventListener() {
            @Override
            public void onRankingCalculationSuccess(ArrayList<Point> points) {
                dialog.dismiss();

                list.clear();
                for(Point p : points){
                    list.add(p.getUser());
                }

                adapter.notifyDataSetChanged();
            }
        });
        dialog.show();
        firebase.getPools();
    }

    private void moveToReview(String user){
        Intent intent = new Intent(this, ReviewActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
