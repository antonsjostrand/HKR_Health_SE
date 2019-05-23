package com.example.hkrhealth.Fragments;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.example.hkrhealth.Models.Exercise;
import com.example.hkrhealth.Models.StrengthWorkout;
import com.example.hkrhealth.R;

import java.util.Calendar;

public class StrengthBWorkoutFragment extends Fragment {

    private static final String TAG = "StrBWorkoutFragment";

    //Database
    private HkrHealthRepository mHkrHealthRepository;

    //Variables
    private final String mWorkoutType = "B";
    private int mWorkoutID;
    private StrengthWorkout mStrengthWorkout;
    private Exercise mExercise;
    private String mExerciseName;
    private int mExerciseReps;
    private double mExerciseWeight;
    private int mExerciseSet = 1;

    //UI
    private Button mSaveWorkoutButton, mYouTubeButton1,
            mYouTubeButton2, mYouTubeButton3;

    private EditText mRatingET, mCommentET;
    private TextView mHeaderOne, mHeaderTwo, mHeaderThree, mHeaderOneInfo, mHeaderTwoInfo, mHeaderThreeInfo;
    private EditText mExOneSetOneReps, mExOneSetTwoReps, mExOneSetThreeReps;
    private EditText mExOneSetOneWeight, mExOneSetTwoWeight, mExOneSetThreeWeight;
    private EditText mExTwoSetOneReps, mExTwoSetTwoReps, mExTwoSetThreeReps;
    private EditText mExTwoSetOneWeight, mExTwoSetTwoWeight, mExTwoSetThreeWeight;
    private EditText mExThreeSetOneReps, mExThreeSetTwoReps, mExThreeSetThreeReps, mExThreeSetFourReps, mExThreeSetFiveReps;
    private EditText mExThreeSetOneWeight, mExThreeSetTwoWeight, mExThreeSetThreeWeight, mExThreeSetFourWeight, mExThreeSetFiveWeight;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_strength_b_workout_layout, container, false);

        try {
            mHkrHealthRepository = new HkrHealthRepository(getActivity());

            initalizeTextViews(view);
            initalizeEditTexts(view);
            retrieveMaxWorkoutID();
            youTubeButtons(view);

            mSaveWorkoutButton = view.findViewById(R.id.saveWorkoutButton);
            mSaveWorkoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveWorkoutButtonPressed();
                }
            });
        }catch (Exception e){
            Log.d(TAG, "onCreateView: error: " + e);
            e.printStackTrace();
        }

        return view;
    }

    public void saveWorkoutButtonPressed() {
        try {
            //Creates the workout
            String date = String.valueOf(Calendar.getInstance().getTime());
            int rating = Integer.parseInt(String.valueOf(mRatingET.getText()));
            String comment = String.valueOf(mCommentET.getText());
            mStrengthWorkout = new StrengthWorkout(date, rating, comment, mWorkoutType);
            mWorkoutID++;

            //Inserts all the exercises to the database.
            insertFirstExerciseToDatabase(mWorkoutID);
            insertSecondExerciseToDatabase(mWorkoutID);
            insertThirdExerciseToDatabase(mWorkoutID);


            //Finally inserts the saved workout
            mHkrHealthRepository.insertStrengthWorkout(mStrengthWorkout);

            Log.d(TAG, "saveWorkoutButtonPressed: insertion succesful!");
        } catch (Exception e) {
            Log.d(TAG, "saveWorkoutButtonPressed: error: " + e);
        }
    }

    public void initalizeTextViews(View view) {
        mHeaderOne = view.findViewById(R.id.squatStrTW);
        mHeaderTwo = view.findViewById(R.id.militaryPressStrHeaderTW);
        mHeaderThree = view.findViewById(R.id.powerCleanStrHeaderTW);
        mHeaderOneInfo = view.findViewById(R.id.squatInfoTW);
        mHeaderTwoInfo = view.findViewById(R.id.militaryPressStrInfoTW);
        mHeaderThreeInfo = view.findViewById(R.id.powerCleanStrInfoTW);
    }

    public void initalizeEditTexts(View view) {
        mCommentET = view.findViewById(R.id.commentET);
        mRatingET = view.findViewById(R.id.ratingET);
        mExOneSetOneReps = view.findViewById(R.id.squatStrSetOneRepsET);
        mExOneSetTwoReps = view.findViewById(R.id.squatStrSetTwoRepsET);
        mExOneSetThreeReps = view.findViewById(R.id.squatStrSetThreeRepsET);
        mExOneSetOneWeight = view.findViewById(R.id.squatStrSetOneWeightET);
        mExOneSetTwoWeight = view.findViewById(R.id.squatStrSetTwoWeightET);
        mExOneSetThreeWeight = view.findViewById(R.id.squatStrSetThreeWeightET);

        mExTwoSetOneReps = view.findViewById(R.id.militaryPressStrSetOneRepsET);
        mExTwoSetTwoReps = view.findViewById(R.id.militaryPressStrSetTwoRepsET);
        mExTwoSetThreeReps = view.findViewById(R.id.militaryPressStrSetThreeRepsET);
        mExTwoSetOneWeight = view.findViewById(R.id.militaryPressStrSetOneWeightET);
        mExTwoSetTwoWeight = view.findViewById(R.id.militaryPressStrSetTwoWeightET);
        mExTwoSetThreeWeight = view.findViewById(R.id.militaryPressStrSetThreeWeightET);

        mExThreeSetOneReps = view.findViewById(R.id.powerCleanStrSetOneRepsET);
        mExThreeSetTwoReps = view.findViewById(R.id.powerCleanStrSetTwoRepsET);
        mExThreeSetThreeReps = view.findViewById(R.id.powerCleanStrSetThreeRepsET);
        mExThreeSetFourReps = view.findViewById(R.id.powerCleanStrSetFourRepsET);
        mExThreeSetFiveReps = view.findViewById(R.id.powerCleanStrSetFiveRepsET);

        mExThreeSetOneWeight = view.findViewById(R.id.powerCleanStrSetOneWeightET);
        mExThreeSetTwoWeight = view.findViewById(R.id.powerCleanStrSetTwoWeightET);
        mExThreeSetThreeWeight = view.findViewById(R.id.powerCleanStrSetThreeWeightET);
        mExThreeSetFourWeight = view.findViewById(R.id.powerCleanStrSetFourWeightET);
        mExThreeSetFiveWeight = view.findViewById(R.id.powerCleanStrSetFiveWeightET);

    }

    public void retrieveMaxWorkoutID() {
        mHkrHealthRepository.retrieveMaxWorkoutIDStrength().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (integer == null) {
                    mWorkoutID = 0;
                } else {
                    mWorkoutID = integer;
                    Log.d(TAG, "onChanged: workoutID: " + mWorkoutID);
                }

            }
        });
    }

    public void insertFirstExerciseToDatabase(int workoutID) {
        try {
            mExerciseName = String.valueOf(mHeaderOne.getText());
            mExerciseReps = Integer.parseInt(String.valueOf(mExOneSetOneReps.getText()));
            mExerciseWeight = Double.parseDouble(String.valueOf(mExOneSetOneWeight.getText()));
            mExercise = new Exercise(workoutID, mWorkoutType, mExerciseName, mExerciseReps, mExerciseWeight, mExerciseSet);
            mExerciseSet++;
            mHkrHealthRepository.insertExercise(mExercise);

            mExerciseName = String.valueOf(mHeaderOne.getText());
            mExerciseReps = Integer.parseInt(String.valueOf(mExOneSetTwoReps.getText()));
            mExerciseWeight = Double.parseDouble(String.valueOf(mExOneSetTwoWeight.getText()));
            mExercise = new Exercise(workoutID, mWorkoutType, mExerciseName, mExerciseReps, mExerciseWeight, mExerciseSet);
            mExerciseSet++;
            mHkrHealthRepository.insertExercise(mExercise);

            mExerciseName = String.valueOf(mHeaderOne.getText());
            mExerciseReps = Integer.parseInt(String.valueOf(mExOneSetThreeReps.getText()));
            mExerciseWeight = Double.parseDouble(String.valueOf(mExOneSetThreeWeight.getText()));
            mExercise = new Exercise(workoutID, mWorkoutType, mExerciseName, mExerciseReps, mExerciseWeight, mExerciseSet);
            mExerciseSet = 1;
            mHkrHealthRepository.insertExercise(mExercise);

        } catch (Exception e) {
            Log.d(TAG, "insertFirstExerciseToDatabase: error: " + e);
        }
    }

    public void insertSecondExerciseToDatabase(int workoutID) {
        try {
            mExerciseName = String.valueOf(mHeaderTwo.getText());
            mExerciseReps = Integer.parseInt(String.valueOf(mExTwoSetOneReps.getText()));
            mExerciseWeight = Double.parseDouble(String.valueOf(mExTwoSetOneWeight.getText()));
            mExercise = new Exercise(workoutID, mWorkoutType, mExerciseName, mExerciseReps, mExerciseWeight, mExerciseSet);
            mExerciseSet++;
            mHkrHealthRepository.insertExercise(mExercise);

            mExerciseName = String.valueOf(mHeaderTwo.getText());
            mExerciseReps = Integer.parseInt(String.valueOf(mExTwoSetTwoReps.getText()));
            mExerciseWeight = Double.parseDouble(String.valueOf(mExTwoSetTwoWeight.getText()));
            mExercise = new Exercise(workoutID, mWorkoutType, mExerciseName, mExerciseReps, mExerciseWeight, mExerciseSet);
            mExerciseSet++;
            mHkrHealthRepository.insertExercise(mExercise);

            mExerciseName = String.valueOf(mHeaderTwo.getText());
            mExerciseReps = Integer.parseInt(String.valueOf(mExTwoSetThreeReps.getText()));
            mExerciseWeight = Double.parseDouble(String.valueOf(mExTwoSetThreeWeight.getText()));
            mExercise = new Exercise(workoutID, mWorkoutType, mExerciseName, mExerciseReps, mExerciseWeight, mExerciseSet);
            mExerciseSet = 1;
            mHkrHealthRepository.insertExercise(mExercise);


        } catch (Exception e) {
            Log.d(TAG, "insertSecondExerciseToDatabase: error: " + e);
        }
    }

    public void insertThirdExerciseToDatabase(int workoutID) {
        try {
            mExerciseName = String.valueOf(mHeaderThree.getText());
            mExerciseReps = Integer.parseInt(String.valueOf(mExThreeSetOneReps.getText()));
            mExerciseWeight = Double.parseDouble(String.valueOf(mExThreeSetOneWeight.getText()));
            mExercise = new Exercise(workoutID, mWorkoutType, mExerciseName, mExerciseReps, mExerciseWeight, mExerciseSet);
            mExerciseSet++;
            mHkrHealthRepository.insertExercise(mExercise);

            mExerciseName = String.valueOf(mHeaderThree.getText());
            mExerciseReps = Integer.parseInt(String.valueOf(mExThreeSetTwoReps.getText()));
            mExerciseWeight = Double.parseDouble(String.valueOf(mExThreeSetTwoWeight.getText()));
            mExercise = new Exercise(workoutID, mWorkoutType, mExerciseName, mExerciseReps, mExerciseWeight, mExerciseSet);
            mExerciseSet++;
            mHkrHealthRepository.insertExercise(mExercise);

            mExerciseName = String.valueOf(mHeaderThree.getText());
            mExerciseReps = Integer.parseInt(String.valueOf(mExThreeSetThreeReps.getText()));
            mExerciseWeight = Double.parseDouble(String.valueOf(mExThreeSetThreeWeight.getText()));
            mExercise = new Exercise(workoutID,mWorkoutType,  mExerciseName, mExerciseReps, mExerciseWeight, mExerciseSet);
            mExerciseSet++;
            mHkrHealthRepository.insertExercise(mExercise);

            mExerciseName = String.valueOf(mHeaderThree.getText());
            mExerciseReps = Integer.parseInt(String.valueOf(mExThreeSetFourReps.getText()));
            mExerciseWeight = Double.parseDouble(String.valueOf(mExThreeSetFourWeight.getText()));
            mExercise = new Exercise(workoutID, mWorkoutType, mExerciseName, mExerciseReps, mExerciseWeight, mExerciseSet);
            mExerciseSet++;
            mHkrHealthRepository.insertExercise(mExercise);

            mExerciseName = String.valueOf(mHeaderThree.getText());
            mExerciseReps = Integer.parseInt(String.valueOf(mExThreeSetFiveReps.getText()));
            mExerciseWeight = Double.parseDouble(String.valueOf(mExThreeSetFiveWeight.getText()));
            mExercise = new Exercise(workoutID, mWorkoutType, mExerciseName, mExerciseReps, mExerciseWeight, mExerciseSet);
            mExerciseSet = 1;
            mHkrHealthRepository.insertExercise(mExercise);

        } catch (Exception e) {
            Log.d(TAG, "insertThirdExerciseToDatabase: error: " + e);
            e.printStackTrace();
        }
    }

    public void youTubeButtons(View view) {
        mYouTubeButton1 = view.findViewById(R.id.goToSquatButton);
        mYouTubeButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webLink = new Intent(Intent.ACTION_VIEW);
                webLink.setData(Uri.parse("https://www.youtube.com/watch?v=qz43Vtc7v4Q"));
                startActivity(webLink);
            }
        });

        mYouTubeButton2 = view.findViewById(R.id.goToMilitaryPressStrButton);
        mYouTubeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webLink = new Intent(Intent.ACTION_VIEW);
                webLink.setData(Uri.parse("https://www.youtube.com/watch?v=o6iMMdZSxB0"));
                startActivity(webLink);
            }
        });

        mYouTubeButton3 = view.findViewById(R.id.goTopowerCleantrButton);
        mYouTubeButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webLink = new Intent(Intent.ACTION_VIEW);
                webLink.setData(Uri.parse("https://www.youtube.com/watch?v=HdiJBghzkLQ"));
                startActivity(webLink);
            }
        });
    }
}

