package com.gramajo.josue.quinielasvergas;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.gramajo.josue.quinielasvergas.Adapters.QuinielaAdapter;
import com.gramajo.josue.quinielasvergas.Helpers.FirebaseUtils;
import com.gramajo.josue.quinielasvergas.Helpers.Global;
import com.gramajo.josue.quinielasvergas.Helpers.OnFirestoreEventListener;
import com.gramajo.josue.quinielasvergas.Helpers.OnPoolEventListener;
import com.gramajo.josue.quinielasvergas.Objects.Game;
import com.gramajo.josue.quinielasvergas.Objects.Games;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

/**
 * Created by josuegramajo on 6/14/18.
 */

public class QuinielaActivity extends AppCompatActivity{

    ArrayList<Game> list = new ArrayList<Game>();
    RecyclerView recyclerView;
    QuinielaAdapter adapter;
    SpotsDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiniela);

        dialog = (SpotsDialog) new SpotsDialog.Builder().setContext(this).build();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_save);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(QuinielaActivity.this);
                alert.setMessage("Desea guardar? (Ahora se puede editar la cantidad de veces que sea el partido, hasta 1 dia antes del mismo)");
                alert.setTitle("Confirmacion");

                alert.setPositiveButton("Dale", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, int whichButton) {

                        FirebaseUtils firebase = new FirebaseUtils();
                        firebase.setOnFirestoreEventListener(new OnFirestoreEventListener() {
                            @Override
                            public void onFirebaseSuccessfulSaving() {
                                QuinielaActivity.this.dialog.dismiss();
                                Toast.makeText(QuinielaActivity.this, "Datos guardados exitosmanete", Toast.LENGTH_SHORT).show();
                                adapter.notifyDataSetChanged();
                            }
                        });
                        QuinielaActivity.this.dialog.show();
                        firebase.checkForExistingDocument(QuinielaActivity.this, new Games(adapter.getList(), Global.globalUser));


                        //FirebaseUtils.INSTANCE.saveMasterPool(QuinielaActivity.this, new Games(adapter.getList(), Global.globalUser));
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });

                alert.show();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        adapter = new QuinielaAdapter(this, list);
        //adapter = new QuinielaAdapter(this, Global.initList());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.setBackgroundColor(getResources().getColor(R.color.white));

        FirebaseUtils firebase = new FirebaseUtils();
        firebase.setOnPoolEventListener(new OnPoolEventListener() {
            @Override
            public void onPoolSuccess(Games games) {
                dialog.dismiss();

                list.clear();
                for(Game game : games.getGames()){
                    list.add(game);
                }

                adapter.notifyDataSetChanged();
            }
        });
        dialog.show();
        firebase.getSpecificPool(this, Global.globalUser);

    }

}
