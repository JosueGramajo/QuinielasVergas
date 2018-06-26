package com.gramajo.josue.quinielasvergas.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.gramajo.josue.quinielasvergas.Helpers.DateUtils;
import com.gramajo.josue.quinielasvergas.Objects.FinalsGame;
import com.gramajo.josue.quinielasvergas.Objects.Game;
import com.gramajo.josue.quinielasvergas.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by josuegramajo on 6/20/18.
 */

public class FinalsAdapter extends RecyclerView.Adapter<FinalsAdapter.MyViewHolder> {
    private List<FinalsGame> list;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView team1, team2;
        public EditText score1, score2;
        public TextView info, header, name1, name2;
        public View penaltiesView;
        public CheckBox penalties;
        public RadioButton team1P, team2P;

        public MyViewHolder(View view) {
            super(view);
            team1 = view.findViewById(R.id.iv_finals_team1);
            team2 = view.findViewById(R.id.iv_finals_team2);
            score1 = view.findViewById(R.id.et_finals_score1);
            score2 = view.findViewById(R.id.et_finals_score2);
            info = view.findViewById(R.id.tv_finals_info);
            header = view.findViewById(R.id.tv_header);
            penaltiesView = view.findViewById(R.id.v_penalties_winner);
            penalties = view.findViewById(R.id.cb_finals_penalties);
            team1P = view.findViewById(R.id.rb_team1);
            team2P = view.findViewById(R.id.rb_team2);
            name1 = view.findViewById(R.id.tv_finals_team1_name);
            name2 = view.findViewById(R.id.tv_finals_team2_name);
        }
    }

    public FinalsAdapter(Context context, List<FinalsGame> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public FinalsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.finals_cell, parent, false);

        return new FinalsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final FinalsAdapter.MyViewHolder holder, final int position) {
        FinalsGame game = list.get(position);

        if(game.getGameType().contains("$")){
            holder.header.setVisibility(View.VISIBLE);
            holder.header.setText(game.getGameType().replace("$","   "));
        }else{
            holder.header.setVisibility(View.GONE);
        }

        if (game.getFirstTeam().getFlag().equals("undefined") || game.getSecondTeam().getFlag().equals("undefined")){
            holder.score1.setEnabled(false);
            holder.score2.setEnabled(false);
            holder.penalties.setEnabled(false);
        }

        int id1 = context.getResources().getIdentifier("com.gramajo.josue.quinielasvergas:drawable/" + game.getFirstTeam().getFlag() + "_big", null, null);
        holder.team1.setImageResource(id1);

        int id2 = context.getResources().getIdentifier("com.gramajo.josue.quinielasvergas:drawable/" + game.getSecondTeam().getFlag() + "_big", null, null);
        holder.team2.setImageResource(id2);

        holder.name1.setText(game.getFirstTeam().getName());
        holder.name2.setText(game.getSecondTeam().getName());

        holder.info.setText(game.getDate() + " - " +game.getHour());

        holder.score1.setGravity(Gravity.CENTER);
        holder.score2.setGravity(Gravity.CENTER);

        Date gameDate = DateUtils.INSTANCE.stringToDate(game.getDate());
        Date currentDate = DateUtils.INSTANCE.stringToDate(DateUtils.INSTANCE.getCurrentDate());

        if(gameDate.equals(currentDate) || gameDate.before(currentDate)){
            if(game.getFirstTeamScore() == null || game.getSecondTeamScore() == null){
                holder.score1.setBackgroundColor(context.getResources().getColor(R.color.gray));
                holder.score1.setEnabled(false);

                holder.score2.setBackgroundColor(context.getResources().getColor(R.color.gray));
                holder.score2.setEnabled(false);
            }else{
                holder.score1.setText(game.getFirstTeamScore().toString());
                holder.score1.setEnabled(false);

                holder.score2.setText(game.getSecondTeamScore().toString());
                holder.score2.setEnabled(false);
            }
        }else{
            if(game.getFirstTeamScore() != null || game.getSecondTeamScore() != null){
                holder.score1.setText(game.getFirstTeamScore().toString());
                holder.score1.setEnabled(true);

                holder.score2.setText(game.getSecondTeamScore().toString());
                holder.score2.setEnabled(true);
            }
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

        holder.penalties.setChecked(game.isPenalties());

        holder.penalties.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    holder.penaltiesView.setVisibility(View.VISIBLE);
                }else{
                    holder.penaltiesView.setVisibility(View.INVISIBLE);
                }

                list.get(position).setPenalties(b);
            }
        });

        holder.team1P.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    list.get(position).setPenaltiesWinner(holder.team1P.getText().toString());
                }else{
                    list.get(position).setPenaltiesWinner(holder.team2P.getText().toString());
                }
            }
        });

        if(game.isPenalties()){
            holder.penaltiesView.setVisibility(View.VISIBLE);
        }else{
            holder.penaltiesView.setVisibility(View.INVISIBLE);
        }

        holder.team1P.setText(game.getFirstTeam().getName());
        holder.team2P.setText(game.getSecondTeam().getName());

        if(game.getPenaltiesWinner() != ""){
            if(holder.team1P.getText().toString().equals(game.getPenaltiesWinner())){
                holder.team1P.setChecked(true);
                holder.team2P.setChecked(false);
            }else if(holder.team2P.getText().toString().equals(game.getPenaltiesWinner())){
                holder.team1P.setChecked(false);
                holder.team2P.setChecked(true);
            }
        }
    }

    public List<FinalsGame> getList(){
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
