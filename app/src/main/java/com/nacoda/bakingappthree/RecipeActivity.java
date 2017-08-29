package com.nacoda.bakingappthree;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nacoda.bakingappthree.Adapters.RecipesAdapter;
import com.nacoda.bakingappthree.Gson.GsonRecipe;
import com.nacoda.bakingappthree.Utilities.ErrorHandler;
import com.nacoda.bakingappthree.Utilities.Fonts;
import com.nacoda.bakingappthree.Utilities.NetworkUtils;
import com.nacoda.bakingappthree.Utilities.RecyclerHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RecipeActivity extends AppCompatActivity {


    @InjectView(R.id.rvRecipes)
    RecyclerView rvRecipes;
    @InjectView(R.id.swipeRefreshRecipes)
    SwipeRefreshLayout swipeRefreshRecipes;
    @InjectView(R.id.ivError)
    ImageView ivError;
    @InjectView(R.id.tvRotationHandler)
    TextView tvRotationHandler;
    @InjectView(R.id.tvAppTitle)
    TextView tvAppTitle;

    GsonRecipe gsonRecipe;
    RequestQueue requestQueue;
    StringRequest stringRequest;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.inject(this);

        Fonts.Montez(this, tvAppTitle);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        swipeRefreshRecipes.setColorSchemeResources(R.color.colorPrimaryDark);
        swipeRefreshRecipes.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(savedInstanceState);
            }
        });

        getData(savedInstanceState);
        rotationHandler(savedInstanceState);

    }

    void rotationHandler(Bundle savedInstanceState) {


        if (savedInstanceState != null) {
            String response = savedInstanceState.getString("response");

            gsonRecipe = GsonRecipe.GsonBuilder("{recipes:" + response + "}");

            tvRotationHandler.setText(response);
            setRecyclerView(gsonRecipe);
            ErrorHandler.onSuccess(rvRecipes, ivError, swipeRefreshRecipes);
        }
    }

    public void appTitleToast(View view) {
        Toast.makeText(this, "This is the RecipeActivity", Toast.LENGTH_SHORT).show();
    }

    void getData(final Bundle savedInstanceState) {
        rvRecipes.setVisibility(View.GONE);
        swipeRefreshRecipes.setRefreshing(true);

        stringRequest = new StringRequest(Request.Method.GET, NetworkUtils.recipeUrl().toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response != null) {
                    gsonRecipe = GsonRecipe.GsonBuilder("{recipes:" + response + "}");
                    tvRotationHandler.setText(response);
                    setRecyclerView(gsonRecipe);

                }

                rotationHandler(savedInstanceState);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (savedInstanceState == null) {
                    ErrorHandler.onError(ivError, swipeRefreshRecipes);
                }
            }
        });

        requestQueue.add(stringRequest);
    }

    private void layoutManagerController() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.list_recipes, null);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        if (view.findViewById(R.id.cardTablet) != null) {
            rvRecipes.setLayoutManager(gridLayoutManager);
        } else {
            rvRecipes.setLayoutManager(linearLayoutManager);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("response", tvRotationHandler.getText().toString());
    }

    void setRecyclerView(GsonRecipe gsonRecipe) {
        ErrorHandler.onSuccess(rvRecipes, ivError, swipeRefreshRecipes);
        RecipesAdapter adapter = new RecipesAdapter(gsonRecipe.getRecipes(), RecipeActivity.this);
        layoutManagerController();
        RecyclerHelper.recyclerOnClickMain(rvRecipes, RecipeActivity.this, gsonRecipe);
        rvRecipes.setAdapter(adapter);
    }
}
