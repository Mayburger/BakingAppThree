
package com.nacoda.bakingappthree.Gson;

import java.util.List;

import javax.annotation.Generated;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class GsonRecipe {

    @SerializedName("recipes")
    private List<Recipe> mRecipes;

    public List<Recipe> getRecipes() {
        return mRecipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        mRecipes = recipes;
    }

    public class Recipe {

        @SerializedName("id")
        private String mId;
        @SerializedName("image")
        private String mImage;
        @SerializedName("ingredients")
        private List<Ingredient> mIngredients;
        @SerializedName("name")
        private String mName;
        @SerializedName("servings")
        private String mServings;
        @SerializedName("steps")
        private List<Step> mSteps;

        public String getId() {
            return mId;
        }

        public void setId(String id) {
            mId = id;
        }

        public String getImage() {
            return mImage;
        }

        public void setImage(String image) {
            mImage = image;
        }

        public List<Ingredient> getIngredients() {
            return mIngredients;
        }

        public void setIngredients(List<Ingredient> ingredients) {
            mIngredients = ingredients;
        }

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            mName = name;
        }

        public String getServings() {
            return mServings;
        }

        public void setServings(String servings) {
            mServings = servings;
        }

        public List<Step> getSteps() {
            return mSteps;
        }

        public void setSteps(List<Step> steps) {
            mSteps = steps;
        }

        public class Ingredient {

            @SerializedName("ingredient")
            private String mIngredient;
            @SerializedName("measure")
            private String mMeasure;
            @SerializedName("quantity")
            private String mQuantity;

            public String getIngredient() {
                return mIngredient;
            }

            public void setIngredient(String ingredient) {
                mIngredient = ingredient;
            }

            public String getMeasure() {
                return mMeasure;
            }

            public void setMeasure(String measure) {
                mMeasure = measure;
            }

            public String getQuantity() {
                return mQuantity;
            }

            public void setQuantity(String quantity) {
                mQuantity = quantity;
            }

        }

        public class Step {

            @SerializedName("description")
            private String mDescription;
            @SerializedName("id")
            private String mId;
            @SerializedName("shortDescription")
            private String mShortDescription;
            @SerializedName("thumbnailURL")
            private String mThumbnailURL;
            @SerializedName("videoURL")
            private String mVideoURL;

            public String getDescription() {
                return mDescription;
            }

            public void setDescription(String description) {
                mDescription = description;
            }

            public String getId() {
                return mId;
            }

            public void setId(String id) {
                mId = id;
            }

            public String getShortDescription() {
                return mShortDescription;
            }

            public void setShortDescription(String shortDescription) {
                mShortDescription = shortDescription;
            }

            public String getThumbnailURL() {
                return mThumbnailURL;
            }

            public void setThumbnailURL(String thumbnailURL) {
                mThumbnailURL = thumbnailURL;
            }

            public String getVideoURL() {
                return mVideoURL;
            }

            public void setVideoURL(String videoURL) {
                mVideoURL = videoURL;
            }

        }

    }

    public static GsonRecipe GsonBuilder(String response) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        GsonRecipe gsonRecipe = gson.fromJson(response, GsonRecipe.class);
        return gsonRecipe;
    }


}
