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
import com.gramajo.josue.quinielasvergas.Objects.Review;
import com.gramajo.josue.quinielasvergas.R;

import java.util.Date;
import java.util.List;

/**
 * Created by josuegramajo on 6/18/18.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder>  {
    private List<Review> list;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView team1, team2;
        public TextView finalScore1, finalScore2, poolScore1, poolScore2, date, points, name1, name2;

        public MyViewHolder(View view) {
            super(view);
            team1 = view.findViewById(R.id.iv_review_firstTeam);
            team2 = view.findViewById(R.id.iv_review_secondTeam);
            finalScore1 = view.findViewById(R.id.tv_review_first_final_score);
            finalScore2 = view.findViewById(R.id.tv_review_second_final_score);
            poolScore1 = view.findViewById(R.id.tv_review_first_pool_score);
            poolScore2 = view.findViewById(R.id.tv_review_second_pool_score);
            date = view.findViewById(R.id.tv_review_date);
            points = view.findViewById(R.id.tv_review_points);
            name1 = view.findViewById(R.id.tv_review_firstTeamName);
            name2 = view.findViewById(R.id.tv_review_secondTeamName);
        }
    }

    public ReviewAdapter(Context context, List<Review> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ReviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_cell, parent, false);

        return new ReviewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReviewAdapter.MyViewHolder holder, final int position) {
        Review rev = list.get(position);

        int id1 = context.getResources().getIdentifier("com.gramajo.josue.quinielasvergas:drawable/" + rev.getGame().getFirstTeam().getFlag() + "_big", null, null);
        holder.team1.setImageResource(id1);

        int id2 = context.getResources().getIdentifier("com.gramajo.josue.quinielasvergas:drawable/" + rev.getGame().getSecondTeam().getFlag() + "_big", null, null);
        holder.team2.setImageResource(id2);

        holder.name1.setText(rev.getGame().getFirstTeam().getName());
        holder.name2.setText(rev.getGame().getSecondTeam().getName());

        holder.date.setText(rev.getGame().getDate());

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
