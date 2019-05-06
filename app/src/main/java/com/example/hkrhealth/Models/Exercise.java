package com.example.hkrhealth.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.util.Log;

@Entity(tableName = "exercises")
public class Exercise {

    private static final String TAG = "Exercise";

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "workoutID")
    private int workoutID;

    @NonNull
    @ColumnInfo(name = "exerciseName")
    private String exerciseName;

    @NonNull
    @ColumnInfo(name = "exerciseReps")
    private int exerciseReps;

    @NonNull
    @ColumnInfo(name = "exerciseWeight")
    private double exerciseWeight;

    public Exercise(int id, int workoutID, @NonNull String exerciseName, int exerciseReps, double exerciseWeight) {
        try {
            this.workoutID = workoutID;
            this.exerciseName = exerciseName;
            this.exerciseReps = exerciseReps;
            this.exerciseWeight = exerciseWeight;
        } catch (Exception e) {
            Log.d(TAG, "Exercise: error: " + e);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkoutID() {
        return workoutID;
    }

    public void setWorkoutID(int workoutID) {
        this.workoutID = workoutID;
    }

    @NonNull
    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(@NonNull String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getExerciseReps() {
        return exerciseReps;
    }

    public void setExerciseReps(int exerciseReps) {
        this.exerciseReps = exerciseReps;
    }

    public double getExerciseWeight() {
        return exerciseWeight;
    }

    public void setExerciseWeight(double exerciseWeight) {
        this.exerciseWeight = exerciseWeight;
    }
}
