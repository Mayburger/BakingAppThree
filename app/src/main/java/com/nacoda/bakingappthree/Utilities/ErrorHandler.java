package com.nacoda.bakingappthree.Utilities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by ghifari on 8/16/17.
 */

public class ErrorHandler {

    public static void onError(ImageView ivError, SwipeRefreshLayout swipeRefreshRecipes) {
        ivError.setVisibility(View.VISIBLE);
        swipeRefreshRecipes.setRefreshing(false);
    }

    public static void onSuccess(RecyclerView rvRecipes, ImageView ivError, SwipeRefreshLayout swipeRefreshRecipes) {
        rvRecipes.setVisibility(View.VISIBLE);
        ivError.setVisibility(View.GONE);
        swipeRefreshRecipes.setRefreshing(false);
    }
}
