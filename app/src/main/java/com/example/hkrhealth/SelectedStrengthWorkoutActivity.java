package com.example.hkrhealth;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.hkrhealth.Adapters.ExerciseRecyclerAdapter;
import com.example.hkrhealth.Database.HkrHealthRepository;
import com.example.hkrhealth.Models.Exercise;
import com.example.hkrhealth.Models.StrengthWorkout;
import com.example.hkrhealth.Util.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.List;

public class SelectedStrengthWorkoutActivity extends AppCompatActivity {

    private static final String TAG = "SelectedStrengthWork";

    //UI
    private TextView mWorkoutType, mDate, mComment, mRating;
    private RecyclerView mRecyclerView;

    //Variables
    private StrengthWorkout mStrengthWorkout;
    private String mModifiedDate;
    private ArrayList<Exercise> mExercises = new ArrayList<>();
    private ExerciseRecyclerAdapter mExerciseRecyclerAdapter;

    //Database
    private HkrHealthRepository mHkrHealthRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_strength_workout);

        try{
            mHkrHealthRepository = new HkrHealthRepository(this);
            mRecyclerView = findViewById(R.id.strengthHistoryRecyclerview);

            mWorkoutType = findViewById(R.id.workoutTypeTV);
            mDate = findViewById(R.id.workoutDateTV);
            mComment = findViewById(R.id.workoutCommentTV);
            mRating = findViewById(R.id.workoutRatingTV);

            mStrengthWorkout = getIntent().getParcelableExtra("strength_workout");
            Log.d(TAG, "onCreate: TESTING WORKOUTID: " + mStrengthWorkout.getWorkoutID());

            initializeTextViews();
            initRecyclerView();
            getAllExercises(mStrengthWorkout.getWorkoutID());


        }catch (Exception e){
            Log.d(TAG, "onCreate: error: " + e);
        }
    }
    public void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);
        mExerciseRecyclerAdapter = new ExerciseRecyclerAdapter(mExercises);
        mRecyclerView.setAdapter(mExerciseRecyclerAdapter);
    }
    public void initializeTextViews(){
        mWorkoutType.setText(mStrengthWorkout.getWorkoutType());
        mRating.setText(String.valueOf(mStrengthWorkout.getRating()));
        mComment.setText(mStrengthWorkout.getComment());

        mModifiedDate = mStrengthWorkout.getDate();
        mModifiedDate = mModifiedDate.substring(0,16);

        mDate.setText(mModifiedDate);
    }

    public void getAllExercises(int workoutID){
        try{
            mHkrHealthRepository.getAllExercisesForSpecificWorkout(workoutID).observe(this, new Observer<List<Exercise>>() {
                @Override
                public void onChanged(@Nullable List<Exercise> exercises) {
                    if (mExercises.size() > 0){
                        mExercises.clear();
                    }
                    if (exercises != null){
                        mExercises.addAll(exercises);
                    }
                    mExerciseRecyclerAdapter.notifyDataSetChanged();
                }
            });

        }catch (Exception e){
            Log.d(TAG, "getAllExercises: error: " + e);
        }


    }

}
