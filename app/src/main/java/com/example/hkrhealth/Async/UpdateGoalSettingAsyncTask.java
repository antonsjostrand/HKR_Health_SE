package com.example.hkrhealth.Async;

import android.os.AsyncTask;

import com.example.hkrhealth.Database.GoalSettingDAO;

public class UpdateGoalSettingAsyncTask extends AsyncTask<Double, Void, Void> {

    private GoalSettingDAO mGoalSettingDAO;

    public UpdateGoalSettingAsyncTask(GoalSettingDAO mGoalSettingDAO) {
        this.mGoalSettingDAO = mGoalSettingDAO;
    }

    @Override
    protected Void doInBackground(Double... doubles) {
        mGoalSettingDAO.updateGoal(doubles);
        return null;
    }
}
