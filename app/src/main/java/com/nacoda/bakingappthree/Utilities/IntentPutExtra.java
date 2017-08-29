package com.nacoda.bakingappthree.Utilities;

import android.content.Intent;

import com.nacoda.bakingappthree.Gson.GsonRecipe;
import com.nacoda.bakingappthree.Parcelable.ParcelableRecipe;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ghifari on 8/15/17.
 */

public class IntentPutExtra {
    public static void stepsPutExtra(GsonRecipe gsonRecipe, int position, Intent detailIntent) {
        ArrayList<HashMap<String, String>> listSteps;
        listSteps = new ArrayList<>();

        for (int i = 0; i < gsonRecipe.getRecipes().get(position).getSteps().size(); i++) {
            HashMap<String, String> stepsMap = new HashMap<>();
            stepsMap.put("stepsId", gsonRecipe.getRecipes().get(position).getSteps().get(i).getId());
            stepsMap.put("stepsShortDescription", gsonRecipe.getRecipes().get(position).getSteps().get(i).getShortDescription());
            stepsMap.put("stepsDescription", gsonRecipe.getRecipes().get(position).getSteps().get(i).getDescription());
            stepsMap.put("stepsVideoURL", gsonRecipe.getRecipes().get(position).getSteps().get(i).getVideoURL());
            stepsMap.put("stepsThumbnailURL", gsonRecipe.getRecipes().get(position).getSteps().get(i).getThumbnailURL());
            listSteps.add(stepsMap);
            detailIntent.putExtra("listSteps", listSteps);
        }


    }

    public static void ingredientsPutExtra(GsonRecipe gsonRecipe, int position, Intent detailIntent) {

        ArrayList<HashMap<String, String>> listIngredients;
        listIngredients = new ArrayList<>();

        for (int i = 0; i < gsonRecipe.getRecipes().get(position).getIngredients().size(); i++) {
            HashMap<String, String> ingredientsMap = new HashMap<>();
            ingredientsMap.put("ingredientsQuantity", gsonRecipe.getRecipes().get(position).getIngredients().get(i).getQuantity());
            ingredientsMap.put("ingredientsMeasure", gsonRecipe.getRecipes().get(position).getIngredients().get(i).getMeasure());
            ingredientsMap.put("ingredientsIngredient", gsonRecipe.getRecipes().get(position).getIngredients().get(i).getIngredient());
            listIngredients.add(ingredientsMap);
            detailIntent.putExtra("listIngredients", listIngredients);
        }
    }

    public static void recipePutExtra(GsonRecipe gsonRecipe, int position, Intent detailIntent) {

        ParcelableRecipe parcelableRecipe = new ParcelableRecipe(
                gsonRecipe.getRecipes().get(position).getId(),
                gsonRecipe.getRecipes().get(position).getName(),
                gsonRecipe.getRecipes().get(position).getServings()
        );

        detailIntent.putExtra("parcelableRecipe", parcelableRecipe);

    }
}
