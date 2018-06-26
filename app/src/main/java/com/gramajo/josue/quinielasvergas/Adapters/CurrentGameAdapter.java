package com.gramajo.josue.quinielasvergas.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gramajo.josue.quinielasvergas.Helpers.DateUtils;
import com.gramajo.josue.quinielasvergas.Objects.CurrentGame;
import com.gramajo.josue.quinielasvergas.Objects.FinalsGame;
import com.gramajo.josue.quinielasvergas.Objects.Game;
import com.gramajo.josue.quinielasvergas.R;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

/**
 * Created by josuegramajo on 6/25/18.
 */

public class CurrentGameAdapter extends RecyclerView.Adapter<CurrentGameAdapter.MyViewHolder> {
private List<CurrentGame> list;
private Context context;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public ImageView team1, team2;
    public TextView score1, score2, teamName1, teamName2, descripcion;
    public LinearLayout pool;

    public MyViewHolder(View view) {
        super(view);
        team1 = (ImageView) view.findViewById(R.id.current_team1);
        team2 = (ImageView) view.findViewById(R.id.current_team2);
        score1 = (TextView) view.findViewById(R.id.current_team1_score);
        score2 = (TextView) view.findViewById(R.id.current_team2_score);
        teamName1 = (TextView) view.findViewById(R.id.current_team1_name);
        teamName2 = (TextView) view.findViewById(R.id.current_team2_name);
        pool = (LinearLayout) view.findViewById(R.id.ll_players_pool);
        descripcion = view.findViewById(R.id.descripcion);
    }
}

    public CurrentGameAdapter(Context context, List<CurrentGame> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public CurrentGameAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.current_game_cell, parent, false);
        return new CurrentGameAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CurrentGameAdapter.MyViewHolder holder, final int position) {

        if(list.get(position).getType().equals("finals")){
            FinalsGame game = list.get(position).getFinalsGame();

            int id1 = context.getResources().getIdentifier("com.gramajo.josue.quinielasvergas:drawable/" + game.getFirstTeam().getFlag() + "_big", null, null);
            holder.team1.setImageResource(id1);

            int id2 = context.getResources().getIdentifier("com.gramajo.josue.quinielasvergas:drawable/" + game.getSecondTeam().getFlag() + "_big", null, null);
            holder.team2.setImageResource(id2);

            holder.teamName1.setText(game.getFirstTeam().getName());
            holder.teamName2.setText(game.getSecondTeam().getName());

            if(game.getFirstTeamScore() != null){
                holder.score1.setText(String.valueOf(game.getFirstTeamScore()));
            }
            if(game.getSecondTeamScore() != null){
                holder.score2.setText(String.valueOf(game.getSecondTeamScore()));
            }

            if(game.isPenalties()){
                holder.descripcion.setText(game.getPenaltiesWinner() + " gano en penales");
            }


            for(String s : list.get(position).getUserResults()){
                TextView textView = new TextView(context);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(15, 5, 15, 5);
                textView.setText(Html.fromHtml(s));
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER);

                holder.pool.addView(textView);
            }
        }else if(list.get(position).getType().equals("groups")){
            Game game = list.get(position).getGroupGame();

            int id1 = context.getResources().getIdentifier("com.gramajo.josue.quinielasvergas:drawable/" + game.getFirstTeam().getFlag() + "_big", null, null);
            holder.team1.setImageResource(id1);

            int id2 = context.getResources().getIdentifier("com.gramajo.josue.quinielasvergas:drawable/" + game.getSecondTeam().getFlag() + "_big", null, null);
            holder.team2.setImageResource(id2);

            holder.teamName1.setText(game.getFirstTeam().getName());
            holder.teamName2.setText(game.getSecondTeam().getName());

            if(game.getFirstTeamScore() != null){
                holder.score1.setText(String.valueOf(game.getFirstTeamScore()));
            }
            if(game.getSecondTeamScore() != null){
                holder.score2.setText(String.valueOf(game.getSecondTeamScore()));
            }


            for(String s : list.get(position).getUserResults()){
                TextView textView = new TextView(context);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(15, 5, 15, 5);
                textView.setText(Html.fromHtml(s));
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER);

                holder.pool.addView(textView);
            }
        }


    }

    public List<CurrentGame> getList(){
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
