package com.example.hkrhealth.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.util.Log;

@Entity(tableName = "goal_setting")
public class GoalSetting {

    private static final String TAG = "GoalSetting";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "date")
    private String date;

    @NonNull
    @ColumnInfo(name = "weight")
    private double weight;

    //Constructor
    public GoalSetting(@NonNull String date, double weight) {
        try {
            this.date=date;
            this.weight=weight;

        }catch (Exception e){
            Log.d(TAG, "GoalSetting: Constructor error: " + e);
        }
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    public double getWeight() { return  weight; }

    public void setWeight (double weight){ this.weight = weight; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
