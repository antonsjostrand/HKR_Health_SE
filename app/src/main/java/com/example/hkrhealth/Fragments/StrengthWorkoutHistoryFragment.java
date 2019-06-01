package com.example.hkrhealth.Fragments;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hkrhealth.Adapters.StrengthWorkoutRecyclerAdapter;
import com.example.hkrhealth.Database.HkrHealthRepository;
import com.example.hkrhealth.Models.StrengthWorkout;
import com.example.hkrhealth.R;
import com.example.hkrhealth.SelectedStrengthWorkoutActivity;
import com.example.hkrhealth.Util.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.List;

public class StrengthWorkoutHistoryFragment extends Fragment implements StrengthWorkoutRecyclerAdapter.OnStrengthWorkoutListener {

    private static final String TAG = "StrengthWorkoutHistory";

    //Database
    private HkrHealthRepository mHkrHealthRepository;

    //UI
    private RecyclerView mRecyclerView;

    //Variables
    private ArrayList<StrengthWorkout> mStrengthWorkouts = new ArrayList<>();
    private StrengthWorkoutRecyclerAdapter mStrengthWorkoutRecyclerAdapter;
    private StrengthWorkout mSelectedWorkout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_strength_history_layout, container, false);
        try {
            mRecyclerView = view.findViewById(R.id.strRecyclerView);
            mHkrHealthRepository = new HkrHealthRepository(getActivity());

            getAllWorkouts();
            initRecyclerView();

        }catch (Exception e){
            Log.d(TAG, "onCreateView: Error: " + e);
        }
        return view;
    }

    public void getAllWorkouts(){
        try {
            mHkrHealthRepository.getAllStrengthWorkouts().observe(getActivity(), new Observer<List<StrengthWorkout>>() {
                @Override
                public void onChanged(@Nullable List<StrengthWorkout> strengthWorkouts) {
                    if (mStrengthWorkouts.size() > 0) {
                        mStrengthWorkouts.clear();
                    }
                    if (strengthWorkouts != null) {
                        mStrengthWorkouts.addAll(strengthWorkouts);
                    }
                    mStrengthWorkoutRecyclerAdapter.notifyDataSetChanged();
                }
            });
        }catch (Exception e){
            Log.d(TAG, "getAllWorkouts: error: " + e);
        }
    }

    public void initRecyclerView(){
        try {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(linearLayoutManager);
            VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
            mRecyclerView.addItemDecoration(itemDecorator);
            mStrengthWorkoutRecyclerAdapter = new StrengthWorkoutRecyclerAdapter(mStrengthWorkouts, this);
            mRecyclerView.setAdapter(mStrengthWorkoutRecyclerAdapter);
        }catch (Exception e){
            Log.d(TAG, "initRecyclerView: error: " + e);
        }
    }

    @Override
    public void onWorkoutClick(int position) {
        try{
            Log.d(TAG, "onWorkoutClick: workout clicked, position: " + position);

            mSelectedWorkout = mStrengthWorkouts.get(position);

            Intent intent = new Intent(getActivity(), SelectedStrengthWorkoutActivity.class);
            intent.putExtra("strength_workout", mSelectedWorkout);
            startActivity(intent);

        }catch (Exception e){
            Log.d(TAG, "onWorkoutClick: error: " + e);
        }
}
}
