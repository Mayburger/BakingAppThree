package com.nacoda.bakingappthree.Parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ghifari on 8/13/17.
 */

public class ParcelableRecipe implements Parcelable {

    private String id;
    private String name;
    private String servings;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getServings() {
        return servings;
    }

    public ParcelableRecipe(String id, String name, String servings) {

        this.id = id;
        this.name = name;
        this.servings = servings;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.servings);
    }

    public ParcelableRecipe() {
    }

    protected ParcelableRecipe(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.servings = in.readString();
    }

    public static final Creator<ParcelableRecipe> CREATOR = new Creator<ParcelableRecipe>() {
        @Override
        public ParcelableRecipe createFromParcel(Parcel source) {
            return new ParcelableRecipe(source);
        }

        @Override
        public ParcelableRecipe[] newArray(int size) {
            return new ParcelableRecipe[size];
        }
    };
}