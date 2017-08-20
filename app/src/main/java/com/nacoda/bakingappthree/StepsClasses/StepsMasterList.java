package com.nacoda.bakingappthree.StepsClasses;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.nacoda.bakingappthree.Adapters.IngredientsAdapter;
import com.nacoda.bakingappthree.Adapters.StepsAdapter;
import com.nacoda.bakingappthree.Parcelable.ParcelableRecipe;
import com.nacoda.bakingappthree.R;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StepsMasterList extends Fragment {

    @InjectView(R.id.lvIngredients)
    ListView lvIngredients;
    @InjectView(R.id.rvSteps)
    RecyclerView rvSteps;

    ArrayList<HashMap<String, String>> listSteps, listIngredients;
    ParcelableRecipe parcelableRecipe;

    onPositionSelected mCallback;

    // onPositionSelected interface, calls a method in the host activity named mPositionSelected
    interface onPositionSelected {
        void mPositionSelected(int position);
    }

    // Override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            mCallback = (onPositionSelected) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement onPositionSelected");
        }
    }

    public StepsMasterList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_steps_master_list, container, false);

        ButterKnife.inject(this, v);

        getData();
        setAdapters();

        rvSteps.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {

                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);

                    mCallback.mPositionSelected(position);

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

        return v;
    }

    void setAdapters(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(listIngredients, getActivity());
        StepsAdapter stepsAdapter = new StepsAdapter(listSteps, getActivity());

        lvIngredients.setAdapter(ingredientsAdapter);
        rvSteps.setLayoutManager(linearLayoutManager);
        rvSteps.setAdapter(stepsAdapter);
    }


    void getData() {
        getStepsList();
        getIngredientsList();
        getParcelableRecipe();
    }

    void getStepsList() {
        listSteps = (ArrayList<HashMap<String, String>>) getActivity().getIntent().getSerializableExtra("listSteps");
    }

    void getIngredientsList() {
        listIngredients = (ArrayList<HashMap<String, String>>) getActivity().getIntent().getSerializableExtra("listIngredients");
    }

    void getParcelableRecipe() {
        parcelableRecipe = getActivity().getIntent().getParcelableExtra("parcelableRecipe");
    }


}
