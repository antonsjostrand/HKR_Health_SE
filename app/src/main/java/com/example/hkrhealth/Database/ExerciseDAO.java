package com.example.hkrhealth.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.hkrhealth.Models.Exercise;

import java.util.List;

@Dao
public interface ExerciseDAO {

    @Insert
    void insertExercise(Exercise... exercises);

    @Query("SELECT * FROM exercises WHERE id = :exerciseID")
    LiveData<Exercise> retrieveSpecificExercise(int exerciseID);

    @Query("SELECT * FROM exercises WHERE workoutID = :workoutID")
    LiveData<List<Exercise>> getAllExercisesForSpecificWorkout(int workoutID);

    @Query("SELECT * FROM exercises WHERE workoutID = :workoutID AND (workoutType = 'Push' OR workoutType = 'Pull' OR workoutType = 'Legs')")
    LiveData<List<Exercise>> getAllExercisesForSpecificHypertrophyWorkout(int workoutID);

    @Query("SELECT * FROM exercises WHERE workoutID = :workoutID AND (workoutType = 'A' OR workoutType = 'B')")
    LiveData<List<Exercise>> getAllExercisesForSpecificStrengthWorkout(int workoutID);

    @Query("SELECT * FROM exercises WHERE exerciseName = :exerciseName AND exerciseReps = :exerciseReps")
    LiveData<List<Exercise>> getAllExercisesByNameAndReps(String exerciseName, int exerciseReps);

    @Query("SELECT MAX(exerciseWeight) FROM exercises WHERE exerciseName = :exerciseName")
    LiveData<Double> getMaximumLiftFromExerciseByName(String exerciseName);

    @Query("SELECT SUM(exerciseWeight) FROM exercises WHERE exerciseName = :exerciseName")
    LiveData<Double> getTotalAmountOfWeightLiftedExerciseByName(String exerciseName);

    @Query("SELECT SUM(exerciseReps) FROM exercises WHERE exerciseName = :exerciseName")
    LiveData<Integer> getTotalAmountOfRepsExerciseByName(String exerciseName);

    @Query("SELECT MIN(exerciseWeight) FROM exercises WHERE exerciseReps =  1 AND exerciseName = :exerciseName")
    LiveData<Double> getSmallest1RmPerformedExerciseByName(String exerciseName);

    @Query("SELECT MAX(exerciseWeight) FROM exercises WHERE exerciseReps =  1 AND exerciseName = :exerciseName")
    LiveData<Double> getBiggest1RmPerformedExerciseByName(String exerciseName);

    @Query("SELECT SUM(exerciseWeight) FROM exercises WHERE workoutType = 'Push'")
    LiveData<Integer> getTotalWeightForPushHypertrophyWorkout();

    @Query("SELECT SUM(exerciseWeight) FROM exercises WHERE workoutType = 'Pull'")
    LiveData<Integer> getTotalWeightForPullHypertrophyWorkout();

    @Query("SELECT SUM(exerciseWeight) FROM exercises WHERE workoutType = 'Legs'")
    LiveData<Integer> getTotalWeightForLegsHypertrophyWorkout();

    @Query("SELECT SUM(exerciseWeight) FROM exercises WHERE workoutID = :workoutID AND (workoutType = 'Push' OR workoutType = 'Pull' OR workoutType = 'Legs')")
    LiveData<Integer> getTotalWeightForSpecificHypertrophyWorkout(int workoutID);

    @Query("SELECT SUM(exerciseWeight) FROM exercises WHERE workoutID = :workoutID AND (workoutType = 'A' OR workoutType = 'B')")
    LiveData<Integer> getTotalWeightForSpecificStrengthWorkout(int workoutID);

    @Query("SELECT SUM(exerciseWeight) FROM exercises WHERE workoutType = 'A'")
    LiveData<Integer> getTotalWeightForStrengthWorkoutTypeA();

    @Query("SELECT SUM(exerciseWeight) FROM exercises WHERE workoutType = 'B'")
    LiveData<Integer> getTotalWeightForStrengthWorkoutTypeB();
}
