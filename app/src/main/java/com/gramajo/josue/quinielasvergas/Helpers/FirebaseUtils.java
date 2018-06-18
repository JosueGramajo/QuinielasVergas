package com.gramajo.josue.quinielasvergas.Helpers;

/**
 * Created by josuegramajo on 6/15/18.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.gramajo.josue.quinielasvergas.Objects.Games;
import com.gramajo.josue.quinielasvergas.Objects.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseUtils {
    public static FirebaseUtils INSTANCE = new FirebaseUtils();
    private final String quinielaCollectionID = "Quiniela";

    private final String userCollectionID = "users";
    private final String userID = "user";
    private final String passID = "password";

    private final String masterID = "Master";
    private final String masterPoolID = "masterPool";

    private final String sampleID = "Sample";
    private final String quinielaSampleID = "QuinielaSample";

    private OnEventListener mOnEventListener;

    private OnPoolEventListener mOnPoolEventListener;

    private OnRankingEventListener mOnRankingEventListener;

    private OnFirestoreEventListener mOnFirestoreEventListener;

    public void setOnFirestoreEventListener(OnFirestoreEventListener mOnFirestoreEventListener) {
        this.mOnFirestoreEventListener = mOnFirestoreEventListener;
    }

    public void setOnEventListener(OnEventListener listener) {
        mOnEventListener = listener;
    }

    public void setOnPoolEventListener(OnPoolEventListener mOnPoolEventListener) {
        this.mOnPoolEventListener = mOnPoolEventListener;
    }

    public void setOnRankingEventListener(OnRankingEventListener mOnRankingEventListener) {
        this.mOnRankingEventListener = mOnRankingEventListener;
    }

    OnMasterPoolEventListener listener;
    public void setOnMasterPoolListener(OnMasterPoolEventListener listener){
        this.listener = listener;
    }

    public void register(final Context context, String user, String pass){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> dc = new HashMap<>();
        dc.put(userID, user);
        dc.put(passID, pass);

        db.collection(userCollectionID)
                .add(dc)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(context, "Registro exitoso :D", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    public void login(final Context context, final String user, final String pass){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference questionRef = db.collection(userCollectionID);
        questionRef.whereEqualTo("user", user).whereEqualTo("password", pass).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()) {
                    Toast.makeText(context, "Usuario o contraseña invalidos", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    Toast.makeText(context, "Login exitoso :D", Toast.LENGTH_LONG).show();
                    Global.globalUser = user;
                    mOnEventListener.onFirestoreLoginSuccess();
                    return;
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Usuario o contraseña invalidos", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void saveGamesInFirestore(final Context context, Games games, String documentID){
        if(documentID == null){
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection(quinielaCollectionID).document().set(games).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    mOnFirestoreEventListener.onFirebaseSuccessfulSaving();
                }
            });
        }else{
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection(quinielaCollectionID).document(documentID).set(games).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    mOnFirestoreEventListener.onFirebaseSuccessfulSaving();
                }
            });
        }


    }

    public void getSpecificPool(final Context context, final String user){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference questionRef = db.collection(quinielaCollectionID);
        questionRef.whereEqualTo("user", user).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()) {
                    getSamplePool();
                } else {
                    Games games = queryDocumentSnapshots.toObjects(Games.class).get(0);
                    mOnPoolEventListener.onPoolSuccess(games);
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Usuario o contraseña invalidos", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getMasterPool(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference ref = db.collection(masterID).document(masterPoolID);
        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Games games = documentSnapshot.toObject(Games.class);
                listener.onMasterPoolSuccess(games.getGames());
            }
        });
    }

    public void getSamplePool(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference ref = db.collection(sampleID).document(quinielaSampleID);
        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Games games = documentSnapshot.toObject(Games.class);
                mOnPoolEventListener.onPoolSuccess(games);
            }
        });
    }

    public void saveMasterPool(final Context context, Games games){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(sampleID).document(quinielaSampleID).set(games).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(context, "Datos guardados exitosmanete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getPools(final Context context){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference ref = db.collection(quinielaCollectionID);
        ref.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshot) {
                List<Games> games = documentSnapshot.toObjects(Games.class);
                getMasterPoolForEvaluation(games);
            }
        });
    }
    public void getMasterPoolForEvaluation(final List<Games> games){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference ref = db.collection(masterID).document(masterPoolID);
        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Games masterGames = documentSnapshot.toObject(Games.class);

                ArrayList<Point> points = new ArrayList<>();

                for(Games g : games){
                    int p = QuinielaUtils.INSTANCE.getPoints(masterGames, g);

                    points.add(new Point(p, g.getUser()));
                }

                mOnRankingEventListener.onRankingCalculationSuccess(points);
            }
        });
    }

    public void checkForExistingDocument(final Context context, final Games games){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference questionRef = db.collection(quinielaCollectionID);
        questionRef.whereEqualTo("user", Global.globalUser).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                String id = null;

                if (!queryDocumentSnapshots.isEmpty()) {
                    id = queryDocumentSnapshots.getDocuments().get(0).getId();
                }

                saveGamesInFirestore(context, games, id);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Usuario o contraseña invalidos", Toast.LENGTH_LONG).show();
            }
        });
    }
}

