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

import com.example.hkrhealth.Adapters.HypertrophyWorkoutRecyclerAdapter;
import com.example.hkrhealth.Database.HkrHealthRepository;
import com.example.hkrhealth.Models.HypertrophyWorkout;
import com.example.hkrhealth.R;
import com.example.hkrhealth.SelectedHypertrophyWorkoutActivity;
import com.example.hkrhealth.Util.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.List;

public class HypertrophyWorkoutHistoryFragment extends Fragment implements HypertrophyWorkoutRecyclerAdapter.OnHypertrophyWorkoutListener{

    private static final String TAG = "HypertrophyWorkoutHisto";

    //Database
    private HkrHealthRepository mHkrHealthRepository;

    //UI
    private RecyclerView mRecyclerView;

    //Variables
    private ArrayList<HypertrophyWorkout> mHypertrophyWorkouts = new ArrayList<>();
    private HypertrophyWorkoutRecyclerAdapter mHypertrophyWorkoutRecyclerAdapter;
    private HypertrophyWorkout mSelectedWorkout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.workout_hypertrophy_history_fragment_layout, container, false);
        try {
            mRecyclerView = view.findViewById(R.id.recyclerView);
            mHkrHealthRepository = new HkrHealthRepository(getActivity());

            getAllWorkouts();
            initRecyclerView();

        }catch (Exception e){
            Log.d(TAG, "onCreateView: Error: " + e);
        }
        return view;
    }

    public void getAllWorkouts(){
        mHkrHealthRepository.getAllHypertrophyWorkouts().observe(getActivity(), new Observer<List<HypertrophyWorkout>>() {
            @Override
            public void onChanged(@Nullable List<HypertrophyWorkout> hypertrophyWorkouts) {
                if (mHypertrophyWorkouts.size() > 0){
                    mHypertrophyWorkouts.clear();
                }
                if (hypertrophyWorkouts != null){
                    mHypertrophyWorkouts.addAll(hypertrophyWorkouts);
                }
                mHypertrophyWorkoutRecyclerAdapter.notifyDataSetChanged();
            }
        });
    }

    public void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);
        mHypertrophyWorkoutRecyclerAdapter = new HypertrophyWorkoutRecyclerAdapter(mHypertrophyWorkouts, this);
        mRecyclerView.setAdapter(mHypertrophyWorkoutRecyclerAdapter);
    }

    @Override
    public void onWorkoutClick(int position) {
        try{
            Log.d(TAG, "onWorkoutClick: workout clicked, position: " + position);

            mSelectedWorkout = mHypertrophyWorkouts.get(position);

            Intent intent = new Intent(getActivity(), SelectedHypertrophyWorkoutActivity.class);
            intent.putExtra("hypertrophy_workout", mSelectedWorkout);
            startActivity(intent);

        }catch (Exception e){
            Log.d(TAG, "onWorkoutClick: error: " + e);
        }

    }
}
