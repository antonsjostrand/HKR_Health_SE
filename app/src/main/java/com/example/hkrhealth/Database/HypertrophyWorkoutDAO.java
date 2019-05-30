package com.example.hkrhealth.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.hkrhealth.Models.HypertrophyWorkout;

import java.util.List;

@Dao
public interface HypertrophyWorkoutDAO {

    @Insert
    void insertHypertrophyWorkout(HypertrophyWorkout... hypertrophyWorkouts);

    @Query("SELECT MAX(workoutID) FROM hypertrophy_workout")
    LiveData<Integer> retrieveMaxWorkoutID();

    @Query("SELECT * FROM hypertrophy_workout")
    LiveData<List<HypertrophyWorkout>> getAllHypertrophyWorkouts();

    @Query("SELECT * FROM hypertrophy_workout WHERE workoutID = :workoutID")
    LiveData<HypertrophyWorkout> getHypertrophyWorkoutByID(int workoutID);

    @Query("SELECT * FROM hypertrophy_workout WHERE workout_type = 'Push'")
    LiveData<List<HypertrophyWorkout>> getAllHypertrophyPushWorkouts();

    @Query("SELECT * FROM hypertrophy_workout WHERE workout_type = 'Pull'")
    LiveData<List<HypertrophyWorkout>> getAllHypertrophyPullWorkouts();

    @Query("SELECT * FROM hypertrophy_workout WHERE workout_type = 'Legs'")
    LiveData<List<HypertrophyWorkout>> getAllHypertrophyLegsWorkouts();

    }




