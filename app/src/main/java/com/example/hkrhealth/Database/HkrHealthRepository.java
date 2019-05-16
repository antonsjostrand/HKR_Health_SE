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

    public LiveData<List<Exercise>> getAllExercisesByNameAndReps(String exerciseName, int exerciseReps){
        return mHkrHealthDatabase.getExerciseDAO().getAllExercisesByNameAndReps(exerciseName, exerciseReps);
    }

    public LiveData<Double> getMaximumLiftFromExerciseByName(String exerciseName){
        return mHkrHealthDatabase.getExerciseDAO().getMaximumLiftFromExerciseByName(exerciseName);
    }

    public LiveData<Double> getTotalAmountOfWeightLiftedExerciseByName(String exerciseName){
        return mHkrHealthDatabase.getExerciseDAO().getTotalAmountOfWeightLiftedExerciseByName(exerciseName);
    }

    public LiveData<Integer> getTotalAmountOfRepsExerciseByName(String exerciseName){
        return mHkrHealthDatabase.getExerciseDAO().getTotalAmountOfRepsExerciseByName(exerciseName);
    }

    public LiveData<Double> getSmallest1RmForExerciseByName(String exerciseName){
        return mHkrHealthDatabase.getExerciseDAO().getSmallest1RmPerformedExerciseByName(exerciseName);
    }

    public LiveData<Double> getBiggest1RmForExerciseByName(String exerciseName){
        return mHkrHealthDatabase.getExerciseDAO().getBiggest1RmPerformedExerciseByName(exerciseName);
    }
}
