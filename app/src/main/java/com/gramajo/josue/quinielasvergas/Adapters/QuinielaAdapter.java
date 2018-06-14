package com.gramajo.josue.quinielasvergas.Adapters;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
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

    public QuinielaAdapter(List<Game> list) {
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_cell, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Game game = list.get(position);
        holder.team1.setImageResource(game.getFirstTeam().getFlag());
        holder.team1.setImageResource(game.getSecondTeam().getFlag());
        holder.date.setText(game.getDate());

        if(game.getFirstTeamScore() != null && game.getSecondTeamScore() != null){
            holder.score1.setText(game.getFirstTeamScore().toString());
            holder.score1.setEnabled(false);

            holder.score2.setText(game.getSecondTeamScore().toString());
            holder.score2.setEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
