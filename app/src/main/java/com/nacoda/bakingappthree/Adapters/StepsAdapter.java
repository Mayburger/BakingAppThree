package com.nacoda.bakingappthree.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
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
        holder.tvStepsNumber.setText("" + (position + 1));
        Fonts.RobotoRegular(mContext, holder.tvStepsShortDescription);
        Fonts.RobotoLight(mContext, holder.tvStepsNumber);

        if (listSteps.get(position).get("stepsThumbnailURL") != null) {
            Glide.with(mContext).load(listSteps.get(position).get("stepsThumbnailURL")).asBitmap().into(new SimpleTarget<Bitmap>(200, 200) {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    Drawable drawable = new BitmapDrawable(resource);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        holder.ivThumbnailSteps.setBackground(drawable);
                    }
                }
            });
        }


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
        @InjectView(R.id.tvStepsNumber)
        TextView tvStepsNumber;
        @InjectView(R.id.ivThumbnailSteps)
        ImageView ivThumbnailSteps;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);


        }
    }
}
