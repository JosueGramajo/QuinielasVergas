package com.gramajo.josue.quinielasvergas.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gramajo.josue.quinielasvergas.Objects.Game;
import com.gramajo.josue.quinielasvergas.R;

import java.util.List;

/**
 * Created by josuegramajo on 6/14/18.
 */

public class QuinielaAdapter extends RecyclerView.Adapter<QuinielaAdapter.MyViewHolder> {
    private List<Game> list;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView team1, team2;
        public EditText score1, score2;
        public TextView date;

        public MyViewHolder(View view) {
            super(view);
            team1 = (ImageView) view.findViewById(R.id.iv_team1);
            team2 = (ImageView) view.findViewById(R.id.iv_team2);
            score1 = (EditText) view.findViewById(R.id.et_score1);
            score2 = (EditText) view.findViewById(R.id.et_score2);
            date = view.findViewById(R.id.tv_date);
        }
    }

    public QuinielaAdapter(Context context, List<Game> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_cell, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {
        Game game = list.get(position);

        int id1 = context.getResources().getIdentifier("com.gramajo.josue.quinielasvergas:drawable/" + game.getFirstTeam().getFlag(), null, null);
        holder.team1.setImageResource(id1);

        int id2 = context.getResources().getIdentifier("com.gramajo.josue.quinielasvergas:drawable/" + game.getSecondTeam().getFlag(), null, null);
        holder.team2.setImageResource(id2);

        holder.date.setText(game.getDate());

        holder.score1.setGravity(Gravity.CENTER);
        holder.score2.setGravity(Gravity.CENTER);

        if(game.getFirstTeamScore() != null && game.getSecondTeamScore() != null){
            holder.score1.setText(game.getFirstTeamScore().toString());
            holder.score1.setEnabled(false);

            holder.score2.setText(game.getSecondTeamScore().toString());
            holder.score2.setEnabled(false);
        }

        holder.score1.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    list.get(position).setFirstTeamScore(null);
                }else{
                    list.get(position).setFirstTeamScore(Integer.parseInt(s.toString()));
                }

            }
        });

        holder.score2.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    list.get(position).setSecondTeamScore(null);
                }else{
                    list.get(position).setSecondTeamScore(Integer.parseInt(s.toString()));
                }

            }
        });

        if(!game.isActive()){
            holder.score1.setBackgroundColor(context.getResources().getColor(R.color.gray));
            holder.score1.setEnabled(false);

            holder.score2.setBackgroundColor(context.getResources().getColor(R.color.gray));
            holder.score2.setEnabled(false);
        }
    }

    public List<Game> getList(){
        return list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
