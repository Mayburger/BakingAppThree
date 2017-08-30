package com.nacoda.bakingappthree.DetailClasses;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.nacoda.bakingappthree.R;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailActivity extends AppCompatActivity {

    ArrayList<HashMap<String, String>> listSteps;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getStepsList();

        FragmentManager fragmentManager = getSupportFragmentManager();
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setStepsDescription(listSteps.get(position).get("stepsDescription"));
        detailFragment.setVideoURL(listSteps.get(position).get("stepsVideoURL"));
        detailFragment.setThumbnailURL(listSteps.get(position).get("stepsThumbnailURL"));
        fragmentManager.beginTransaction()
                .replace(R.id.frame_detail, detailFragment)
                .commit();


    }

    public void nextData(View view){
        position = position + 1;

        FragmentManager fragmentManager = getSupportFragmentManager();
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setStepsDescription(listSteps.get(position).get("stepsDescription"));
        detailFragment.setVideoURL(listSteps.get(position).get("stepsVideoURL"));
        fragmentManager.beginTransaction()
                .replace(R.id.frame_detail, detailFragment)
                .commit();
    }

    public void prevData(View view){
        position = position - 1;

        FragmentManager fragmentManager = getSupportFragmentManager();
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setStepsDescription(listSteps.get(position).get("stepsDescription"));
        detailFragment.setVideoURL(listSteps.get(position).get("stepsVideoURL"));
        fragmentManager.beginTransaction()
                .replace(R.id.frame_detail, detailFragment)
                .commit();
    }

    void getStepsList() {
        listSteps = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra("listSteps");
        position = getIntent().getIntExtra("position", 0);
    }
}
