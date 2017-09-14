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
import com.nacoda.bakingappthree.Gson.GsonRecipe;
import com.nacoda.bakingappthree.R;
import com.nacoda.bakingappthree.Utilities.Fonts;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mayburger on 4/19/2017.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {


    private List<GsonRecipe.Recipe> recipesData;
    private Context mContext;

    public RecipesAdapter(List<GsonRecipe.Recipe> recipesData, Context mContext) {
        this.recipesData = recipesData;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recipes, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        /** Set Data **/
        holder.tvCakeName.setText(recipesData.get(position).getName());
        holder.tvStepsCount.setText(recipesData.get(position).getSteps().size() + " Steps");
        holder.tvIngredientsCount.setText(recipesData.get(position).getIngredients().size() + " Ingredients Needed");
        holder.tvServingsCount.setText("" + recipesData.get(position).getServings());

        /** Set Fonts **/
        Fonts.RobotoBold(mContext, holder.tvCakeName);
        Fonts.RobotoRegular(mContext, holder.tvStepsCount);
        Fonts.RobotoLight(mContext, holder.tvIngredientsCount);
        Fonts.RobotoRegular(mContext, holder.tvServingsCount);

        if (recipesData.get(position).getImage() != null) {
            Glide.with(mContext).load(recipesData.get(position).getImage()).asBitmap().into(new SimpleTarget<Bitmap>(200, 200) {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    Drawable drawable = new BitmapDrawable(resource);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        holder.ivThumbnailRecipes.setBackground(drawable);
                    }
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return recipesData.size();
    }

    /**
     * Declaring widgets using Butterknife
     **/

    public class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.tvCakeName)
        TextView tvCakeName;
        @InjectView(R.id.tvStepsCount)
        TextView tvStepsCount;
        @InjectView(R.id.tvIngredientsCount)
        TextView tvIngredientsCount;
        @InjectView(R.id.tvServingsCount)
        TextView tvServingsCount;
        @InjectView(R.id.ivThumbnailRecipes)
        ImageView ivThumbnailRecipes;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);

        }
    }
}
