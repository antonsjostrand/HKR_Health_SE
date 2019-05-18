package com.example.hkrhealth.Fragments;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hkrhealth.Database.HkrHealthRepository;
import com.example.hkrhealth.Models.GoalSetting;
import com.example.hkrhealth.R;

import java.util.Calendar;

public class GoalsFragment extends Fragment {

    private static final String TAG = "GoalSettingFragment";

    //Database
    private HkrHealthRepository mHkrHealthRepository;

    //Variables
    private GoalSetting mGoalSetting;
    private int mCuGoal;

    //UI
    private TextView mGoalsettingTV;
    private TextView mCurrentWeightTV;
    private TextView mCurrentGoalShown;
    private EditText mWeightGoalET;
    private EditText mCurrentWeightET;
    private Button mSaveGsButton, mSaveCurrentWeightButton;
    private TextView textView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goalsetting, container, false);

        //textView = (TextView)view.findViewById(R.id.textView1)

        mHkrHealthRepository = new HkrHealthRepository(getActivity());

        initializeTextViews(view);
        initializeEditTexts(view);

        getCurrentGoal();

        mSaveGsButton = view.findViewById(R.id.saveGoalWeightButton);
        mSaveGsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGoalSettingButtonPressed();
            }
        });

        mSaveCurrentWeightButton = view.findViewById(R.id.saveCurrentWeightButton);
        mSaveCurrentWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCurrentWeightButtonPressed();
            }
        });
        return view;
    }

    //Save Goal Setting
    public void saveGoalSettingButtonPressed(){
        try {
            String date = String.valueOf(Calendar.getInstance().getTime());
            int goalWeight = Integer.parseInt(String.valueOf(mWeightGoalET.getText()));

            mGoalSetting = new GoalSetting(date, goalWeight);
            
            if (mCuGoal == 0) {
                mHkrHealthRepository.insertGoal(mGoalSetting);
            }else{
                mHkrHealthRepository.updateGoal(goalWeight);
            }

            Log.d(TAG, "saveGoalSettingButtonPressed: Insertion successful!");
        }catch (Exception e){
            Log.d(TAG, "saveGoalSettingButtonPressed: error: " + e);
        }
    }

    public void saveCurrentWeightButtonPressed(){
        try {
            String date = String.valueOf(Calendar.getInstance().getTime());
            int weight = Integer.parseInt(String.valueOf(mCurrentWeightET.getText()));

            mGoalSetting = new GoalSetting(date, weight);

            mHkrHealthRepository.insertGoal(mGoalSetting);

            Log.d(TAG, "saveCurrentWeightButtonPressed: Insertion successful!");
        }catch (Exception e){
            Log.d(TAG, "saveCurrentWeightButtonPressed: error: " + e);
        }
    }

    public void initializeTextViews(View view){
        mGoalsettingTV = view.findViewById(R.id.goalSettingTV);
        mCurrentWeightTV = view.findViewById(R.id.currentWeightTV);
        mCurrentGoalShown = view.findViewById(R.id.currentGoalShownTV);

    }

    public void initializeEditTexts(View view){
        mWeightGoalET = view.findViewById(R.id.weigtGoalET);
        mCurrentWeightET = view.findViewById(R.id.currentWeightET);
    }

    public void getCurrentGoal(){
        mHkrHealthRepository.getCurrentGoal().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (integer != null){
                    mCuGoal = integer;
                    mCurrentGoalShown.setText(String.valueOf(mCuGoal));
                }else{
                    mCuGoal = 0;
                    mCurrentGoalShown.setText(R.string.zero);
                }
            }
        });
    }
}

