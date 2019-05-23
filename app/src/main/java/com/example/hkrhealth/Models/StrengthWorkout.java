package com.example.hkrhealth.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

@Entity(tableName = "strength_workout")
public class StrengthWorkout implements Parcelable{

    private static final String TAG = "StrengthWorkout";

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

    public StrengthWorkout(@NonNull String date, int rating, @NonNull String comment, @NonNull String workoutType) {
        try {
            this.date = date;
            this.rating = rating;
            this.comment = comment;
            this.workoutType = workoutType;
        }catch (Exception e){
            Log.d(TAG, "StrengthWorkout: Constructor error: " + e);
        }
    }
    protected StrengthWorkout(Parcel in) {
        workoutID = in.readInt();
        date = in.readString();
        rating = in.readInt();
        comment = in.readString();
        workoutType = in.readString();
    }
    public static final Creator<StrengthWorkout>
            CREATOR = new Creator<StrengthWorkout>() {
        @Override
        public StrengthWorkout createFromParcel(Parcel in) {
            return new StrengthWorkout(in);
        }

        @Override
        public StrengthWorkout[] newArray(int size) { return new StrengthWorkout[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(workoutID);
        dest.writeString(date);
        dest.writeInt(rating);
        dest.writeString(comment);
        dest.writeString(workoutType);
    }
}