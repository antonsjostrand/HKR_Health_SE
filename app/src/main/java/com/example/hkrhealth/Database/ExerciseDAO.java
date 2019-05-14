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
}
