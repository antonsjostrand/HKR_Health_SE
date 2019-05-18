package com.example.hkrhealth.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.hkrhealth.Models.Exercise;
import com.example.hkrhealth.Models.GoalSetting;
import com.example.hkrhealth.Models.HypertrophyWorkout;
import com.example.hkrhealth.Models.StrengthWorkout;

@Database(entities = {Exercise.class, HypertrophyWorkout.class, StrengthWorkout.class, GoalSetting.class}, version = 1)
public abstract class HkrHealthDatabase extends RoomDatabase {

    private static final String TAG = "HkrHealthDatabase";

    private static final String DATABASE_NAME = "hkrhealth.db";

    private static HkrHealthDatabase instance;

    static HkrHealthDatabase getInstance(final Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), HkrHealthDatabase.class, DATABASE_NAME).build();
        }
        return instance;
    }

    public abstract HypertrophyWorkoutDAO getHypertrophyWorkoutDAO();

    public abstract StrengthWorkoutDAO getStrengthWorkoutDAO();

    public abstract ExerciseDAO getExerciseDAO();

    public abstract GoalSettingDAO getGoalSettingDAO();

}
