package com.example.hkrhealth.Fragments;

import android.arch.lifecycle.Observer;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hkrhealth.Database.HkrHealthRepository;
import com.example.hkrhealth.Models.StrengthWorkout;
import com.example.hkrhealth.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class StrengthWorkoutStatisticsFragment extends Fragment {

    private static final String TAG = "StrengthWorkoutStatisti";

    //Database
    private HkrHealthRepository mHkrHealthRepository;

    //UI
    private Button mTypeAButton, mTypeBButton;
    private LineChart mLineChart;
    private TextView mLiftedWeightTV;

    //Variables
    private ArrayList<StrengthWorkout> mStrength_A_Workouts = new ArrayList<>();
    private ArrayList<StrengthWorkout> mStrength_B_Workouts = new ArrayList<>();
    private ArrayList<Integer> mWorkoutsLiftedWeight = new ArrayList<>();
    private int mLiftedWeight = 0;
    private boolean mWorkoutsTypeAAdded = false, mWorkoutsTypeBAdded = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_strength_workout_statistics_layout, container, false);
        try {

            mHkrHealthRepository = new HkrHealthRepository(getActivity());

            mTypeAButton = view.findViewById(R.id.stat_strength_aButton);
            mTypeBButton = view.findViewById(R.id.stat_strength_bButton);
            mLineChart = view.findViewById(R.id.stat_strength_lineChart);
            mLiftedWeightTV = view.findViewById(R.id.stat_strength_totalWeightLiftedTV);

            getAllTypeAWorkouts();
            getAllTypeBWorkouts();

            mTypeAButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    typeAButtonPressed();
                }
            });

            mTypeBButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    typeBButtonPressed();
                }
            });


        }catch (Exception e){
            Log.d(TAG, "onCreateView: Error: " + e);
            e.printStackTrace();
        }
        return view;
    }

    public void typeAButtonPressed(){
        try{
            if (mWorkoutsTypeAAdded){
                clearLineChart();
                if (mWorkoutsLiftedWeight.size() > 0){
                    mWorkoutsLiftedWeight.clear();
                }

                calculateTotalWeightTypeAWorkout();

                for (StrengthWorkout i : mStrength_A_Workouts){
                    getTotalWeightPerExerciseTypeA(i.getWorkoutID());

                    Thread.sleep(1000);
                    Log.d(TAG, "typeAButtonPressed: delay finished.");
                }
            }
        }catch (Exception e){
            Log.d(TAG, "typeAButtonPressed: error: " + e);
            e.printStackTrace();
        }
    }

    public void typeBButtonPressed(){
        try{
            if (mWorkoutsTypeBAdded){
                clearLineChart();
                if (mWorkoutsLiftedWeight.size() > 0){
                    mWorkoutsLiftedWeight.clear();
                }

                calculateTotalWeightTypeBWorkout();

                for (StrengthWorkout i : mStrength_B_Workouts){
                    getTotalWeightPerExerciseTypeB(i.getWorkoutID());

                    Thread.sleep(1000);
                    Log.d(TAG, "typeBButtonPressed: delay finished");
                }
            }
        }catch (Exception e){
            Log.d(TAG, "typeBButtonPressed: error: " + e);
            e.printStackTrace();
        }
    }

    public void getAllTypeAWorkouts(){
        try {
            mHkrHealthRepository.getAllStrengthWorkoutsTypeA().observe(getActivity(), new Observer<List<StrengthWorkout>>() {
                @Override
                public void onChanged(@Nullable List<StrengthWorkout> strengthWorkouts) {
                    if (mStrength_A_Workouts.size() > 0) {
                        mStrength_A_Workouts.clear();
                    }
                    if (strengthWorkouts != null) {
                        Log.d(TAG, "onChanged: Added all type A workouts.");
                        mStrength_A_Workouts.addAll(strengthWorkouts);
                        mWorkoutsTypeAAdded = true;
                    }
                }
            });
        }catch (Exception e){
            Log.d(TAG, "getAllTypeAWorkouts: error: " + e);
            e.printStackTrace();
        }
    }

    public void getAllTypeBWorkouts(){
        try {
            mHkrHealthRepository.getAllStrengthWorkoutsTypeB().observe(getActivity(), new Observer<List<StrengthWorkout>>() {
                @Override
                public void onChanged(@Nullable List<StrengthWorkout> strengthWorkouts) {
                    if (mStrength_B_Workouts.size() > 0) {
                        mStrength_B_Workouts.clear();
                    }
                    if (strengthWorkouts != null) {
                        Log.d(TAG, "onChanged: Added all type B workouts.");
                        mStrength_B_Workouts.addAll(strengthWorkouts);
                        mWorkoutsTypeBAdded = true;
                    }
                }
            });
        }catch (Exception e){
            Log.d(TAG, "getAllTypeBWorkouts: error: " + e);
            e.printStackTrace();
        }
    }

    public void getTotalWeightPerExerciseTypeA(final int workoutID){
        try{
            mHkrHealthRepository.getTotalWeightForSpecificStrengthWorkout(workoutID).observe(getActivity(), new Observer<Integer>() {
                @Override
                public void onChanged(@Nullable Integer integer) {
                    if (integer != null){
                        clearLineChart();
                        Log.d(TAG, "onChanged: adding weight: " + integer + ", for workout id: " + workoutID);
                        mWorkoutsLiftedWeight.add(integer);
                        drawLineChartTypeA();
                    }
                }
            });
        }catch (Exception e){
            Log.d(TAG, "getTotalWeightPerExerciseTypeA: error: " + e);
            e.printStackTrace();
        }

    }

    public void getTotalWeightPerExerciseTypeB(final int workoutID){
        try{
            mHkrHealthRepository.getTotalWeightForSpecificStrengthWorkout(workoutID).observe(getActivity(), new Observer<Integer>() {
                @Override
                public void onChanged(@Nullable Integer integer) {
                    if (integer != null){
                        clearLineChart();
                        Log.d(TAG, "onChanged: adding weight: " + integer + ", for workout id: " + workoutID);
                        mWorkoutsLiftedWeight.add(integer);
                        drawLineChartTypeB();
                    }
                }
            });
        }catch (Exception e){
            Log.d(TAG, "getTotalWeightPerExerciseTypeB: error: " + e);
            e.printStackTrace();
        }

    }

    public void calculateTotalWeightTypeAWorkout(){
        try{
            mHkrHealthRepository.getTotalWeightForStrengthWorkoutTypeA().observe(getActivity(), new Observer<Integer>() {
                @Override
                public void onChanged(@Nullable Integer integer) {
                    if (integer != null) {
                        Log.d(TAG, "onChanged: added weight.");
                        mLiftedWeight = integer;
                        Log.d(TAG, "onChanged: total weight: " + mLiftedWeight);
                        mLiftedWeightTV.setText(String.valueOf(mLiftedWeight));
                    }else{
                        Toast toast = Toast.makeText(getActivity(), "No data found for this workout type", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            });
        }catch (Exception e){
            Log.d(TAG, "calculateTotalWeightTypeAWorkout: error: " + e);
            e.printStackTrace();
        }
    }

    public void calculateTotalWeightTypeBWorkout(){
        try{
            mHkrHealthRepository.getTotalWeightForStrengthWorkoutTypeB().observe(getActivity(), new Observer<Integer>() {
                @Override
                public void onChanged(@Nullable Integer integer) {
                    if (integer != null) {
                        Log.d(TAG, "onChanged: added weight.");
                        mLiftedWeight = integer;
                        Log.d(TAG, "onChanged: total weight: " + mLiftedWeight);
                        mLiftedWeightTV.setText(String.valueOf(mLiftedWeight));
                    }else{
                        Toast toast = Toast.makeText(getActivity(), "No data found for this workout type", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            });
        }catch (Exception e){
            Log.d(TAG, "calculateTotalWeightTypeBWorkout: error: " + e);
            e.printStackTrace();
        }
    }

    public void drawLineChartTypeA(){
        try {
            Log.d(TAG, "drawLineChartTypeA: starting to draw linechart");
            ArrayList<Entry> yAxisWeight = new ArrayList<>();

            for (int i = 0; i < mWorkoutsLiftedWeight.size(); i++) {

                float weight = (float) mWorkoutsLiftedWeight.get(i);
                Entry entry = new Entry(i, weight);
                yAxisWeight.add(entry);

            }

            ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

            LineDataSet lineDataSet = new LineDataSet(yAxisWeight, "weight");
            lineDataSet.setDrawCircles(false);
            lineDataSet.setColor(Color.BLUE);
            lineDataSet.setFillAlpha(110);

            lineDataSets.add(lineDataSet);

            LineData lineData = new LineData(lineDataSet);


            mLineChart.setData(lineData);
            Log.d(TAG, "drawLineChartTypeA: linechart drawn");
        } catch (Exception e) {
            Log.d(TAG, "drawLineChartTypeA: error: " + e);
            e.printStackTrace();
        }
    }

    public void drawLineChartTypeB(){
        try {
            Log.d(TAG, "drawLineChartTypeB: starting to draw linechart");
            ArrayList<Entry> yAxisWeight = new ArrayList<>();

            for (int i = 0; i < mWorkoutsLiftedWeight.size(); i++) {

                float weight = (float) mWorkoutsLiftedWeight.get(i);
                Entry entry = new Entry(i, weight);
                yAxisWeight.add(entry);

            }

            ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

            LineDataSet lineDataSet = new LineDataSet(yAxisWeight, "weight");
            lineDataSet.setDrawCircles(false);
            lineDataSet.setColor(Color.BLUE);
            lineDataSet.setFillAlpha(110);

            lineDataSets.add(lineDataSet);

            LineData lineData = new LineData(lineDataSet);


            mLineChart.setData(lineData);
            Log.d(TAG, "drawLineChartTypeB: linechart drawn");
        } catch (Exception e) {
            Log.d(TAG, "drawLineChartTypeB: error: " + e);
            e.printStackTrace();
        }
    }

    public void clearLineChart(){
        mLineChart.clear();
    }
}
