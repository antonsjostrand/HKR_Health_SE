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
import com.example.hkrhealth.Models.HypertrophyWorkout;
import com.example.hkrhealth.R;

import java.util.Calendar;

public class HyperCWorkoutFragment extends Fragment {

    private static final String TAG = "HyperCWorkoutFragment";

    //Database
    private HkrHealthRepository mHkrHealthRepository;

    //Variables
    private final String mWorkoutType = "Legs";
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hyper_c_workout_layout, container, false);

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
        mHeaderOne = view.findViewById(R.id.squatHeaderTW);
        mHeaderTwo = view.findViewById(R.id.legPressHeaderTW);
        mHeaderThree = view.findViewById(R.id.romanianDeadliftHeaderTW);
        mHeaderFour = view.findViewById(R.id.legCurlHeaderTW);
        mHeaderFive = view.findViewById(R.id.lyingLegCurlHeaderTW);
        mHeaderSix = view.findViewById(R.id.standingCalfRaiseHeaderTW);
    }

    public void initalizeEditTexts(View view){
        mCommentET = view.findViewById(R.id.commentET);
        mRatingET = view.findViewById(R.id.ratingET);
        mExOneSetOneReps = view.findViewById(R.id.squatSetOneRepsET);
        mExOneSetTwoReps = view.findViewById(R.id.squatSetTwoRepsET);
        mExOneSetThreeReps = view.findViewById(R.id.squatSetThreeRepsET);
        mExOneSetOneWeight = view.findViewById(R.id.squatSetOneWeightET);
        mExOneSetTwoWeight = view.findViewById(R.id.squatSetTwoWeightET);
        mExOneSetThreeWeight = view.findViewById(R.id.squatSetThreeWeightET);
        mExTwoSetOneReps = view.findViewById(R.id.legPressSetOneRepsET);
        mExTwoSetTwoReps = view.findViewById(R.id.legPressSetTwoRepsET);
        mExTwoSetThreeReps = view.findViewById(R.id.legPressSetThreeRepsET);
        mExTwoSetOneWeight = view.findViewById(R.id.legPressSetOneWeightET);
        mExTwoSetTwoWeight = view.findViewById(R.id.legPressSetTwoWeightET);
        mExTwoSetThreeWeight = view.findViewById(R.id.legPressSetThreeWeightET);
        mExThreeSetOneReps = view.findViewById(R.id.romanianDeadliftSetOneRepsET);
        mExThreeSetTwoReps = view.findViewById(R.id.romanianDeadliftSetTwoRepsET);
        mExThreeSetThreeReps = view.findViewById(R.id.romanianDeadliftSetThreeRepsET);
        mExThreeSetOneWeight = view.findViewById(R.id.romanianDeadliftSetOneWeightET);
        mExThreeSetTwoWeight = view.findViewById(R.id.romanianDeadliftSetTwoWeightET);
        mExThreeSetThreeWeight = view.findViewById(R.id.romanianDeadliftSetThreeWeightET);
        mExFourSetOneReps = view.findViewById(R.id.legCurlSetOneRepsET);
        mExFourSetTwoReps = view.findViewById(R.id.legCurlSetTwoRepsET);
        mExFourSetThreeReps = view.findViewById(R.id.legCurlSetThreeRepsET);
        mExFourSetOneWeight = view.findViewById(R.id.legCurlSetOneWeightET);
        mExFourSetTwoWeight = view.findViewById(R.id.legCurlSetTwoWeightET);
        mExFourSetThreeWeight = view.findViewById(R.id.legCurlSetThreeWeightET);
        mExFiveSetOneReps = view.findViewById(R.id.lyingLegCurlSetOneRepsET);
        mExFiveSetTwoReps = view.findViewById(R.id.lyingLegCurlSetTwoRepsET);
        mExFiveSetThreeReps = view.findViewById(R.id.lyingLegCurlSetThreeRepsET);
        mExFiveSetOneWeight = view.findViewById(R.id.lyingLegCurlSetOneWeightET);
        mExFiveSetTwoWeight = view.findViewById(R.id.lyingLegCurlSetTwoWeightET);
        mExFiveSetThreeWeight = view.findViewById(R.id.lyingLegCurlSetThreeWeightET);
        mExSixSetOneReps = view.findViewById(R.id.standingCalfRaiseSetOneRepsET);
        mExSixSetTwoReps = view.findViewById(R.id.standingCalfRaiseSetTwoRepsET);
        mExSixSetThreeReps = view.findViewById(R.id.standingCalfRaiseSetThreeRepsET);
        mExSixSetOneWeight = view.findViewById(R.id.standingCalfRaiseSetOneWeightET);
        mExSixSetTwoWeight = view.findViewById(R.id.standingCalfRaiseSetTwoWeightET);
        mExSixSetThreeWeight = view.findViewById(R.id.standingCalfRaiseSetThreeWeightET);
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
        mYouTubeButton1 = view.findViewById(R.id.goToSquatButton);
        mYouTubeButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webLink = new Intent(Intent.ACTION_VIEW);
                webLink.setData(Uri.parse("https://www.youtube.com/watch?v=qz43Vtc7v4Q"));
                startActivity(webLink);
            }
        });

        mYouTubeButton2 = view.findViewById(R.id.goToLegPressButton);
        mYouTubeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webLink = new Intent(Intent.ACTION_VIEW);
                webLink.setData(Uri.parse("https://www.youtube.com/watch?v=S6gNwZhxTW4"));
                startActivity(webLink);
            }
        });

        mYouTubeButton3 = view.findViewById(R.id.goToRomanianDeadliftButton);
        mYouTubeButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webLink = new Intent(Intent.ACTION_VIEW);
                webLink.setData(Uri.parse("https://www.youtube.com/watch?v=F8-LJWcEk1M"));
                startActivity(webLink);
            }
        });

        mYouTubeButton4 = view.findViewById(R.id.goToLegCurlButton);
        mYouTubeButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webLink = new Intent(Intent.ACTION_VIEW);
                webLink.setData(Uri.parse("https://www.youtube.com/watch?v=Q303oFwI7Xk"));
                startActivity(webLink);
            }
        });

        mYouTubeButton5 = view.findViewById(R.id.goToLyingLegCurlButton);
        mYouTubeButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webLink = new Intent(Intent.ACTION_VIEW);
                webLink.setData(Uri.parse("https://www.youtube.com/watch?v=A40C3d3UVFA"));
                startActivity(webLink);
            }
        });

        mYouTubeButton6 = view.findViewById(R.id.goToStandingCalfRaiseButton);
        mYouTubeButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webLink = new Intent(Intent.ACTION_VIEW);
                webLink.setData(Uri.parse("https://www.youtube.com/watch?v=qBe3el4Xxm4"));
                startActivity(webLink);
            }
        });



    }

}
