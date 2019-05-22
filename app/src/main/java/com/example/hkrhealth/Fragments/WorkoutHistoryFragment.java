package com.example.hkrhealth.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hkrhealth.R;

public class WorkoutHistoryFragment extends Fragment {

    private static final String TAG = "WorkoutHistoryFragment";

    //UI
    private Button mHypertrophyButton, mStrengthButton, mLeanButton;

    //Fragment
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_history_layout, container, false);
        try {

            fm = getActivity().getSupportFragmentManager();

            mHypertrophyButton = view.findViewById(R.id.hypertrophyHistoryButton);
            mStrengthButton = view.findViewById(R.id.strengthHistoryButton);
            mLeanButton = view.findViewById(R.id.leanHistoryButton);

            mHypertrophyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hypertrophyButtonPressed();
                }
            });

            mStrengthButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    strengthButtonPressed();
                }
            });

            mLeanButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    leanButtonPressed();
                }
            });


        }catch (Exception e){
            Log.d(TAG, "onCreateView: Error: " + e);
        }
        return view;
    }

    public void hypertrophyButtonPressed(){
        try{
            ft = fm.beginTransaction();

            HypertrophyWorkoutHistoryFragment hypertrophyWorkoutHistoryFragment = new HypertrophyWorkoutHistoryFragment();
            ft.replace(R.id.fragment_container, hypertrophyWorkoutHistoryFragment);
            ft.commit();

        }catch (Exception e){
            Log.d(TAG, "hypertrophyButtonPressed: error: " +e);
        }
    }

    public void strengthButtonPressed(){
        try{
            ft = fm.beginTransaction();

            StrengthWorkoutHistoryFragment strengthWorkoutHistoryFragment = new StrengthWorkoutHistoryFragment();
            ft.replace(R.id.fragment_container, strengthWorkoutHistoryFragment);
            ft.commit();

        }catch (Exception e){
            Log.d(TAG, "strengthButtonPressed: error: " + e);
        }
    }

    public void leanButtonPressed(){
        try{

        }catch (Exception e){
            Log.d(TAG, "leanButtonPressed: error: " + e);
        }
    }



}
