package com.example.hkrhealth.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.example.hkrhealth.Models.HypertrophyWorkout;

@Dao
public interface HypertrophyWorkoutDAO {

    @Insert
    void insertHypertrophyWorkout(HypertrophyWorkout... hypertrophyWorkouts);
    }


