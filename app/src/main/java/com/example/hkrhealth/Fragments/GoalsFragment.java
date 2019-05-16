package com.example.hkrhealth.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
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
    private String mGsName;
    private int mID;
    private int mWeight, mCuWeight;

    //UI
    private TextView mGoalsettingTV;
    private TextView mCurrentWeightTV;
    private TextView mCurrentGoalShown;
    private EditText mWeightGoal;
    private EditText mCurrentWeight;
    private Button mSaveGs;
    private TextView textView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goalsetting, container, false);

        //textView = (TextView)view.findViewById(R.id.textView1)

        mHkrHealthRepository = new HkrHealthRepository(getActivity());

        initializeTextViews(view);
        initializeEditTexts(view);

        mSaveGs = view.findViewById(R.id.saveGoalWeight);
        mSaveGs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGoalSettingButtonPressed();
            }
        });
        return view;
    }

    //Save Goal Setting
    public void saveGoalSettingButtonPressed(){
        try {
            String date = String.valueOf(Calendar.getInstance().getTime());
            int weight = Integer.parseInt(String.valueOf(mWeightGoal));
            int cuWeight = Integer.parseInt(String.valueOf(mCurrentWeight));

            mGoalSetting = new GoalSetting(mID, date, weight, cuWeight);

            //Insert to database
            insertGoalsToDatabase(mID);

            //Inserts the saved goalsettings
            //mHkrHealthRepository.insertGoalSetting(mGoalSetting);

            Log.d(TAG, "saveGoalSettingButtonPressed: Insertion successful!");
        }catch (Exception e){
            Log.d(TAG, "saveGoalSettingButtonPressed: error: " + e);
        }
    }

    public void saveCurrentWeightButtonPressed(){
        try {
            String date = String.valueOf(Calendar.getInstance().getTime());
            int weight = Integer.parseInt(String.valueOf(mWeightGoal));
            int cuWeight = Integer.parseInt(String.valueOf(mCurrentWeight));

            mGoalSetting = new GoalSetting(mID, date, weight, cuWeight);

            //Insert to database
            insertGoalsToDatabase(mID);

            //Inserts the saved goalsettings
            //mHkrHealthRepository.insertGoalSetting(mGoalSetting);

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
        mWeightGoal = view.findViewById(R.id.weigtGoalET);
        mCurrentWeight = view.findViewById(R.id.currentWeightET);
    }

    public void insertGoalsToDatabase (int mID){
        try{
            mGsName = String.valueOf(mGoalsettingTV.getText());
            mWeight = Integer.parseInt(String.valueOf(mWeightGoal.getText()));
            mCuWeight = Integer.parseInt(String.valueOf(mCurrentWeight.getText()));

            mGoalSetting = new GoalSetting(mID, mGsName, mWeight, mCuWeight);

        }catch (Exception e){
            Log.d(TAG, "insertGoalsToDatabase: error: " + e);
        }
    }

}

