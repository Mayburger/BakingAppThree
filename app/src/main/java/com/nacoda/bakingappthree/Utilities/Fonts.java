package com.nacoda.bakingappthree.Utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by Mayburger on 8/10/2017.
 */

public class Fonts {

    public static void RobotoLight(Context context, TextView tvData) {
        tvData.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/roboto_light.ttf"));
    }

    public static void Montez(Context context, TextView tvData) {
        tvData.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/montez.ttf"));
    }

    public static void RobotoRegular(Context context, TextView tvData) {
        tvData.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/roboto_regular.ttf"));
    }

    public static void RobotoBold(Context context, TextView tvData) {
        tvData.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/roboto_bold.ttf"));
    }

    public static void RobotoMedium(Context context, TextView tvData) {
        tvData.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/roboto_medium.ttf"));
    }

}