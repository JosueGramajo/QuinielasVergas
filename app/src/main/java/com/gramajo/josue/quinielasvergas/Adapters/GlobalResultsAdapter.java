package com.gramajo.josue.quinielasvergas.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gramajo.josue.quinielasvergas.Helpers.DateUtils;
import com.gramajo.josue.quinielasvergas.Objects.Game;
import com.gramajo.josue.quinielasvergas.R;

import java.util.Date;
import java.util.List;

/**
 * Created by josue on 16/06/2018.
 */

public class GlobalResultsAdapter extends RecyclerView.Adapter<GlobalResultsAdapter.MyViewHolder> {
    private List<Game> list;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView team1, team2;
        public TextView date, score1, score2;

        public MyViewHolder(View view) {
            super(view);
            team1 = (ImageView) view.findViewById(R.id.iv_global_team1);
            team2 = (ImageView) view.findViewById(R.id.iv_global_team2);
            score1 = (TextView) view.findViewById(R.id.tv_global_score1);
            score2 = (TextView) view.findViewById(R.id.tv_global_score2);
            date = view.findViewById(R.id.tv_global_date);
        }
    }

    public GlobalResultsAdapter(Context context, List<Game> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public GlobalResultsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.global_results_cell, parent, false);

        return new GlobalResultsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GlobalResultsAdapter.MyViewHolder holder, final int position) {
        Game game = list.get(position);

        int id1 = context.getResources().getIdentifier("com.gramajo.josue.quinielasvergas:drawable/" + game.getFirstTeam().getFlag(), null, null);
        holder.team1.setImageResource(id1);

        int id2 = context.getResources().getIdentifier("com.gramajo.josue.quinielasvergas:drawable/" + game.getSecondTeam().getFlag(), null, null);
        holder.team2.setImageResource(id2);

        holder.date.setText(game.getDate());

        holder.score1.setGravity(Gravity.CENTER);
        holder.score2.setGravity(Gravity.CENTER);

        Date gameDate = DateUtils.INSTANCE.stringToDate(game.getDate());
        Date currentDate = DateUtils.INSTANCE.stringToDate(DateUtils.INSTANCE.getCurrentDate());

        if(game.getFirstTeamScore() != null || game.getSecondTeamScore() != null){
            holder.score1.setText(game.getFirstTeamScore().toString());
            holder.score2.setText(game.getSecondTeamScore().toString());
        }else{
            holder.score1.setText("");
            holder.score2.setText("");
        }
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
