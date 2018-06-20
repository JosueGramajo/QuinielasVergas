package com.gramajo.josue.quinielasvergas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gramajo.josue.quinielasvergas.Adapters.RankingAdapter;
import com.gramajo.josue.quinielasvergas.Helpers.FirebaseUtils;
import com.gramajo.josue.quinielasvergas.Helpers.OnRankingEventListener;
import com.gramajo.josue.quinielasvergas.Objects.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import dmax.dialog.SpotsDialog;

/**
 * Created by josuegramajo on 6/15/18.
 */

public class RankingActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RankingAdapter adapter;

    ArrayList<Point> list = new ArrayList<>();

    SpotsDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        dialog = (SpotsDialog) new SpotsDialog.Builder().setContext(this).build();

        recyclerView = findViewById(R.id.rv_ranking);

        adapter = new RankingAdapter(this, list);

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
                    list.add(p);
                }

                Collections.sort(list, new Comparator<Point>() {
                    @Override
                    public int compare(Point point1, Point point2) {
                        if(point1.getPoints() < point2.getPoints()) return 1;
                        else if(point1.getPoints() > point2.getPoints()) return -1;
                        else return 0;
                    }
                });

                adapter.notifyDataSetChanged();
            }
        });
        dialog.show();
        firebase.getPools();

    }
}
