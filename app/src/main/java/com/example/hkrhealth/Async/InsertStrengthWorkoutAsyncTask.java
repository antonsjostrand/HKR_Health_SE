package com.example.hkrhealth.Async;

import android.os.AsyncTask;

import com.example.hkrhealth.Database.StrengthWorkoutDAO;
import com.example.hkrhealth.Models.StrengthWorkout;

public class InsertStrengthWorkoutAsyncTask extends AsyncTask<StrengthWorkout, Void, Void> {

    private StrengthWorkoutDAO mStrengthWorkoutDAO;

    public InsertStrengthWorkoutAsyncTask(StrengthWorkoutDAO mStrengthWorkoutDAO) {
        this.mStrengthWorkoutDAO = mStrengthWorkoutDAO;
    }

    @Override
    protected Void doInBackground(StrengthWorkout... strengthWorkouts) {
        mStrengthWorkoutDAO.insertStrengthWorkout(strengthWorkouts);
        return null;
    }
}
