package com.example.hkrhealth.Async;

import android.os.AsyncTask;

import com.example.hkrhealth.Database.GoalSettingDAO;
import com.example.hkrhealth.Models.GoalSetting;

public class InsertGoalSettingAsyncTask extends AsyncTask<GoalSetting, Void, Void> {

    private GoalSettingDAO mGoalSettingDAO;

    public InsertGoalSettingAsyncTask(GoalSettingDAO goalSettingDAO){
        mGoalSettingDAO = goalSettingDAO;
    }

    @Override
    protected Void doInBackground(GoalSetting... goalSettings) {
        mGoalSettingDAO.insertGoal(goalSettings);
        return null;
    }
}
