package com.nacoda.bakingappthree.StepsClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.nacoda.bakingappthree.DetailClasses.DetailActivity;
import com.nacoda.bakingappthree.DetailClasses.DetailFragment;
import com.nacoda.bakingappthree.R;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class StepsActivity extends AppCompatActivity implements StepsMasterList.onPositionSelected {


    private boolean mTwoPane;
    private ArrayList<HashMap<String, String>> listSteps;
    @InjectView(R.id.tvPositionHandler)
    TextView tvPositionHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        ButterKnife.inject(this);
        getStepsList();

        if (findViewById(R.id.tablet_linear_layout) != null) {
            mTwoPane = true;

            if (savedInstanceState != null) {
                int position = Integer.parseInt(savedInstanceState.getString("position"));
                tvPositionHandler.setText("" + position);
                FragmentManager fragmentManager = getSupportFragmentManager();
                DetailFragment detailFragment = new DetailFragment();
                detailFragment.setStepsDescription(listSteps.get(position).get("stepsDescription"));
                detailFragment.setVideoURL(listSteps.get(position).get("stepsVideoURL"));
                detailFragment.setThumbnailURL(listSteps.get(position).get("stepsThumbnailURL"));
                fragmentManager.beginTransaction()
                        .add(R.id.frame_detail, detailFragment)
                        .commit();
            } else {
                FragmentManager fragmentManager = getSupportFragmentManager();
                DetailFragment detailFragment = new DetailFragment();
                detailFragment.setVideoURL(listSteps.get(0).get("stepsVideoURL"));
                detailFragment.setThumbnailURL(listSteps.get(0).get("stepsThumbnailURL"));
                detailFragment.setStepsDescription(listSteps.get(0).get("stepsDescription"));
                fragmentManager.beginTransaction()
                        .add(R.id.frame_detail, detailFragment)
                        .commit();
            }

        } else {
            mTwoPane = false;
        }
    }

    @Override
    public void mPositionSelected(int position) {

        if (mTwoPane) {
            tvPositionHandler.setText("" + position);
            FragmentManager fragmentManager = getSupportFragmentManager();
            DetailFragment detailFragment = new DetailFragment();
            detailFragment.setStepsDescription(listSteps.get(position).get("stepsDescription"));
            detailFragment.setVideoURL(listSteps.get(position).get("stepsVideoURL"));
            detailFragment.setThumbnailURL(listSteps.get(position).get("stepsThumbnailURL"));
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_detail, detailFragment)
                    .commit();
        } else {
            Intent detailIntent = new Intent(getApplicationContext(), DetailActivity.class);
            detailIntent.putExtra("listSteps", listSteps);
            detailIntent.putExtra("position", position);
            startActivity(detailIntent);
        }

    }

    void getStepsList() {
        listSteps = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra("listSteps");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("position", tvPositionHandler.getText().toString());
    }
}

