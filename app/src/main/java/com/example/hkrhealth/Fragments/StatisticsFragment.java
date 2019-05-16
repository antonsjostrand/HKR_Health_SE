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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hkrhealth.Database.HkrHealthRepository;
import com.example.hkrhealth.Models.Exercise;
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

    //Variables
    private ArrayList<Exercise> mExercises = new ArrayList<>();
    private String mChosenReps;
    private Double mSmallest1Rm, mBiggest1Rm;


    //UI
    private AutoCompleteTextView mSearchFieldACTV;
    private TextView mMaxLiftTV, mTotalRepsTV, mTotalWeightTV, mIncreaseTV;
    private LineChart mLineChart;
    private Button mConfirmButton, mConfirmRepsButton;
    private EditText mEnterRepsET;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics_layout, container, false);

        try {
            mHkrHealthRepository = new HkrHealthRepository(getActivity());

            mIncreaseTV = view.findViewById(R.id.percentualIncreaseTV);
            mConfirmRepsButton = view.findViewById(R.id.repsConfirmButton);
            mEnterRepsET = view.findViewById(R.id.repsChoiceET);
            mTotalWeightTV = view.findViewById(R.id.totalWeightTV);
            mConfirmButton = view.findViewById(R.id.confirmButton);
            mSearchFieldACTV = view.findViewById(R.id.searchFieldACTV);
            mMaxLiftTV = view.findViewById(R.id.heaviestLiftTV);
            mTotalRepsTV = view.findViewById(R.id.totalRepsTV);
            mLineChart = view.findViewById(R.id.exerciseLineChart);

            //Initialize ACTV
            List<String> exerciseList = Arrays.asList(getResources().getStringArray(R.array.array_exercises));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, exerciseList);
            mSearchFieldACTV.setAdapter(adapter);

            //Set on click listener
            mConfirmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmButtonPressed(String.valueOf(mSearchFieldACTV.getText()));
                }
            });

            mConfirmRepsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        mChosenReps = String.valueOf(mEnterRepsET.getText());
                        confirmRepsButtonPressed(String.valueOf(mSearchFieldACTV.getText()), Integer.parseInt(mChosenReps));
                    }catch (Exception e){
                        Log.d(TAG, "onClick: mConfirmRepsButton: " + e);
                    }
                }
            });

        }catch (Exception e){
            Log.d(TAG, "onCreateView: error: " + e);
        }
        return view;
    }

    public void confirmRepsButtonPressed(String exerciseName, int exerciseReps){
        try {
            clearLineChart();
            getAllExercisesBySearchedNameAndReps(exerciseName, exerciseReps);
            if (mExercises.size() > 0) {
                drawLineChart();
            }
        }catch (Exception e){
            Log.d(TAG, "confirmRepsButtonPressed: error: " + e);
        }
    }

    public void confirmButtonPressed(String exerciseName){
        try {
            Log.d(TAG, "confirmButtonPressed: getting information for exercise: " + exerciseName);
            clearLineChart();
            clearFields();
            getBiggestAndSmallest1RmForExerciseByName(exerciseName);
            getMaximumLiftFromExerciseByName(exerciseName);
            getTotalAmountOfRepsForExerciseByName(exerciseName);
            getTotalWeightLiftedForExerciseByName(exerciseName);

        }catch (Exception e){
            Log.d(TAG, "confirmButtonPressed: error: " + e);
        }
    }

    public void clearFields(){
        mMaxLiftTV.setText("");
        mTotalRepsTV.setText("");
        mTotalWeightTV.setText("");
    }

    public void clearLineChart(){
        mLineChart.clear();
    }

    public void getAllExercisesBySearchedNameAndReps(String exerciseName, int exerciseReps){
        mHkrHealthRepository.getAllExercisesByNameAndReps(exerciseName, exerciseReps).observe(getActivity(), new Observer<List<Exercise>>() {
            @Override
            public void onChanged(@Nullable List<Exercise> exercises) {
                if (mExercises.size() > 0){
                    mExercises.clear();
                }
                if (exercises != null){
                    mExercises.addAll(exercises);
                }else{
                    mEnterRepsET.setText("No data for this number of repetitions.");
                }
            }
        });
    }

    public void getMaximumLiftFromExerciseByName(String exericseName){
        mHkrHealthRepository.getMaximumLiftFromExerciseByName(exericseName).observe(getActivity(), new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double aDouble) {
                if (aDouble == 0){
                    mMaxLiftTV.setText("0");
                }else {
                    mMaxLiftTV.setText(String.valueOf(aDouble + " kg"));
                }
            }
        });
    }

    public void getTotalAmountOfRepsForExerciseByName(String exerciseName){
        mHkrHealthRepository.getTotalAmountOfRepsExerciseByName(exerciseName).observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (integer == 0){
                    mTotalRepsTV.setText("0");
                }else {
                    mTotalRepsTV.setText(String.valueOf(integer + " reps"));
                }
            }
        });
    }

    public void getTotalWeightLiftedForExerciseByName(String exerciseName){
        mHkrHealthRepository.getTotalAmountOfWeightLiftedExerciseByName(exerciseName).observe(getActivity(), new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double aDouble) {
                if (aDouble == 0){
                    mTotalWeightTV.setText("0");
                }else {
                    mTotalWeightTV.setText(String.valueOf(aDouble / 1000 + " ton"));
                }
            }
        });
    }

    public void getBiggestAndSmallest1RmForExerciseByName(String exerciseName){
        final String name = exerciseName;
        mHkrHealthRepository.getBiggest1RmForExerciseByName(exerciseName).observe(getActivity(), new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double aDouble) {
                Log.d(TAG, "onChanged: biggest value: " + aDouble);
                if (aDouble != null){
                    mBiggest1Rm = aDouble;
                    Log.d(TAG, "onChanged: biggest value: " + mBiggest1Rm);

                    mHkrHealthRepository.getSmallest1RmForExerciseByName(name).observe(getActivity(), new Observer<Double>() {
                        @Override
                        public void onChanged(@Nullable Double aDouble) {
                            Log.d(TAG, "onChanged: smallest value: " + aDouble);
                            if (aDouble != null) {
                                mSmallest1Rm = aDouble;
                                Log.d(TAG, "onChanged: smallest value: " + mSmallest1Rm);

                                calculateAndSetPercentualIncrease(mSmallest1Rm, mBiggest1Rm);
                            }
                        }
                    });

                }
            }
        });
    }

    public void calculateAndSetPercentualIncrease(double smallest, double biggest){
        try {
            Log.d(TAG, "calculateAndSetPercentualIncrease: calculating percent");
            double percentualIncrease;
            String percent;

            percentualIncrease = biggest / smallest;
            percent = String.valueOf(percentualIncrease);
            percent = percent.substring(1, 3);
            mIncreaseTV.setText(String.valueOf(percent) + "%");
            Log.d(TAG, "calculateAndSetPercentualIncrease: percent set, value: " + String.valueOf(percent));
        }catch (Exception e){
            Log.d(TAG, "calculateAndSetPercentualIncrease: error: " + e);
        }

    }

    public void drawLineChart() {
        try {
            ArrayList<Entry> yAxesWeight = new ArrayList<>();
            ArrayList<String> xAxes = new ArrayList<>();


            for (int i = 0; i < mExercises.size(); i++) {

                Entry entry = new Entry(Float.parseFloat(String.valueOf(mExercises.get(i).getExerciseWeight())), i);
                yAxesWeight.add(entry);

                xAxes.add(i, String.valueOf(i));
            }

            String[] xAxesFinal = new String[xAxes.size()];
            for (int i = 0; i < xAxes.size(); i++) {
                xAxesFinal[i] = xAxes.get(i);
            }

            ArrayList<LineDataSet> lineDataSets = new ArrayList<>();

            LineDataSet lineDataSet = new LineDataSet(yAxesWeight, "weight");
            lineDataSet.setDrawCircles(false);
            lineDataSet.setColor(Color.BLUE);

            lineDataSets.add(lineDataSet);

            LineData lineData = new LineData(lineDataSet);

            mLineChart.setData(lineData);
            Log.d(TAG, "drawLineChart: linechart drawn.");
        } catch (Exception e) {
            Log.d(TAG, "drawLineChart: error: " + e);
        }
    }
}
