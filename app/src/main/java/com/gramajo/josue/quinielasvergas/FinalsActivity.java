package com.gramajo.josue.quinielasvergas;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.gramajo.josue.quinielasvergas.Adapters.FinalsAdapter;
import com.gramajo.josue.quinielasvergas.Helpers.FirebaseUtils;
import com.gramajo.josue.quinielasvergas.Helpers.Global;
import com.gramajo.josue.quinielasvergas.Helpers.OnFinalsGamesSavingEventListener;
import com.gramajo.josue.quinielasvergas.Helpers.OnFinalsPoolEventListener;
import com.gramajo.josue.quinielasvergas.Helpers.OnFirestoreEventListener;
import com.gramajo.josue.quinielasvergas.Objects.FinalsGame;
import com.gramajo.josue.quinielasvergas.Objects.FinalsGames;
import com.gramajo.josue.quinielasvergas.Objects.Games;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

/**
 * Created by josuegramajo on 6/20/18.
 */

public class FinalsActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    FinalsAdapter adapter;

    ArrayList<FinalsGame> list = new ArrayList<>();

    FloatingActionButton fab;

    SpotsDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finals);

        dialog = (SpotsDialog) new SpotsDialog.Builder().setContext(this).build();

        fab = (FloatingActionButton) findViewById(R.id.fab_save_finals);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(FinalsActivity.this);
                alert.setMessage("Desea Guardar?");
                alert.setTitle("Confirmacion");

                alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, int whichButton) {
                        FirebaseUtils firebase = new FirebaseUtils();
                        firebase.setOnFinalsGamesSavingEventListener(new OnFinalsGamesSavingEventListener() {
                            @Override
                            public void onFinalsGamesSuccessSaving() {
                                FinalsActivity.this.dialog.dismiss();
                                Toast.makeText(FinalsActivity.this, "Datos guardados exitosmanete", Toast.LENGTH_SHORT).show();
                                adapter.notifyDataSetChanged();
                            }
                        });
                        FinalsActivity.this.dialog.show();
                        firebase.checkForExistingFinalsDocument(FinalsActivity.this, new FinalsGames(adapter.getList(), Global.globalUser));
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });

                alert.show();
            }
        });

        recyclerView = findViewById(R.id.rv_finals);

        adapter = new FinalsAdapter(this, list);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setBackgroundColor(getResources().getColor(R.color.white));
        recyclerView.setAdapter(adapter);

        FirebaseUtils firebase = new FirebaseUtils();
        firebase.setOnFinalsPoolEventListener(new OnFinalsPoolEventListener() {
            @Override
            public void onFinalsPoolSuccess(FinalsGames games, boolean empty) {
                dialog.dismiss();

                list.clear();

                for(FinalsGame g : games.getGames()){
                    list.add(g);
                }

                if(empty){
                    for(int i = 0;i<list.size();i++){
                        list.get(i).setFirstTeamScore(null);
                        list.get(i).setSecondTeamScore(null);
                        list.get(i).setPenalties(false);
                    }
                }

                adapter.notifyDataSetChanged();
            }
        });
        dialog.show();
        firebase.getSpecificFinalsPool( this, Global.globalUser);
    }
}
