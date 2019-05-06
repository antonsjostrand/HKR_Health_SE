package com.example.hkrhealth.Database;

import android.content.Context;

import com.example.hkrhealth.Models.HypertrophyWorkout;

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
        mHkrHealthDatabase.getHypertrophyWorkoutDAO().insertHypertrophyWorkout(hypertrophyWorkout);
    }


}
