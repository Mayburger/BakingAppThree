package com.nacoda.bakingappthree.Gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ghifari on 8/12/17.
 */

public class GsonRecipe {

    @SerializedName("recipes")
    public List<Recipes> recipes;


    public class Recipes {

        @SerializedName("id")
        public String id;
        @SerializedName("name")
        public String name;
        @SerializedName("ingredients")
        public List<Ingredients> ingredients;
        @SerializedName("steps")
        public List<Steps> steps;
        @SerializedName("servings")
        public String servings;

        public class Steps {

            @SerializedName("id")
            public String id;
            @SerializedName("shortDescription")
            public String shortDescription;
            @SerializedName("description")
            public String description;
            @SerializedName("videoURL")
            public String videoURL;
            @SerializedName("thumbnailURL")
            public String thumbnailURL;

        }

        public class Ingredients {

            @SerializedName("quantity")
            public String quantity;
            @SerializedName("measure")
            public String measure;
            @SerializedName("ingredient")
            public String ingredient;
        }

    }

    public static GsonRecipe GsonBuilder(String response) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        GsonRecipe gsonRecipe = gson.fromJson(response, GsonRecipe.class);
        return gsonRecipe;
    }


}
