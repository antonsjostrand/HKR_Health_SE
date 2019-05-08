package com.example.hkrhealth.Async;

import android.os.AsyncTask;

import com.example.hkrhealth.Database.ExerciseDAO;
import com.example.hkrhealth.Models.Exercise;

public class InsertExerciseAsyncTask extends AsyncTask<Exercise, Void, Void> {

    private ExerciseDAO mExerciseDao;

    public InsertExerciseAsyncTask(ExerciseDAO exerciseDAO){
        mExerciseDao = exerciseDAO;
    }

    @Override
    protected Void doInBackground(Exercise... exercises) {
        mExerciseDao.insertExercise(exercises);
        return null;
    }
}
