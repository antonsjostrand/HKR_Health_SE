package com.example.hkrhealth.Adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hkrhealth.Models.HypertrophyWorkout;
import com.example.hkrhealth.R;

import java.util.ArrayList;

public class HypertrophyWorkoutRecyclerAdapter extends RecyclerView.Adapter<HypertrophyWorkoutRecyclerAdapter.ViewHolder>{

    private ArrayList<HypertrophyWorkout> mWorkouts;
    private OnHypertrophyWorkoutListener mOnWorkoutListener;

    public HypertrophyWorkoutRecyclerAdapter(ArrayList<HypertrophyWorkout> Workouts, OnHypertrophyWorkoutListener onWorkoutListener) {
        this.mWorkouts = Workouts;
        this.mOnWorkoutListener = onWorkoutListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.workout_history_recyclerview_layout, viewGroup, false);
        return new ViewHolder(view, mOnWorkoutListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.dateTV.setText(mWorkouts.get(i).getDate());
        viewHolder.workoutTypeTV.setText(mWorkouts.get(i).getWorkoutType());
    }

    @Override
    public int getItemCount() {
        return mWorkouts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView workoutTypeTV, dateTV;
        OnHypertrophyWorkoutListener onWorkoutListener;

        public ViewHolder(@NonNull View itemView, OnHypertrophyWorkoutListener onWorkoutListener) {
            super(itemView);
            workoutTypeTV = itemView.findViewById(R.id.workoutTypeTV);
            dateTV = itemView.findViewById(R.id.dateOfWorkoutTV);
            this.onWorkoutListener = onWorkoutListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onWorkoutListener.onWorkoutClick(getAdapterPosition());
        }
    }

    public interface OnHypertrophyWorkoutListener {
        void onWorkoutClick(int position);
    }
}
