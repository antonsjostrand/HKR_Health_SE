package com.example.hkrhealth.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hkrhealth.Models.GoalSetting;

import java.util.List;

@Dao
public interface GoalSettingDAO {

    @Insert
    void insertGoal(GoalSetting... goal);

    @Query("SELECT * FROM goal_setting")
    LiveData<List<GoalSetting>> getAllGoals();

    @Query("UPDATE goal_setting SET weight = :goalWeight WHERE id = 1")
    void updateGoal(Integer... goalWeight);

    @Query("SELECT weight FROM goal_setting WHERE id = 1")
    LiveData<Integer> getCurrentGoal();
}
