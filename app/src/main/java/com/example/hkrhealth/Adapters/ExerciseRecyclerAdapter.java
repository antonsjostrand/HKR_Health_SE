package com.example.hkrhealth.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hkrhealth.Models.Exercise;
import com.example.hkrhealth.R;

import java.util.ArrayList;

public class ExerciseRecyclerAdapter extends RecyclerView.Adapter<ExerciseRecyclerAdapter.ViewHolder> {

    private static final String TAG = "ExerciseRecyclerAdapter";

    private ArrayList<Exercise> mExerciseList;

    public ExerciseRecyclerAdapter(ArrayList<Exercise> exercises){
        mExerciseList = exercises;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.selected_workout_recyclerview_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: exercise workoutid: " + mExerciseList.get(i).getWorkoutID());
        viewHolder.mExercise.setText(mExerciseList.get(i).getExerciseName());
        viewHolder.mWeight.setText(mExerciseList.get(i).getExerciseWeight()+ " kg");
        viewHolder.mSet.setText(String.valueOf("Set " + mExerciseList.get(i).getExerciseSet()));
        viewHolder.mReps.setText(mExerciseList.get(i).getExerciseReps() + " reps");
    }

    @Override
    public int getItemCount() {
        return mExerciseList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView mExercise, mSet, mWeight, mReps;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mExercise = itemView.findViewById(R.id.selectedExerciseTV);
            mSet = itemView.findViewById(R.id.selectedSetTV);
            mWeight = itemView.findViewById(R.id.selectedWeightTV);
            mReps = itemView.findViewById(R.id.selectedRepsTV);
        }
    }
}