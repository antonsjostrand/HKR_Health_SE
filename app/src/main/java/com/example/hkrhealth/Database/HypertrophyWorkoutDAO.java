package com.example.hkrhealth.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.hkrhealth.Models.HypertrophyWorkout;

@Dao
public interface HypertrophyWorkoutDAO {

    @Insert
    void insertHypertrophyWorkout(HypertrophyWorkout... hypertrophyWorkouts);

    @Query("SELECT MAX(workoutID) FROM hypertrophy_workout")
    LiveData<Integer> retrieveMaxWorkoutID();

    }




