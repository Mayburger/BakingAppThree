package com.nacoda.bakingappthree.Utilities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.nacoda.bakingappthree.Gson.GsonRecipe;
import com.nacoda.bakingappthree.StepsClasses.StepsActivity;

/**
 * Created by ghifari on 8/12/17.
 */

public class RecyclerHelper {
    public static void recyclerOnClickMain(RecyclerView rvRecipe, final Context mContext, final GsonRecipe gsonRecipe) {
        rvRecipe.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {

                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);

                    Intent detailIntent = new Intent(mContext, StepsActivity.class);

                    IntentPutExtra.stepsPutExtra(gsonRecipe, position, detailIntent);
                    IntentPutExtra.ingredientsPutExtra(gsonRecipe, position, detailIntent);
                    IntentPutExtra.recipePutExtra(gsonRecipe, position, detailIntent);
                    mContext.startActivity(detailIntent);


                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });
    }
}
