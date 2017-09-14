package com.nacoda.bakingappthree.Widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import com.nacoda.bakingappthree.Gson.GsonRecipe;
import com.nacoda.bakingappthree.R;

/**
 * If you are familiar with Adapter of ListView,this is the same as adapter
 * with few changes
 */
public class ListProvider implements RemoteViewsFactory {
    private Context context = null;
    private Intent intent;
    private String data;
    private GsonRecipe gsonRecipe;
    private int dataPosition;
    private int appWidgetId;

    public ListProvider(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;

    }


    @Override
    public int getCount() {
        gsonRecipe = GsonRecipe.GsonBuilder(data);
        return gsonRecipe.getRecipes().get(0).getIngredients().size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
     *Similar to getView of Adapter where instead of View
     *we return RemoteViews
     *
     */
    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews remoteView = new RemoteViews(
                context.getPackageName(), R.layout.list_widget);

            if (dataPosition != 0) {
                remoteView.setTextViewText(R.id.tvIngredientsWidget, gsonRecipe.getRecipes().get(dataPosition).getIngredients().get(position).getIngredient());
                remoteView.setTextViewText(R.id.tvMeasureWidget, gsonRecipe.getRecipes().get(dataPosition).getIngredients().get(position).getMeasure());
                remoteView.setTextViewText(R.id.tvQuantityWidget, gsonRecipe.getRecipes().get(dataPosition).getIngredients().get(position).getQuantity());
            } else {
                remoteView.setTextViewText(R.id.tvIngredientsWidget, gsonRecipe.getRecipes().get(0).getIngredients().get(position).getIngredient());
                remoteView.setTextViewText(R.id.tvMeasureWidget, gsonRecipe.getRecipes().get(0).getIngredients().get(position).getMeasure());
                remoteView.setTextViewText(R.id.tvQuantityWidget, gsonRecipe.getRecipes().get(0).getIngredients().get(position).getQuantity());
            }



        return remoteView;
    }


    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onCreate() {

        data = intent.getStringExtra("stringData");
        dataPosition = intent.getIntExtra("position", 0);
        appWidgetId = intent.getIntExtra("appWidgetId", 0);

    }

    @Override
    public void onDataSetChanged() {
    }

    @Override
    public void onDestroy() {
    }

}
