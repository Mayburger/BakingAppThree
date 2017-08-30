package com.nacoda.bakingappthree.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.nacoda.bakingappthree.R;
import com.nacoda.bakingappthree.Utilities.Fonts;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ghifari on 8/15/17.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {


    private ArrayList<HashMap<String, String>> listSteps;

    public StepsAdapter(ArrayList<HashMap<String, String>> listSteps, Context mContext) {
        this.listSteps = listSteps;
        this.mContext = mContext;
    }

    private Context mContext;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_steps, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        /** Set Data and Fonts **/
        holder.tvStepsShortDescription.setText(listSteps.get(position).get("stepsShortDescription"));
        Glide.with(mContext).load(listSteps.get(position).get("stepsThumbnailURL"))
                .fitCenter()
                .centerCrop()
                .placeholder(R.drawable.error_image)
                .into(holder.ivStepsThumbnail);
        Fonts.RobotoMedium(mContext, holder.tvStepsShortDescription);
    }

    @Override
    public int getItemCount() {
        return listSteps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * Declaring widgets using Butterknife
         **/
        @InjectView(R.id.tvStepsShortDescription)
        TextView tvStepsShortDescription;
        @InjectView(R.id.ivStepsThumbnail)
        ImageView ivStepsThumbnail;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);


        }
    }
}
