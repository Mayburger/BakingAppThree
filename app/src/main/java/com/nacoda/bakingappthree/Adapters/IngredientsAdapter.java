package com.nacoda.bakingappthree.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.nacoda.bakingappthree.R;
import com.nacoda.bakingappthree.Utilities.Fonts;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ghifari on 8/15/17.
 */

public class IngredientsAdapter extends BaseAdapter {

    private ArrayList<HashMap<String, String>> listIngredients;
    private Context mContext;

    public IngredientsAdapter(ArrayList<HashMap<String, String>> listIngredients, Context mContext) {
        this.listIngredients = listIngredients;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return listIngredients.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View v, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);

        ViewHolder holder;
        if (v != null) {
            holder = (ViewHolder) v.getTag();
        } else {
            v = inflater.inflate(R.layout.list_ingredients, null, false);
            holder = new ViewHolder(v);
            v.setTag(holder);
        }

        /** Set Data **/
        holder.tvIngredientsIngredient.setText(listIngredients.get(position).get("ingredientsIngredient"));
        holder.tvIngredientsQuantity.setText(listIngredients.get(position).get("ingredientsQuantity"));
        holder.tvIngredientsMeasure.setText(listIngredients.get(position).get("ingredientsMeasure"));

        /** Set Fonts **/
        Fonts.RobotoMedium(mContext, holder.tvIngredientsIngredient);
        Fonts.RobotoLight(mContext, holder.tvIngredientsQuantity);
        Fonts.RobotoLight(mContext, holder.tvIngredientsMeasure);

        return v;

    }

    /**
     * Declare widgets using Butterknife
     **/
    static class ViewHolder {
        @InjectView(R.id.tvIngredientsIngredient)
        TextView tvIngredientsIngredient;
        @InjectView(R.id.tvIngredientsQuantity)
        TextView tvIngredientsQuantity;
        @InjectView(R.id.tvIngredientsMeasure)
        TextView tvIngredientsMeasure;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
