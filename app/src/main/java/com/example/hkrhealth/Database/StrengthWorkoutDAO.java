package com.example.hkrhealth.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.hkrhealth.Models.StrengthWorkout;

import java.util.List;

@Dao
public interface StrengthWorkoutDAO {

    @Insert
    void insertStrengthWorkout(StrengthWorkout... strengthWorkouts);

    @Query("SELECT MAX(workoutID) FROM strength_workout")
    LiveData<Integer> retrieveMaxWorkoutIDStrength();

    @Query("SELECT * FROM strength_workout")
    LiveData<List<StrengthWorkout>> getAllStrengthWorkouts();

    @Query("SELECT * FROM strength_workout WHERE workoutID = :workoutID")
    LiveData<StrengthWorkout> getStrengthWorkoutByID(int workoutID);
}
