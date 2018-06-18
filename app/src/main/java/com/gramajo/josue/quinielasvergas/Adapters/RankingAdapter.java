package com.gramajo.josue.quinielasvergas.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gramajo.josue.quinielasvergas.Objects.Point;
import com.gramajo.josue.quinielasvergas.R;

import java.util.ArrayList;

/**
 * Created by josuegramajo on 6/15/18.
 */

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.MyViewHolder> {
    private ArrayList<Point> list;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView points, user;

        public MyViewHolder(View view) {
            super(view);
            points = view.findViewById(R.id.tv_points);
            user = view.findViewById(R.id.tv_user);
        }
    }

    public RankingAdapter(Context context, ArrayList<Point> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RankingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ranking_cell, parent, false);

        return new RankingAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RankingAdapter.MyViewHolder holder, final int position) {
        Point point = list.get(position);

        holder.points.setText(String.valueOf(point.getPoints()));
        holder.user.setText(point.getUser());
    }

    public ArrayList<Point> getList(){
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
