package com.example.hkrhealth.Async;

import android.os.AsyncTask;

import com.example.hkrhealth.Database.GoalSettingDAO;

public class UpdateGoalSettingAsyncTask extends AsyncTask<Integer, Void, Void> {

    private GoalSettingDAO mGoalSettingDAO;

    public UpdateGoalSettingAsyncTask(GoalSettingDAO mGoalSettingDAO) {
        this.mGoalSettingDAO = mGoalSettingDAO;
    }

    @Override
    protected Void doInBackground(Integer... ints) {
        mGoalSettingDAO.updateGoal(ints);
        return null;
    }
}
