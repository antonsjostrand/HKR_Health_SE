package com.example.hkrhealth.Fragments;

import android.arch.lifecycle.Observer;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hkrhealth.Database.HkrHealthRepository;
import com.example.hkrhealth.Models.Exercise;
import com.example.hkrhealth.Models.HypertrophyWorkout;
import com.example.hkrhealth.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatisticsFragment extends Fragment {

    private static final String TAG = "StatisticsFragment";

    //Database
    private HkrHealthRepository mHkrHealthRepository;

    //Fragment handlers
    private FragmentTransaction ft;
    private FragmentManager fm;

    //UI
    private Button mExerciseStatisticsButton, mGoalSettingStatisticsButton, mHyperWorkoutStatisticsButton, mStrengthWorkoutStatisticsButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics_layout, container, false);

        try {
            fm = getActivity().getSupportFragmentManager();
            mHkrHealthRepository = new HkrHealthRepository(getActivity());

            mExerciseStatisticsButton = view.findViewById(R.id.exerciseStatisticsButton);
            mGoalSettingStatisticsButton = view.findViewById(R.id.goalsettingStatisticsButton);
            mHyperWorkoutStatisticsButton = view.findViewById(R.id.hyperWorkoutStatisticsButton);
            mStrengthWorkoutStatisticsButton = view.findViewById(R.id.strengthWorkoutStatisticsButton);

            mExerciseStatisticsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ft = fm.beginTransaction();
                    ExerciseStatisticsFragment exerciseStatisticsFragment = new ExerciseStatisticsFragment();
                    ft.replace(R.id.fragment_container, exerciseStatisticsFragment);
                    ft.commit();
                }
            });

            mGoalSettingStatisticsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ft = fm.beginTransaction();
                    GoalSettingStatisticsFragment goalSettingStatisticsFragment = new GoalSettingStatisticsFragment();
                    ft.replace(R.id.fragment_container, goalSettingStatisticsFragment);
                    ft.commit();
                }
            });

            mHyperWorkoutStatisticsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ft = fm.beginTransaction();
                    HypertrophyWorkoutStatisticsFragment hypertrophyWorkoutStatisticsFragment = new HypertrophyWorkoutStatisticsFragment();
                    ft.replace(R.id.fragment_container, hypertrophyWorkoutStatisticsFragment);
                    ft.commit();
                }
            });

        }catch (Exception e){
            Log.d(TAG, "onCreateView: error: " + e);
        }
        return view;
    }


}
