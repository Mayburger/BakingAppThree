package com.nacoda.bakingappthree.DetailClasses;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nacoda.bakingappthree.R;
import com.nacoda.bakingappthree.Utilities.Fonts;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class  DetailActivity extends AppCompatActivity {

    ArrayList<HashMap<String, String>> listSteps;
    int position;
    @InjectView(R.id.tvDetailTitle)
    TextView tvDetailTitle;
    @InjectView(R.id.btnDetailNext)
    Button btnDetailNext;
    @InjectView(R.id.btnDetailPrevious)
    Button btnDetailPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.inject(this);

        Fonts.Montez(this, tvDetailTitle);
        tvDetailTitle.setText("Detail");

        getStepsList();

        FragmentManager fragmentManager = getSupportFragmentManager();
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setStepsDescription(listSteps.get(position).get("stepsDescription"));
        detailFragment.setVideoURL(listSteps.get(position).get("stepsVideoURL"));
        detailFragment.setThumbnailURL(listSteps.get(position).get("stepsThumbnailURL"));
        fragmentManager.beginTransaction()
                .replace(R.id.frame_detail, detailFragment)
                .commit();

        if ((position + 1) == listSteps.size()){
            btnDetailNext.setVisibility(View.GONE);
        }

        if (position == 0){
            btnDetailPrevious.setVisibility(View.GONE);
        }

    }

    public void nextData(View view) {

        position = position + 1;
        FragmentManager fragmentManager = getSupportFragmentManager();
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setStepsDescription(listSteps.get(position).get("stepsDescription"));
        detailFragment.setVideoURL(listSteps.get(position).get("stepsVideoURL"));
        fragmentManager.beginTransaction()
                .replace(R.id.frame_detail, detailFragment)
                .commit();

        if (position == (listSteps.size() - 1)){
            btnDetailNext.setVisibility(View.GONE);
        }

        btnDetailPrevious.setVisibility(View.VISIBLE);

    }

    public void prevData(View view) {
        position = position - 1;
        FragmentManager fragmentManager = getSupportFragmentManager();
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setStepsDescription(listSteps.get(position).get("stepsDescription"));
        detailFragment.setVideoURL(listSteps.get(position).get("stepsVideoURL"));
        fragmentManager.beginTransaction()
                .replace(R.id.frame_detail, detailFragment)
                .commit();

        if (position == 0){
            btnDetailPrevious.setVisibility(View.GONE);
        }

        btnDetailNext.setVisibility(View.VISIBLE);

    }

    void getStepsList() {
        listSteps = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra("listSteps");
        position = getIntent().getIntExtra("position", 0);
    }

    public void backDetail(View view) {
        finish();
    }
}
