package com.nacoda.bakingappthree.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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


    private List<GsonRecipe.Recipes> recipesData;
    private Context mContext;

    public RecipesAdapter(List<GsonRecipe.Recipes> recipesData, Context mContext) {
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
        holder.tvCakeName.setText(recipesData.get(position).name);
        holder.tvStepsCount.setText(recipesData.get(position).steps.size() + " Steps");
        holder.tvIngredientsCount.setText(recipesData.get(position).ingredients.size() + " Ingredients Needed");
        holder.tvServingsCount.setText(recipesData.get(position).servings);

        /** Set Fonts **/
        Fonts.RobotoBold(mContext, holder.tvCakeName);
        Fonts.RobotoRegular(mContext, holder.tvStepsCount);
        Fonts.RobotoLight(mContext, holder.tvIngredientsCount);
        Fonts.RobotoRegular(mContext, holder.tvServingsCount);


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

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);

        }
    }
}
