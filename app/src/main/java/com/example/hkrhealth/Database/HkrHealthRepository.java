package com.example.hkrhealth.Database;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.hkrhealth.Async.InsertExerciseAsyncTask;
import com.example.hkrhealth.Async.InsertHypertrophyWorkoutAsyncTask;
import com.example.hkrhealth.Models.Exercise;
import com.example.hkrhealth.Models.HypertrophyWorkout;

import java.util.List;

public class HkrHealthRepository {

    private HkrHealthDatabase mHkrHealthDatabase;

    public HkrHealthRepository(Context context) {
        mHkrHealthDatabase = HkrHealthDatabase.getInstance(context);
    }

    /*
       ------ WORKOUT QUERIES -----
     */

    //Insert a single Hypertrophyworkout to the database.
    public void insertHypertrophyWorkout(HypertrophyWorkout hypertrophyWorkout){
       new InsertHypertrophyWorkoutAsyncTask(mHkrHealthDatabase.getHypertrophyWorkoutDAO()).execute(hypertrophyWorkout);
    }

    //Returns the biggest hypertrophy workout id.
    public LiveData<Integer> retrieveMaxWorkoutID(){
        return mHkrHealthDatabase.getHypertrophyWorkoutDAO().retrieveMaxWorkoutID();
    }

    public LiveData<List<HypertrophyWorkout>> getAllHypertrophyWorkouts(){
        return mHkrHealthDatabase.getHypertrophyWorkoutDAO().getAllHypertrophyWorkouts();
    }

    /*
      --------- EXERCISE QUERIES -------
     */

    //Insert a single exercise into the database
    public void insertExercise(Exercise exercise){
        new InsertExerciseAsyncTask(mHkrHealthDatabase.getExerciseDAO()).execute(exercise);
    }

    //Retrieves a specific exercise, parameter: the primary key value
    public LiveData<Exercise> retrieveSpecificExercise(int exerciseID){
        return mHkrHealthDatabase.getExerciseDAO().retrieveSpecificExercise(exerciseID);
    }

    public LiveData<List<Exercise>> getAllExercisesForSpecificWorkout(int workoutID){
        return mHkrHealthDatabase.getExerciseDAO().getAllExercisesForSpecificWorkout(workoutID);
    }

}
