package com.gramajo.josue.quinielasvergas.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.gramajo.josue.quinielasvergas.Helpers.FirebaseUtils;
import com.gramajo.josue.quinielasvergas.Helpers.OnAdminAdapterEventListener;
import com.gramajo.josue.quinielasvergas.Objects.CurrentGame;
import com.gramajo.josue.quinielasvergas.Objects.FinalsGame;
import com.gramajo.josue.quinielasvergas.Objects.Game;
import com.gramajo.josue.quinielasvergas.R;

import java.util.List;

/**
 * Created by josuegramajo on 6/26/18.
 */

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.MyViewHolder> {
    private List<CurrentGame> list;
    private Context context;

    OnAdminAdapterEventListener listener;

    public void setListener(OnAdminAdapterEventListener listener) {
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView team1, team2;
        public RadioButton rbTeam1, rbTeam2;
        public CheckBox penalties;
        public LinearLayout finals;
        public ElegantNumberButton score1, score2;
        public Button save;

        public MyViewHolder(View view) {
            super(view);
            team1 = view.findViewById(R.id.admin_team1);
            team2 = view.findViewById(R.id.admin_team2);
            rbTeam1 = view.findViewById(R.id.admin_rb_team1);
            rbTeam2 = view.findViewById(R.id.admin_rb_team2);
            penalties = view.findViewById(R.id.admin_penalties);
            finals = view.findViewById(R.id.finals_view);
            score1 = (ElegantNumberButton) view.findViewById(R.id.admin_score1);
            score2 = (ElegantNumberButton) view.findViewById(R.id.admin_score2);
            save = view.findViewById(R.id.admin_save);
        }
    }

    public AdminAdapter(Context context, List<CurrentGame> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public AdminAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_cell, parent, false);
        return new AdminAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AdminAdapter.MyViewHolder holder, final int position) {

        if(list.get(position).getType().equals("finals")){
            FinalsGame game = list.get(position).getFinalsGame();

            holder.team1.setText(game.getFirstTeam().getName());
            holder.team2.setText(game.getSecondTeam().getName());

            if(game.getFirstTeamScore() != null){
                holder.score1.setNumber(String.valueOf(game.getFirstTeamScore()));
            }
            if(game.getSecondTeamScore() != null){
                holder.score2.setNumber(String.valueOf(game.getSecondTeamScore()));
            }

            holder.score1.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
                @Override
                public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                    list.get(position).getFinalsGame().setFirstTeamScore(Integer.parseInt(view.getNumber()));
                }
            });
            holder.score2.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
                @Override
                public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                    list.get(position).getFinalsGame().setSecondTeamScore(Integer.parseInt(view.getNumber()));
                }
            });

            holder.finals.setVisibility(View.VISIBLE);

            holder.penalties.setChecked(game.isPenalties());

            holder.rbTeam1.setText(game.getFirstTeam().getName());
            holder.rbTeam2.setText(game.getSecondTeam().getName());

            if(holder.rbTeam1.getText().equals(game.getPenaltiesWinner())){
                holder.rbTeam1.setChecked(true);
            }else if(holder.rbTeam2.getText().equals(game.getPenaltiesWinner())){
                holder.rbTeam2.setChecked(true);
            }

            holder.rbTeam1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        list.get(position).getFinalsGame().setPenaltiesWinner(holder.rbTeam1.getText().toString());
                    }
                }
            });
            holder.rbTeam2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        list.get(position).getFinalsGame().setPenaltiesWinner(holder.rbTeam2.getText().toString());
                    }
                }
            });
            holder.penalties.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    list.get(position).getFinalsGame().setPenalties(b);
                }
            });

            holder.save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onButtonPressed(list.get(position).getFinalsGame());
                }
            });

        }else if(list.get(position).getType().equals("groups")){
            Game game = list.get(position).getGroupGame();

            holder.team1.setText(game.getFirstTeam().getName());
            holder.team2.setText(game.getSecondTeam().getName());

            if(game.getFirstTeamScore() != null){
                holder.score1.setNumber(String.valueOf(game.getFirstTeamScore()));
            }
            if(game.getSecondTeamScore() != null){
                holder.score2.setNumber(String.valueOf(game.getSecondTeamScore()));
            }
            holder.score1.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
                @Override
                public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                    list.get(position).getGroupGame().setFirstTeamScore(Integer.parseInt(view.getNumber()));
                }
            });
            holder.score2.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
                @Override
                public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                    list.get(position).getGroupGame().setSecondTeamScore(Integer.parseInt(view.getNumber()));
                }
            });

            holder.finals.setVisibility(View.GONE);

            holder.save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onButtonPressed(list.get(position).getGroupGame());
                }
            });
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
