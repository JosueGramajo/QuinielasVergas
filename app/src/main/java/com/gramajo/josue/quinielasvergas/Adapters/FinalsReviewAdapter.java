package com.gramajo.josue.quinielasvergas.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gramajo.josue.quinielasvergas.Helpers.DateUtils;
import com.gramajo.josue.quinielasvergas.Objects.FinalsReview;
import com.gramajo.josue.quinielasvergas.Objects.Review;
import com.gramajo.josue.quinielasvergas.R;

import java.util.Date;
import java.util.List;

/**
 * Created by josue on 20/06/2018.
 */

public class FinalsReviewAdapter extends RecyclerView.Adapter<FinalsReviewAdapter.MyViewHolder> {
    private List<FinalsReview> list;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView team1, team2;
        public TextView finalScore1, finalScore2, finalPenalties, finalWinner, poolScore1, poolScore2, poolPenalties, poolWinner, points, name1, name2;

        public MyViewHolder(View view) {
            super(view);
            team1 = view.findViewById(R.id.iv_rev_team1);
            team2 = view.findViewById(R.id.iv_rev_team2);
            finalScore1 = view.findViewById(R.id.tv_rev_first_final_score);
            finalScore2 = view.findViewById(R.id.tv_rev_second_final_score);
            poolScore1 = view.findViewById(R.id.tv_rev_first_pool_score);
            poolScore2 = view.findViewById(R.id.tv_rev_second_pool_score);
            points = view.findViewById(R.id.tv_rev_points);
            finalPenalties = view.findViewById(R.id.tv_rev_final_penalties);
            poolPenalties = view.findViewById(R.id.tv_rev_pool_penalties);
            finalWinner = view.findViewById(R.id.tv_rev_final_winner);
            poolWinner = view.findViewById(R.id.tv_rev_pool_winner);
            name1 = view.findViewById(R.id.iv_rev_team1_name);
            name2 = view.findViewById(R.id.iv_rev_team2_name);
        }
    }

    public FinalsReviewAdapter(Context context, List<FinalsReview> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public FinalsReviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.finals_review_cell, parent, false);

        return new FinalsReviewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FinalsReviewAdapter.MyViewHolder holder, final int position) {
        FinalsReview rev = list.get(position);

        int id1 = context.getResources().getIdentifier("com.gramajo.josue.quinielasvergas:drawable/" + rev.getGame().getFirstTeam().getFlag() + "_big", null, null);
        holder.team1.setImageResource(id1);

        int id2 = context.getResources().getIdentifier("com.gramajo.josue.quinielasvergas:drawable/" + rev.getGame().getSecondTeam().getFlag() + "_big", null, null);
        holder.team2.setImageResource(id2);

        holder.name1.setText(rev.getGame().getFirstTeam().getName());
        holder.name2.setText(rev.getGame().getSecondTeam().getName());

        holder.points.setText(String.valueOf(rev.getPoints()));

        if(rev.getGame().getFirstTeamScore() != null && rev.getGame().getSecondTeamScore() != null){
            holder.finalScore1.setText(String.valueOf(rev.getGame().getFirstTeamScore()));
            holder.finalScore2.setText(String.valueOf(rev.getGame().getSecondTeamScore()));
        }else{
            holder.finalScore1.setText("");
            holder.finalScore2.setText("");
        }

        if(rev.getPoolFirstScore() != null && rev.getPoolSecondScore() != null){
            holder.poolScore1.setText(String.valueOf(rev.getPoolFirstScore()));
            holder.poolScore2.setText(String.valueOf(rev.getPoolSecondScore()));
        }else{
            Date gameDate = DateUtils.INSTANCE.stringToDate(rev.getGame().getDate());
            Date currentDate = DateUtils.INSTANCE.stringToDate(DateUtils.INSTANCE.getCurrentDate());

            if(gameDate.before(currentDate)){
                holder.poolScore1.setText("X");
                holder.poolScore2.setText("X");
            }else{
                holder.poolScore1.setText("");
                holder.poolScore2.setText("");
            }
        }

        holder.poolPenalties.setText(rev.isPoolPenalties() ? "SI" : "NO");
        holder.finalPenalties.setText(rev.getGame().isPenalties() ? "SI" : "NO");

        holder.poolWinner.setText(rev.getPoolPenaltiesWinner());
        holder.finalWinner.setText(rev.getGame().getPenaltiesWinner());
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
