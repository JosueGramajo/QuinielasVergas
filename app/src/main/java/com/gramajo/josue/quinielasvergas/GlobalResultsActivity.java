package com.gramajo.josue.quinielasvergas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gramajo.josue.quinielasvergas.Adapters.GlobalResultsAdapter;
import com.gramajo.josue.quinielasvergas.Helpers.FirebaseUtils;
import com.gramajo.josue.quinielasvergas.Helpers.OnMasterPoolEventListener;
import com.gramajo.josue.quinielasvergas.Objects.Game;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * Created by josue on 16/06/2018.
 */

public class GlobalResultsActivity extends AppCompatActivity {

    ArrayList<Game> list = new ArrayList<>();

    RecyclerView recyclerView;

    GlobalResultsAdapter adapter;

    SpotsDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_results);

        dialog = (SpotsDialog) new SpotsDialog.Builder().setContext(this).build();

        recyclerView = findViewById(R.id.rv_global_results);

        adapter = new GlobalResultsAdapter(this, list);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.setBackgroundColor(getResources().getColor(R.color.white));

        FirebaseUtils firebase = new FirebaseUtils();
        firebase.setOnMasterPoolListener(new OnMasterPoolEventListener() {
            @Override
            public void onMasterPoolSuccess(List<Game> games) {
                dialog.dismiss();

                list.clear();

                for(Game g : games){
                    list.add(g);
                }

                adapter.notifyDataSetChanged();
            }
        });

        dialog.show();
        firebase.getMasterPool();
    }
}
