package com.gramajo.josue.quinielasvergas.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gramajo.josue.quinielasvergas.Helpers.DateUtils;
import com.gramajo.josue.quinielasvergas.Helpers.OnUserEventListener;
import com.gramajo.josue.quinielasvergas.Objects.Review;
import com.gramajo.josue.quinielasvergas.R;

import java.util.Date;
import java.util.List;

/**
 * Created by josuegramajo on 6/18/18.
 */

public class PrevReviewAdapter extends RecyclerView.Adapter<PrevReviewAdapter.MyViewHolder> {
    private List<String> list;
    private Context context;

    OnUserEventListener listener;

    public void setListener(OnUserEventListener listener) {
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView user;

        public MyViewHolder(View view) {
            super(view);
            user = view.findViewById(R.id.tv_prev_review_user);
        }
    }

    public PrevReviewAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public PrevReviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.prev_review_cell, parent, false);

        return new PrevReviewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PrevReviewAdapter.MyViewHolder holder, final int position) {
        final String usr = list.get(position);
        holder.user.setText(usr);

        holder.user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onUserSelected(usr);
            }
        });
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
