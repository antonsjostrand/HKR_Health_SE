package com.example.hkrhealth.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.util.Log;

@Entity(tableName = "hypertrophy_workout")
public class HypertrophyWorkout {

    private static final String TAG = "HypertrophyWorkout";

    @PrimaryKey(autoGenerate = true)
    private int workoutID;

    @NonNull
    @ColumnInfo(name = "date")
    private String date;

    @NonNull
    @ColumnInfo(name = "rating")
    private int rating;

    @NonNull
    @ColumnInfo(name = "comment")
    private String comment;

    @NonNull
    @ColumnInfo(name = "workout_type")
    private String workoutType;

    public HypertrophyWorkout(@NonNull String date, int rating, @NonNull String comment, @NonNull String workoutType) {
        try {
            this.date = date;
            this.rating = rating;
            this.comment = comment;
            this.workoutType = workoutType;
        }catch (Exception e){
            Log.d(TAG, "HypertrophyWorkout: Constructor error: " + e);
        }
    }

    public int getWorkoutID() {
        return workoutID;
    }

    public void setWorkoutID(int workoutID) {
        this.workoutID = workoutID;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @NonNull
    public String getComment() {
        return comment;
    }

    public void setComment(@NonNull String comment) {
        this.comment = comment;
    }

    @NonNull
    public String getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(@NonNull String workoutType) {
        this.workoutType = workoutType;
    }
}

