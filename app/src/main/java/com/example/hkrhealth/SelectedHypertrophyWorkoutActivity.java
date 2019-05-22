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
import com.example.hkrhealth.Models.HypertrophyWorkout;
import com.example.hkrhealth.Util.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.List;

public class SelectedHypertrophyWorkoutActivity extends AppCompatActivity {

    private static final String TAG = "SelectedHypertrophyWork";

    //UI
    private TextView mWorkoutType, mDate, mComment, mRating;
    private RecyclerView mRecyclerView;

    //Variables
    private HypertrophyWorkout mHypertrophyWorkout;
    private String mModifiedDate;
    private ArrayList<Exercise> mExercises = new ArrayList<>();
    private ExerciseRecyclerAdapter mExerciseRecyclerAdapter;

    //Database
    private HkrHealthRepository mHkrHealthRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_hypertrophy_workout);

        try{
            mHkrHealthRepository = new HkrHealthRepository(this);
            mRecyclerView = findViewById(R.id.hypertrophyHistoryRecyclerview);

            mWorkoutType = findViewById(R.id.workoutTypeTV);
            mDate = findViewById(R.id.workoutDateTV);
            mComment = findViewById(R.id.workoutCommentTV);
            mRating = findViewById(R.id.workoutRatingTV);



            mHypertrophyWorkout = getIntent().getParcelableExtra("hypertrophy_workout");
            Log.d(TAG, "onCreate: TESTING WORKOUTID: " + mHypertrophyWorkout.getWorkoutID());


            initializeTextViews();

            initRecyclerView();
            getAllExercises(mHypertrophyWorkout.getWorkoutID());




        }catch (Exception e){
            Log.d(TAG, "onCreate: error: " + e);
        }

    }

    public void initRecyclerView(){
        try {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
            mRecyclerView.addItemDecoration(itemDecorator);
            mExerciseRecyclerAdapter = new ExerciseRecyclerAdapter(mExercises);
            mRecyclerView.setAdapter(mExerciseRecyclerAdapter);
        }catch (Exception e){
            Log.d(TAG, "initRecyclerView: error: " + e);
            e.printStackTrace();
        }
    }

    public void initializeTextViews(){
        mWorkoutType.setText(mHypertrophyWorkout.getWorkoutType());
        mRating.setText(String.valueOf(mHypertrophyWorkout.getRating()));
        mComment.setText(mHypertrophyWorkout.getComment());

        mModifiedDate = mHypertrophyWorkout.getDate();
        mModifiedDate = mModifiedDate.substring(0,16);

        mDate.setText(mModifiedDate);
    }

    public void getAllExercises(int workoutID){
        try{
            mHkrHealthRepository.getAllExercisesForSpecificHypertrophyWorkout(workoutID).observe(this, new Observer<List<Exercise>>() {
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
            e.printStackTrace();
        }


    }


}
