package com.example.hkrhealth.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hkrhealth.Database.HkrHealthRepository;
import com.example.hkrhealth.R;

public class WorkoutMenuFragment extends Fragment {

    private static final String TAG = "WorkoutMenuFragment";

    //UI
    private Button mHyperAButton, mHyperBButton, mHyperCButton, mStrengthAButton, mStrengthBButton, mStrengthCButton, mHiitButton;

    //Database
    private HkrHealthRepository mHkrHealthRepository;

    //Fragment handlers
    private FragmentManager fm;
    private FragmentTransaction ft;

    //Empty constructor only used for initalization.
    public WorkoutMenuFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_menu_layout, container, false);

        mHyperAButton = view.findViewById(R.id.hyperAButton);
        mHyperBButton = view.findViewById(R.id.hyperBButton);
        mHyperCButton = view.findViewById(R.id.hyperCButton);
        mStrengthAButton = view.findViewById(R.id.strengthAButton);
        mStrengthBButton = view.findViewById(R.id.strengthBButton);
        mStrengthCButton = view.findViewById(R.id.strengthCButton);
        mHiitButton = view.findViewById(R.id.hiitButton);

        mHyperAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hyperAButtonPressed();
            }
        });

        mHyperBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hyperBButtonPressed();
            }
        });

        mHyperCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hyperCButtonPressed();
            }
        });

        mStrengthAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strengthAButtonPressed();
            }
        });

        mStrengthBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strengthBButtonPressed();
            }
        });

        mStrengthCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strengthCButtonPressed();
            }
        });

        mHiitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hiitButtonPressed();
            }
        });

        return view;
    }

    public void hyperAButtonPressed(){
        try {
            fm = getActivity().getSupportFragmentManager();
            ft = fm.beginTransaction();

            HyperAWorkoutFragment hyperAWorkoutFragment = new HyperAWorkoutFragment();
            ft.replace(R.id.fragment_container, hyperAWorkoutFragment);
            ft.commit();
        }catch (Exception e){
            Log.d(TAG, "hyperAButtonPressed: error: " + e);
        }
    }

    public void hyperBButtonPressed(){

    }

    public void hyperCButtonPressed(){

    }

    public void strengthAButtonPressed(){

    }

    public void strengthBButtonPressed(){

    }

    public void strengthCButtonPressed(){

    }

    public void hiitButtonPressed(){

    }
}
