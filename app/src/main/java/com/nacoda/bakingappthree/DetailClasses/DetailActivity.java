package com.nacoda.bakingappthree.DetailClasses;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.nacoda.bakingappthree.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        FragmentManager fragmentManager = getSupportFragmentManager();
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setStepsDescription(getIntent().getStringExtra("stepsDescription"));
        detailFragment.setVideoURL(getIntent().getStringExtra("stepsVideoURL"));
        fragmentManager.beginTransaction()
                .replace(R.id.frame_detail, detailFragment)
                .commit();


    }
}
