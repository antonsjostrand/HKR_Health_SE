package com.example.hkrhealth.Database;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.hkrhealth.Async.InsertExerciseAsyncTask;
import com.example.hkrhealth.Async.InsertGoalSettingAsyncTask;
import com.example.hkrhealth.Async.InsertHypertrophyWorkoutAsyncTask;
import com.example.hkrhealth.Async.InsertStrengthWorkoutAsyncTask;
import com.example.hkrhealth.Async.UpdateGoalSettingAsyncTask;
import com.example.hkrhealth.Models.Exercise;
import com.example.hkrhealth.Models.GoalSetting;
import com.example.hkrhealth.Models.HypertrophyWorkout;
import com.example.hkrhealth.Models.StrengthWorkout;

import java.util.List;

public class HkrHealthRepository {

    private HkrHealthDatabase mHkrHealthDatabase;

    public HkrHealthRepository(Context context) {
        mHkrHealthDatabase = HkrHealthDatabase.getInstance(context);
    }

    /*
       ------ HYPERTROPHY WORKOUT QUERIES -----
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

    public LiveData<HypertrophyWorkout> getHypertrophyWorkoutByID(int workoutID){
        return mHkrHealthDatabase.getHypertrophyWorkoutDAO().getHypertrophyWorkoutByID(workoutID);
    }

    public LiveData<List<HypertrophyWorkout>> getAllHypertrophyPushWorkouts(){
        return mHkrHealthDatabase.getHypertrophyWorkoutDAO().getAllHypertrophyPushWorkouts();
    }

    public LiveData<List<HypertrophyWorkout>> getAllHypertrophyPullWorkouts(){
        return mHkrHealthDatabase.getHypertrophyWorkoutDAO().getAllHypertrophyPullWorkouts();
    }

    public LiveData<List<HypertrophyWorkout>> getAllHypertrophyLegsWorkouts(){
        return mHkrHealthDatabase.getHypertrophyWorkoutDAO().getAllHypertrophyLegsWorkouts();
    }

    /*
      ----------- STRENGTH WORKOUT QUERIES ---------
     */

    //Insert a single Hypertrophyworkout to the database.
    public void insertStrengthWorkout(StrengthWorkout strengthWorkout){
       new InsertStrengthWorkoutAsyncTask(mHkrHealthDatabase.getStrengthWorkoutDAO()).execute(strengthWorkout);
    }

    //Returns the biggest hypertrophy workout id.
    public LiveData<Integer> retrieveMaxWorkoutIDStrength(){
        return mHkrHealthDatabase.getStrengthWorkoutDAO().retrieveMaxWorkoutIDStrength();
    }

    public LiveData<List<StrengthWorkout>> getAllStrengthWorkouts(){
        return mHkrHealthDatabase.getStrengthWorkoutDAO().getAllStrengthWorkouts();
    }

    public LiveData<StrengthWorkout> getStrengthWorkoutByID(int workoutID){
        return mHkrHealthDatabase.getStrengthWorkoutDAO().getStrengthWorkoutByID(workoutID);
    }

    public LiveData<List<StrengthWorkout>> getAllStrengthWorkoutsTypeA(){
        return mHkrHealthDatabase.getStrengthWorkoutDAO().getAllStrengthWorkoutsTypeA();
    }

    public LiveData<List<StrengthWorkout>> getAllStrengthWorkoutsTypeB(){
        return mHkrHealthDatabase.getStrengthWorkoutDAO().getAllStrengthWorkoutsTypeB();
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

    public LiveData<List<Exercise>> getAllExercisesForSpecificStrengthWorkout(int workoutID){
        return mHkrHealthDatabase.getExerciseDAO().getAllExercisesForSpecificStrengthWorkout(workoutID);
    }

    public LiveData<List<Exercise>> getAllExercisesForSpecificHypertrophyWorkout(int workoutID){
        return mHkrHealthDatabase.getExerciseDAO().getAllExercisesForSpecificHypertrophyWorkout(workoutID);
    }

    public LiveData<Integer> getTotalWeightForPushHypertrophyWorkout(){
        return mHkrHealthDatabase.getExerciseDAO().getTotalWeightForPushHypertrophyWorkout();
    }

    public LiveData<Integer> getTotalWeightForPullHypertrophyWorkout(){
        return mHkrHealthDatabase.getExerciseDAO().getTotalWeightForPullHypertrophyWorkout();
    }

    public LiveData<Integer> getTotalWeightForLegsHypertrophyWorkout(){
        return mHkrHealthDatabase.getExerciseDAO().getTotalWeightForLegsHypertrophyWorkout();
    }

    public LiveData<Integer> getTotalWeightForSpecificHypertrophyWorkout(int workoutID){
        return mHkrHealthDatabase.getExerciseDAO().getTotalWeightForSpecificHypertrophyWorkout(workoutID);
    }

    public LiveData<Integer> getTotalWeightForSpecificStrengthWorkout(int workoutID){
        return mHkrHealthDatabase.getExerciseDAO().getTotalWeightForSpecificStrengthWorkout(workoutID);
    }

    public LiveData<Integer> getTotalWeightForStrengthWorkoutTypeA(){
        return mHkrHealthDatabase.getExerciseDAO().getTotalWeightForStrengthWorkoutTypeA();
    }

    public LiveData<Integer> getTotalWeightForStrengthWorkoutTypeB(){
        return mHkrHealthDatabase.getExerciseDAO().getTotalWeightForStrengthWorkoutTypeB();
    }

      /*
      --------- GOAL QUERIES -------
     */

      public void insertGoal(GoalSetting goal){
          new InsertGoalSettingAsyncTask(mHkrHealthDatabase.getGoalSettingDAO()).execute(goal);
      }

      public LiveData<List<GoalSetting>> getAllGoals(){
          return mHkrHealthDatabase.getGoalSettingDAO().getAllGoals();
      }

      public void updateGoal(Double weight){
          new UpdateGoalSettingAsyncTask(mHkrHealthDatabase.getGoalSettingDAO()).execute(weight);
      }

      public LiveData<Double> getCurrentGoal(){
          return mHkrHealthDatabase.getGoalSettingDAO().getCurrentGoal();
      }

      public LiveData<Integer> getMaxIdGoal(){
          return mHkrHealthDatabase.getGoalSettingDAO().retrieveMaxIdGoalSetting();
      }

      public LiveData<GoalSetting> getLatestGoalSetting(int id){
          return mHkrHealthDatabase.getGoalSettingDAO().getLatestGoalSetting(id);
      }

}
