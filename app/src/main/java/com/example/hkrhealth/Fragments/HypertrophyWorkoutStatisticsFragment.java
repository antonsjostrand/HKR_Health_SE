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
import com.example.hkrhealth.Models.HypertrophyWorkout;
import com.example.hkrhealth.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class HypertrophyWorkoutStatisticsFragment extends Fragment {

    private static final String TAG = "HypertrophyWorkoutStati";

    //Database
    private HkrHealthRepository mHkrHealthRepository;

    //UI
    private Button mPushButton, mPullButton, mLegsButton;
    private LineChart mLineChart;
    private TextView mTotalWeightTV;

    //Variables
    private ArrayList<HypertrophyWorkout> mPushWorkouts = new ArrayList<>();
    private ArrayList<HypertrophyWorkout> mPullWorkouts = new ArrayList<>();
    private ArrayList<HypertrophyWorkout> mLegsWorkouts = new ArrayList<>();
    private ArrayList<Integer> mWorkoutsLiftedWeight = new ArrayList<>();
    private int mLiftedWeight = 0;
    private boolean mPushWorkoutsAdded = false;
    private boolean mPullWorkoutsAdded = false;
    private boolean mLegsWorkoutsAdded = false;
    private boolean mLineChartDrawn = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hypertrophy_workout_statistics_layout, container, false);
        try {

            mHkrHealthRepository = new HkrHealthRepository(getActivity());

            mPushButton = view.findViewById(R.id.stat_pushButton);
            mPullButton = view.findViewById(R.id.stat_pullButton);
            mLegsButton = view.findViewById(R.id.stat_legsButton);

            mTotalWeightTV = view.findViewById(R.id.stat_totalWeightLiftedTV);

            mLineChart = view.findViewById(R.id.stat_lineChart);

            getAllPushWorkouts();
            getAllPullWorkouts();
            getAllLegsWorkouts();

            mPushButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPushButtonPressed();
                }
            });

            mPullButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPullButtonPressed();
                }
            });

            mLegsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mLegsButtonPressed();
                }
            });


        }catch (Exception e){
            Log.d(TAG, "onCreateView: Error: " + e);
        }
        return view;
    }


    public void mPushButtonPressed(){
        try {
            if (mPushWorkoutsAdded){
                if (mWorkoutsLiftedWeight.size() > 0){
                    mWorkoutsLiftedWeight.clear();
                }

                calculateTotalPushWeight();

                for (HypertrophyWorkout i : mPushWorkouts){
                    getTotalWeightPerExercisePush(i.getWorkoutID());

                    Thread.sleep(1000);
                    Log.d(TAG, "mPushButtonPressed: delay finished, moving on.");
                }
            }

        }catch (Exception e){
            Log.d(TAG, "mPushButtonPressed: error: " + e);
            e.printStackTrace();
        }
    }

    public void mPullButtonPressed(){
        try{
            if (mPullWorkoutsAdded){
                if (mWorkoutsLiftedWeight.size() > 0){
                    mWorkoutsLiftedWeight.clear();
                }

                calculateTotalPullWeight();

                for (HypertrophyWorkout i : mPullWorkouts){
                    getTotalWeightPerExercisePull(i.getWorkoutID());

                    Thread.sleep(1000);
                    Log.d(TAG, "mPullButtonPressed: delay finished.");
                }
            }
        }catch (Exception e){
            Log.d(TAG, "mPullButtonPressed: error: " + e);
            e.printStackTrace();
        }
    }

    public void mLegsButtonPressed(){
        try{
            if (mLegsWorkoutsAdded){
                if (mWorkoutsLiftedWeight.size() > 0){
                    mWorkoutsLiftedWeight.clear();
                }

                calculateTotalLegsWeight();

                for (HypertrophyWorkout i : mLegsWorkouts){
                    getTotalWeightPerExerciseLegs(i.getWorkoutID());

                    Thread.sleep(1000);
                    Log.d(TAG, "mLegsButtonPressed: delay finished, moving on.");
                }
            }
        }catch (Exception e){
            Log.d(TAG, "mLegsButtonPressed: error: " + e);
            e.printStackTrace();
        }
    }

    public void getAllPushWorkouts(){
        try {
            mHkrHealthRepository.getAllHypertrophyPushWorkouts().observe(getActivity(), new Observer<List<HypertrophyWorkout>>() {
                @Override
                public void onChanged(@Nullable List<HypertrophyWorkout> hypertrophyWorkouts) {
                    if (mPushWorkouts.size() > 0) {
                        mPushWorkouts.clear();
                    }
                    if (hypertrophyWorkouts != null) {
                        mPushWorkouts.addAll(hypertrophyWorkouts);
                        Log.d(TAG, "onChanged: added all push workouts.");
                        mPushWorkoutsAdded = true;
                    }

                }
            });
        }catch (Exception e){
            Log.d(TAG, "getAllPushWorkouts: error: " + e);
            e.printStackTrace();
        }
    }

    public void getAllPullWorkouts(){
        try {
            mHkrHealthRepository.getAllHypertrophyPullWorkouts().observe(getActivity(), new Observer<List<HypertrophyWorkout>>() {
                @Override
                public void onChanged(@Nullable List<HypertrophyWorkout> hypertrophyWorkouts) {
                    if (mPullWorkouts.size() > 0) {
                        mPullWorkouts.clear();
                    }
                    if (hypertrophyWorkouts != null) {
                        mPullWorkouts.addAll(hypertrophyWorkouts);
                        Log.d(TAG, "onChanged: added all pull workouts.");
                        mPullWorkoutsAdded = true;
                    }
                }
            });
        }catch (Exception e){
            Log.d(TAG, "getAllPullWorkouts: error: " + e);
            e.printStackTrace();
        }
    }

    public void getAllLegsWorkouts(){
        try {
            mHkrHealthRepository.getAllHypertrophyLegsWorkouts().observe(getActivity(), new Observer<List<HypertrophyWorkout>>() {
                @Override
                public void onChanged(@Nullable List<HypertrophyWorkout> hypertrophyWorkouts) {
                    if (mLegsWorkouts.size() > 0) {
                        mLegsWorkouts.clear();
                    }
                    if (hypertrophyWorkouts != null) {
                        mLegsWorkouts.addAll(hypertrophyWorkouts);
                        Log.d(TAG, "onChanged: added all legs workouts.");
                        mLegsWorkoutsAdded = true;
                    }
                }
            });
        }catch (Exception e){
            Log.d(TAG, "getAllLegsWorkouts: error: " + e);
            e.printStackTrace();
        }
    }

    public void getTotalWeightPerExercisePush(final int workoutID){
        try {
            mHkrHealthRepository.getTotalWeightForSpecificHypertrophyWorkout(workoutID).observe(getActivity(), new Observer<Integer>() {
                @Override
                public void onChanged(@Nullable Integer integer) {
                    if (integer != null) {
                        clearLineChart();
                        Log.d(TAG, "onChanged: adding weight: " + integer + ", for workout id: " + workoutID);
                        mWorkoutsLiftedWeight.add(integer);
                        drawLineChartPush();
                    }
                }
            });
        }catch (Exception e){
            Log.d(TAG, "getTotalWeightPerExercisePush: error: " + e);
            e.printStackTrace();
        }
    }

    public void getTotalWeightPerExercisePull(final int workoutID){
        try {
            mHkrHealthRepository.getTotalWeightForSpecificHypertrophyWorkout(workoutID).observe(getActivity(), new Observer<Integer>() {
                @Override
                public void onChanged(@Nullable Integer integer) {
                    if (integer != null) {
                        clearLineChart();
                        Log.d(TAG, "onChanged: adding weight: " + integer + ", for workout id: " + workoutID);
                        mWorkoutsLiftedWeight.add(integer);
                        drawLineChartPull();
                    }
                }
            });
        }catch (Exception e){
            Log.d(TAG, "getTotalWeightPerExercisePull: error: " + e);
            e.printStackTrace();
        }
    }

    public void getTotalWeightPerExerciseLegs(final int workoutID){
        try {
            mHkrHealthRepository.getTotalWeightForSpecificHypertrophyWorkout(workoutID).observe(getActivity(), new Observer<Integer>() {
                @Override
                public void onChanged(@Nullable Integer integer) {
                    if (integer != null) {
                        clearLineChart();
                        Log.d(TAG, "onChanged: adding weight: " + integer + ", for workout id: " + workoutID);
                        mWorkoutsLiftedWeight.add(integer);
                        drawLineChartLegs();
                    }
                }
            });
        }catch (Exception e){
            Log.d(TAG, "getTotalWeightPerExerciseLegs: error: " + e);
            e.printStackTrace();
        }
    }

    public void calculateTotalPushWeight(){
        try {
            mHkrHealthRepository.getTotalWeightForPushHypertrophyWorkout().observe(getActivity(), new Observer<Integer>() {
                @Override
                public void onChanged(@Nullable Integer integer) {
                    if (integer != null) {
                        Log.d(TAG, "onChanged: added weight");
                        mLiftedWeight = integer;
                        Log.d(TAG, "onChanged: total weight: " + mLiftedWeight);
                        mTotalWeightTV.setText(String.valueOf(mLiftedWeight));
                    }else{
                        Toast toast = Toast.makeText(getActivity(), "No data found for this workout type", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            });
        }catch (Exception e){
            Log.d(TAG, "calculateTotalWeight: error: " + e);
            e.printStackTrace();
        }
    }

    public void calculateTotalPullWeight(){
        try{
            mHkrHealthRepository.getTotalWeightForPullHypertrophyWorkout().observe(getActivity(), new Observer<Integer>() {
                @Override
                public void onChanged(@Nullable Integer integer) {
                    if (integer != null){
                        Log.d(TAG, "onChanged: added weight");
                        mLiftedWeight = integer;
                        Log.d(TAG, "onChanged: total weight: " + mLiftedWeight);
                        mTotalWeightTV.setText(String.valueOf(mLiftedWeight));
                    }else{
                        Toast toast = Toast.makeText(getActivity(), "No data found for this workout type", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            });
        }catch (Exception e){
            Log.d(TAG, "calculateTotalPullWeight: error: " + e);
            e.printStackTrace();
        }
    }

    public void calculateTotalLegsWeight(){
        try{
            mHkrHealthRepository.getTotalWeightForLegsHypertrophyWorkout().observe(getActivity(), new Observer<Integer>() {
                @Override
                public void onChanged(@Nullable Integer integer) {
                    if (integer != null){
                        Log.d(TAG, "onChanged: added weight");
                        mLiftedWeight = integer;
                        Log.d(TAG, "onChanged: total weight: " + mLiftedWeight);
                        mTotalWeightTV.setText(String.valueOf(mLiftedWeight));
                    }else{
                        Toast toast = Toast.makeText(getActivity(), "No data found for this workout type", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            });
        }catch (Exception e){
            Log.d(TAG, "calculateTotalLegsWeight: error: " + e);
            e.printStackTrace();
        }
    }

    public void drawLineChartPush(){
        try {
            Log.d(TAG, "drawLineChartPush: starting to draw linechart.");
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
            Log.d(TAG, "drawLineChart: linechart drawn.");
        } catch (Exception e) {
            Log.d(TAG, "drawLineChart: error: " + e);
            e.printStackTrace();
        }
    }

    public void drawLineChartPull(){
        try {
            Log.d(TAG, "drawLineChartPull: starting to draw linechart.");
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
            Log.d(TAG, "drawLineChart: linechart drawn.");
        } catch (Exception e) {
            Log.d(TAG, "drawLineChart: error: " + e);
            e.printStackTrace();
        }
    }

    public void drawLineChartLegs(){
        try {
            Log.d(TAG, "drawLineChartPull: starting to draw linechart.");
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
            Log.d(TAG, "drawLineChart: linechart drawn.");
        } catch (Exception e) {
            Log.d(TAG, "drawLineChart: error: " + e);
            e.printStackTrace();
        }
    }

    public void clearLineChart(){
        mLineChart.clear();
    }
}
