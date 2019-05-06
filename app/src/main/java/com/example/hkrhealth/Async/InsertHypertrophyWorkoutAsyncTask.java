package com.example.hkrhealth.Async;

import android.os.AsyncTask;

import com.example.hkrhealth.Database.HypertrophyWorkoutDAO;
import com.example.hkrhealth.Models.HypertrophyWorkout;

public class InsertHypertrophyWorkoutAsyncTask extends AsyncTask<HypertrophyWorkout, Void, Void> {

    private HypertrophyWorkoutDAO mHypertrophyWorkoutDAO;

    public InsertHypertrophyWorkoutAsyncTask(HypertrophyWorkoutDAO hypertrophyWorkoutDAO){
        mHypertrophyWorkoutDAO = hypertrophyWorkoutDAO;
    }

    @Override
    protected Void doInBackground(HypertrophyWorkout... hypertrophyWorkouts) {
        mHypertrophyWorkoutDAO.insertHypertrophyWorkout(hypertrophyWorkouts);
        return null;
    }
}
