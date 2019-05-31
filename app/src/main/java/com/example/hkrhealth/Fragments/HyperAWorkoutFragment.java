package com.example.hkrhealth.Fragments;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.net.Uri;
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
import com.example.hkrhealth.Models.Exercise;
import com.example.hkrhealth.Models.HypertrophyWorkout;
import com.example.hkrhealth.R;

import java.util.Calendar;

public class HyperAWorkoutFragment extends Fragment {

    private static final String TAG = "HyperAWorkoutFragment";

    //Database
    private HkrHealthRepository mHkrHealthRepository;

    //Variables
    private final String mWorkoutType = "Push";
    private int mWorkoutID;
    private HypertrophyWorkout mHypertrophyWorkout;
    private Exercise mExercise;
    private String mExerciseName;
    private int mExerciseReps;
    private double mExerciseWeight;
    private int mExerciseSet = 1;

    //UI
    private Button mSaveWorkoutButton, mYouTubeButton1, mYouTubeButton2,
            mYouTubeButton3, mYouTubeButton4,
            mYouTubeButton5, mYouTubeButton6;

    private EditText mRatingET, mCommentET;
    private TextView mHeaderOne, mHeaderTwo, mHeaderThree, mHeaderFour, mHeaderFive, mHeaderSix;
    private EditText mExOneSetOneReps, mExOneSetTwoReps, mExOneSetThreeReps;
    private EditText mExOneSetOneWeight, mExOneSetTwoWeight, mExOneSetThreeWeight;
    private EditText mExTwoSetOneReps, mExTwoSetTwoReps, mExTwoSetThreeReps;
    private EditText mExTwoSetOneWeight, mExTwoSetTwoWeight, mExTwoSetThreeWeight;
    private EditText mExThreeSetOneReps, mExThreeSetTwoReps, mExThreeSetThreeReps;
    private EditText mExThreeSetOneWeight, mExThreeSetTwoWeight, mExThreeSetThreeWeight;
    private EditText mExFourSetOneReps, mExFourSetTwoReps, mExFourSetThreeReps;
    private EditText mExFourSetOneWeight, mExFourSetTwoWeight, mExFourSetThreeWeight;
    private EditText mExFiveSetOneReps, mExFiveSetTwoReps, mExFiveSetThreeReps;
    private EditText mExFiveSetOneWeight, mExFiveSetTwoWeight, mExFiveSetThreeWeight;
    private EditText mExSixSetOneReps, mExSixSetTwoReps, mExSixSetThreeReps;
    private EditText mExSixSetOneWeight, mExSixSetTwoWeight, mExSixSetThreeWeight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hyper_a_workout_layout, container, false);

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

        return view;
    }

    public void saveWorkoutButtonPressed(){
        try {
            //Creates the workout
            String date = String.valueOf(Calendar.getInstance().getTime());
            int rating = Integer.parseInt(String.valueOf(mRatingET.getText()));
            String comment = String.valueOf(mCommentET.getText());
            mHypertrophyWorkout = new HypertrophyWorkout(date, rating, comment, mWorkoutType);
            mWorkoutID++;

            //Inserts all the exercises to the database.
            insertFirstExerciseToDatabase(mWorkoutID);
            insertSecondExerciseToDatabase(mWorkoutID);
            insertThirdExerciseToDatabase(mWorkoutID);
            insertFourthExerciseToDatabase(mWorkoutID);
            insertFifthExerciseToDatabase(mWorkoutID);
            insertSixthExerciseToDatabase(mWorkoutID);

            //Finally inserts the saved workout
            mHkrHealthRepository.insertHypertrophyWorkout(mHypertrophyWorkout);
            Log.d(TAG, "saveWorkoutButtonPressed: insertion succesful!");
        }catch (Exception e){
            Log.d(TAG, "saveWorkoutButtonPressed: error: " + e);
        }
    }

    public void initalizeTextViews(View view){

        mHeaderOne = view.findViewById(R.id.benchPressHeaderTW);
        mHeaderTwo = view.findViewById(R.id.InclineBenchPressHeaderTW);
        mHeaderThree = view.findViewById(R.id.exerciseThreeHeaderTW);
        mHeaderFour = view.findViewById(R.id.exerciseFourHeaderTW);
        mHeaderFive = view.findViewById(R.id.exerciseFiveHeaderTW);
        mHeaderSix = view.findViewById(R.id.exerciseSixHeaderTW);

    }

    public void initalizeEditTexts(View view){
        mCommentET = view.findViewById(R.id.commentET);
        mRatingET = view.findViewById(R.id.ratingET);


        mExOneSetOneReps = view.findViewById(R.id.benchPressSetOneRepsET);
        mExOneSetTwoReps = view.findViewById(R.id.benchPressSetTwoRepsET);
        mExOneSetThreeReps = view.findViewById(R.id.benchPressSetThreeRepsET);
        mExOneSetOneWeight = view.findViewById(R.id.benchPressSetOneWeightET);
        mExOneSetTwoWeight = view.findViewById(R.id.benchPressSetTwoWeightET);
        mExOneSetThreeWeight = view.findViewById(R.id.benchPressSetThreeWeightET);
        mExTwoSetOneReps = view.findViewById(R.id.inclineBenchPressSetOneRepsET);
        mExTwoSetTwoReps = view.findViewById(R.id.inclineBenchPressSetTwoRepsET);
        mExTwoSetThreeReps = view.findViewById(R.id.inclineBenchPressSetThreeRepsET);
        mExTwoSetOneWeight = view.findViewById(R.id.inclineBenchPressSetOneWeightET);
        mExTwoSetTwoWeight = view.findViewById(R.id.inclineBenchPressSetTwoWeightET);
        mExTwoSetThreeWeight = view.findViewById(R.id.inclineBenchPressSetThreeWeightET);
        mExThreeSetOneReps = view.findViewById(R.id.exerciseThreeSetOneRepsET);
        mExThreeSetTwoReps = view.findViewById(R.id.exerciseThreeSetTwoRepsET);
        mExThreeSetThreeReps = view.findViewById(R.id.exerciseThreeSetThreeRepsET);
        mExThreeSetOneWeight = view.findViewById(R.id.exerciseThreeSetOneWeightET);
        mExThreeSetTwoWeight = view.findViewById(R.id.exerciseThreeSetTwoWeightET);
        mExThreeSetThreeWeight = view.findViewById(R.id.exerciseThreeSetThreeWeightET);
        mExFourSetOneReps = view.findViewById(R.id.exerciseFourSetOneRepsET);
        mExFourSetTwoReps = view.findViewById(R.id.exerciseFourSetTwoRepsET);
        mExFourSetThreeReps = view.findViewById(R.id.exerciseFourSetThreeRepsET);
        mExFourSetOneWeight = view.findViewById(R.id.exerciseFourSetOneWeightET);
        mExFourSetTwoWeight = view.findViewById(R.id.exerciseFourSetTwoWeightET);
        mExFourSetThreeWeight = view.findViewById(R.id.exerciseFourSetThreeWeightET);
        mExFiveSetOneReps = view.findViewById(R.id.exerciseFiveSetOneRepsET);
        mExFiveSetTwoReps = view.findViewById(R.id.exerciseFiveSetTwoRepsET);
        mExFiveSetThreeReps = view.findViewById(R.id.exerciseFiveSetThreeRepsET);
        mExFiveSetOneWeight = view.findViewById(R.id.exerciseFiveSetOneWeightET);
        mExFiveSetTwoWeight = view.findViewById(R.id.exerciseFiveSetTwoWeightET);
        mExFiveSetThreeWeight = view.findViewById(R.id.exerciseFiveSetThreeWeightET);
        mExSixSetOneReps = view.findViewById(R.id.exerciseSixSetOneRepsET);
        mExSixSetTwoReps = view.findViewById(R.id.exerciseSixSetTwoRepsET);
        mExSixSetThreeReps = view.findViewById(R.id.exerciseSixSetThreeRepsET);
        mExSixSetOneWeight = view.findViewById(R.id.exerciseSixSetOneWeightET);
        mExSixSetTwoWeight = view.findViewById(R.id.exerciseSixSetTwoWeightET);
        mExSixSetThreeWeight = view.findViewById(R.id.exerciseSixSetThreeWeightET);

    }

    public void retrieveMaxWorkoutID(){
        mHkrHealthRepository.retrieveMaxWorkoutID().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (integer == null){
                    mWorkoutID = 0;
                }else {
                    mWorkoutID = integer;
                    Log.d(TAG, "onChanged: workoutID: " + mWorkoutID);
                }

            }
        });
    }

    public void insertFirstExerciseToDatabase(int workoutID){
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

        }catch (Exception e){
            Log.d(TAG, "insertFirstExerciseToDatabase: error: " + e);
        }
    }

    public void insertSecondExerciseToDatabase(int workoutID){
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

        }catch (Exception e){
            Log.d(TAG, "insertSecondExerciseToDatabase: error: " + e);
        }
    }

    public void insertThirdExerciseToDatabase(int workoutID){
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
            mExercise = new Exercise(workoutID, mWorkoutType, mExerciseName, mExerciseReps, mExerciseWeight, mExerciseSet);
            mExerciseSet = 1;
            mHkrHealthRepository.insertExercise(mExercise);

        }catch (Exception e){
            Log.d(TAG, "insertThirdExerciseToDatabase: error: " + e);
        }
    }

    public void insertFourthExerciseToDatabase(int workoutID){
        try {
            mExerciseName = String.valueOf(mHeaderFour.getText());
            mExerciseReps = Integer.parseInt(String.valueOf(mExFourSetOneReps.getText()));
            mExerciseWeight = Double.parseDouble(String.valueOf(mExFourSetOneWeight.getText()));
            mExercise = new Exercise(workoutID, mWorkoutType, mExerciseName, mExerciseReps, mExerciseWeight, mExerciseSet);
            mExerciseSet++;
            mHkrHealthRepository.insertExercise(mExercise);

            mExerciseName = String.valueOf(mHeaderFour.getText());
            mExerciseReps = Integer.parseInt(String.valueOf(mExFourSetTwoReps.getText()));
            mExerciseWeight = Double.parseDouble(String.valueOf(mExFourSetTwoWeight.getText()));
            mExercise = new Exercise(workoutID, mWorkoutType, mExerciseName, mExerciseReps, mExerciseWeight, mExerciseSet);
            mExerciseSet++;
            mHkrHealthRepository.insertExercise(mExercise);

            mExerciseName = String.valueOf(mHeaderFour.getText());
            mExerciseReps = Integer.parseInt(String.valueOf(mExFourSetThreeReps.getText()));
            mExerciseWeight = Double.parseDouble(String.valueOf(mExFourSetThreeWeight.getText()));
            mExercise = new Exercise(workoutID, mWorkoutType, mExerciseName, mExerciseReps, mExerciseWeight, mExerciseSet);
            mExerciseSet = 1;
            mHkrHealthRepository.insertExercise(mExercise);

        }catch (Exception e){
            Log.d(TAG, "insertFourthExerciseToDatabase: error: " + e);
        }
    }

    public void insertFifthExerciseToDatabase(int workoutID){
        try {
            mExerciseName = String.valueOf(mHeaderFive.getText());
            mExerciseReps = Integer.parseInt(String.valueOf(mExFiveSetOneReps.getText()));
            mExerciseWeight = Double.parseDouble(String.valueOf(mExFiveSetOneWeight.getText()));
            mExercise = new Exercise(workoutID, mWorkoutType, mExerciseName, mExerciseReps, mExerciseWeight, mExerciseSet);
            mExerciseSet++;
            mHkrHealthRepository.insertExercise(mExercise);

            mExerciseName = String.valueOf(mHeaderFive.getText());
            mExerciseReps = Integer.parseInt(String.valueOf(mExFiveSetTwoReps.getText()));
            mExerciseWeight = Double.parseDouble(String.valueOf(mExFiveSetTwoWeight.getText()));
            mExercise = new Exercise(workoutID, mWorkoutType, mExerciseName, mExerciseReps, mExerciseWeight, mExerciseSet);
            mExerciseSet++;
            mHkrHealthRepository.insertExercise(mExercise);

            mExerciseName = String.valueOf(mHeaderFive.getText());
            mExerciseReps = Integer.parseInt(String.valueOf(mExFiveSetThreeReps.getText()));
            mExerciseWeight = Double.parseDouble(String.valueOf(mExFiveSetThreeWeight.getText()));
            mExercise = new Exercise(workoutID, mWorkoutType, mExerciseName, mExerciseReps, mExerciseWeight, mExerciseSet);
            mExerciseSet = 1;
            mHkrHealthRepository.insertExercise(mExercise);

        }catch (Exception e){
            Log.d(TAG, "insertFifthExerciseToDatabase: error: " + e);
        }
    }

    public void insertSixthExerciseToDatabase(int workoutID){
        try {
            mExerciseName = String.valueOf(mHeaderSix.getText());
            mExerciseReps = Integer.parseInt(String.valueOf(mExSixSetOneReps.getText()));
            mExerciseWeight = Double.parseDouble(String.valueOf(mExSixSetOneWeight.getText()));
            mExercise = new Exercise(workoutID, mWorkoutType, mExerciseName, mExerciseReps, mExerciseWeight, mExerciseSet);
            mExerciseSet++;
            mHkrHealthRepository.insertExercise(mExercise);

            mExerciseName = String.valueOf(mHeaderSix.getText());
            mExerciseReps = Integer.parseInt(String.valueOf(mExSixSetTwoReps.getText()));
            mExerciseWeight = Double.parseDouble(String.valueOf(mExSixSetTwoWeight.getText()));
            mExercise = new Exercise(workoutID, mWorkoutType, mExerciseName, mExerciseReps, mExerciseWeight, mExerciseSet);
            mExerciseSet++;
            mHkrHealthRepository.insertExercise(mExercise);

            mExerciseName = String.valueOf(mHeaderSix.getText());
            mExerciseReps = Integer.parseInt(String.valueOf(mExSixSetThreeReps.getText()));
            mExerciseWeight = Double.parseDouble(String.valueOf(mExSixSetThreeWeight.getText()));
            mExercise = new Exercise(workoutID, mWorkoutType, mExerciseName, mExerciseReps, mExerciseWeight, mExerciseSet);
            mExerciseSet = 1;
            mHkrHealthRepository.insertExercise(mExercise);

        }catch (Exception e){
            Log.d(TAG, "insertSixthExerciseToDatabase: error: " + e);
        }
    }


    public void youTubeButtons(View view){
        try {

            mYouTubeButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent webLink = new Intent(Intent.ACTION_VIEW);
                    webLink.setData(Uri.parse("https://www.youtube.com/watch?v=o6iMMdZSxB0"));
                    startActivity(webLink);
                }
            });
        }catch (NullPointerException e) {
            e.printStackTrace();
        }

        mYouTubeButton2 = view.findViewById(R.id.goToInclineBenchPressButton);
        mYouTubeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webLink = new Intent(Intent.ACTION_VIEW);
                webLink.setData(Uri.parse("https://www.youtube.com/watch?v=wQTR3SN4htw"));
                startActivity(webLink);
            }
        });

        mYouTubeButton3 = view.findViewById(R.id.goToExerciseThreeButton);
        mYouTubeButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webLink = new Intent(Intent.ACTION_VIEW);
                webLink.setData(Uri.parse("https://www.youtube.com/watch?v=vmi-Xhxt-Ao"));
                startActivity(webLink);
            }
        });

        mYouTubeButton4 = view.findViewById(R.id.goToExerciseFourButton);
        mYouTubeButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webLink = new Intent(Intent.ACTION_VIEW);
                webLink.setData(Uri.parse("https://www.youtube.com/watch?v=bSJbQrZ5NZQ"));
                startActivity(webLink);
            }
        });

       mYouTubeButton5 = view.findViewById(R.id.goToExerciseFiveButton);
        mYouTubeButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webLink = new Intent(Intent.ACTION_VIEW);
                webLink.setData(Uri.parse("https://www.youtube.com/watch?v=DrMtnfhH9Rk"));
                startActivity(webLink);
            }
        });

       mYouTubeButton6 = view.findViewById(R.id.goToExerciseSixButton);
        mYouTubeButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webLink = new Intent(Intent.ACTION_VIEW);
                webLink.setData(Uri.parse("https://www.youtube.com/watch?v=cTus_cugCSs"));
                startActivity(webLink);
            }
        });



    }
}
