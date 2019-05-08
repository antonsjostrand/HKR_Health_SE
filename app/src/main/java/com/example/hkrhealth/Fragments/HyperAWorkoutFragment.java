package com.example.hkrhealth.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hkrhealth.Database.HkrHealthRepository;
import com.example.hkrhealth.R;

public class HyperAWorkoutFragment extends Fragment {

    private static final String TAG = "HyperAWorkoutFragment";

    //Database
    private HkrHealthRepository mHkrHealthRepository;

    //Variables
    private final String workoutType = "A";

    //UI
    private Button mSaveWorkoutButton;
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

}
