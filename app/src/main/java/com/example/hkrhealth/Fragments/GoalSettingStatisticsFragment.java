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
import android.widget.TextView;

import com.example.hkrhealth.Database.HkrHealthRepository;
import com.example.hkrhealth.Models.GoalSetting;
import com.example.hkrhealth.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class GoalSettingStatisticsFragment extends Fragment {

    private static final String TAG = "GoalSettingStatisticsFr";

    //Database
    private HkrHealthRepository mHkrHealthRepository;

    //UI
    private TextView mGoalTV, mCurrentTV, mReachTV;
    private LineChart mLineChart;

    //Variables
    private ArrayList<GoalSetting> mGoalsettings = new ArrayList<>();
    private double mGoalWeight = 0, mCurrentWeight = 0;
    private int mId;
    private GoalSetting mGoalSetting;
    private boolean mGoalWeightFetched = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goal_statistics_layout, container, false);

        try{
            mHkrHealthRepository = new HkrHealthRepository(getActivity());

            mGoalTV = view.findViewById(R.id.headerCurrentGoalTV);
            mCurrentTV = view.findViewById(R.id.headerCurrentWeightTV);
            mReachTV = view.findViewById(R.id.headerReachGoalTV);
            mLineChart = view.findViewById(R.id.lineChartGoalStatistics);

            initializeID();
            getAllGoalSettings();

        }catch (Exception e){
            Log.d(TAG, "onCreateView: error: " + e);
        }

        return view;
    }

    public void initializeID(){
        try {
            mHkrHealthRepository.getMaxIdGoal().observe(getActivity(), new Observer<Integer>() {
                @Override
                public void onChanged(@Nullable Integer integer) {
                    if (integer != null) {
                        mId = integer;
                        Log.d(TAG, "onChanged: mId: " + integer);
                        if (mId > 0) {
                            initializeGoal();
                            initializeCurrent();
                        }
                    } else {
                        mId = 0;
                    }
                }
            });
        }catch (Exception e){
            Log.d(TAG, "initializeID: error: " + e);
            e.printStackTrace();
        }
    }

    public void initializeGoal(){
        try {
            mHkrHealthRepository.getCurrentGoal().observe(getActivity(), new Observer<Double>() {
                @Override
                public void onChanged(@Nullable Double doub) {
                    if (doub != null) {
                        mGoalWeight = doub;
                        mGoalTV.setText(String.valueOf(mGoalWeight));
                        Log.d(TAG, "onChanged: got goal weight: " + mGoalWeight);
                        mGoalWeightFetched = true;
                    }
                }
            });
        }catch (Exception e){
            Log.d(TAG, "initializeGoal: error: " + e);
            e.printStackTrace();
        }
    }

    public void initializeCurrent(){
        try {
            Thread.sleep(1000);
            mHkrHealthRepository.getLatestGoalSetting(mId).observe(getActivity(), new Observer<GoalSetting>() {
                @Override
                public void onChanged(@Nullable GoalSetting goalSetting) {
                    if (goalSetting != null) {
                        mGoalSetting = goalSetting;
                        mCurrentWeight = mGoalSetting.getWeight();
                        mCurrentTV.setText(String.valueOf(mCurrentWeight));

                        if (mGoalSetting != null) {
                            initalizeReach();
                        }
                        Log.d(TAG, "onChanged: got data");
                    } else {
                        Log.d(TAG, "onChanged: no data found (initializeCurrent");
                    }
                }
            });
        }catch (Exception e){
            Log.d(TAG, "initializeCurrent: error: " + e);
            e.printStackTrace();
        }
    }

    public void initalizeReach(){
        try {
            Log.d(TAG, "initalizeReach: currentweight: " + mCurrentWeight + ", goal weight: " + mGoalWeight);
            double difference = mCurrentWeight - mGoalWeight;
            mReachTV.setText(String.valueOf(difference) + " kg");

        }catch (Exception e){
            Log.d(TAG, "initalizeReach: error: " + e);
            e.printStackTrace();
        }
    }

    public void getAllGoalSettings(){
        try {
            mHkrHealthRepository.getAllGoals().observe(getActivity(), new Observer<List<GoalSetting>>() {
                @Override
                public void onChanged(@Nullable List<GoalSetting> goalSettings) {
                    if (mGoalsettings.size() > 0) {
                        mGoalsettings.clear();
                    }
                    if (goalSettings != null) {
                        mGoalsettings.addAll(goalSettings);

                        if (mGoalsettings != null) {
                            Log.d(TAG, "onChanged: mGoalSettings size: " + mGoalsettings.size());
                            initializeLineChart();
                        }
                    } else {
                        Log.d(TAG, "onChanged: getAllGoalSettings, no data found");
                    }
                }
            });
        }catch (Exception e){
            Log.d(TAG, "getAllGoalSettings: error: " + e);
            e.printStackTrace();
        }
    }

    public void initializeLineChart() {
        try {
            ArrayList<Entry> yAxisWeight = new ArrayList<>();

            for (int i = 0; i < mGoalsettings.size(); i++) {

                float weight = (float) mGoalsettings.get(i).getWeight();
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
        }
    }
}
