package com.example.hkrhealth.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hkrhealth.R;

public class WorkoutHistoryFragment extends Fragment {

    private static final String TAG = "WorkoutHistoryFragment";

    //UI
   // private Button mHypertrophyButton, mStrengthButton, mLeanButton;
    private CardView mHypertrophyHistoryCardView, mStrengthHistoryCardView, mLeanCardView;

    //Fragment
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_history_layout, container, false);
        try {

            fm = getActivity().getSupportFragmentManager();

            mHypertrophyHistoryCardView = view.findViewById(R.id.hypertrophyHistoryButton);
            mStrengthHistoryCardView = view.findViewById(R.id.strengthHistoryButton);
              //mLeanCardView = view.findViewById(R.id.leanHistoryButton);


            mHypertrophyHistoryCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: Hypeworkout A: Pressed");
                    hypertrophyButtonPressed();
                }
            });

            mStrengthHistoryCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: Hypeworkout B: Pressed");

                    strengthButtonPressed();
                }
            });

            mLeanCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: Hypeworkout A: Pressed");

                    leanButtonPressed();
                }
            });


        }catch (Exception e){
            Log.d(TAG, "onCreateView: Error: " + e);
            e.printStackTrace();
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
            Log.d(TAG, "hypertrophyButtonPressed: error: " + e);
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    public void leanButtonPressed(){
        try{
            //ft = fm.beginTransaction();


        }catch (Exception e){
            Log.d(TAG, "leanButtonPressed: error: " + e);
            e.printStackTrace();
        }
    }



}
