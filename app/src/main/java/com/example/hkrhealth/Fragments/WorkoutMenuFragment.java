package com.example.hkrhealth.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
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
    private CardView mCardViewA, mCardViewB, mCardViewC, mCardViewD, mCardViewE, mCardViewF;

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

        try {
            mCardViewA = view.findViewById(R.id.card_viewA);
            mCardViewB = view.findViewById(R.id.card_viewB);
            mCardViewC = view.findViewById(R.id.card_viewC);
            mCardViewD = view.findViewById(R.id.card_viewD);
            mCardViewE = view.findViewById(R.id.card_viewE);
            mCardViewF = view.findViewById(R.id.card_viewF);

            mCardViewA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardViewAisPressed();
                    Log.d(TAG, "onClick: Cardview A: Pressed");
                }
            });

            mCardViewB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardViewBisPressed();
                    Log.d(TAG, "onClick: Cardview B: Pressed");
                }
            });

            mCardViewC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardViewCisPressed();
                    Log.d(TAG, "onClick: Cardview C: Pressed");
                }
            });

            mCardViewD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardViewDisPressed();
                    Log.d(TAG, "onClick: Cardview D: Pressed");
                }
            });

            mCardViewE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardViewEisPressed();
                    Log.d(TAG, "onClick: Cardview D: Pressed");
                }
            });

            mCardViewF.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardViewFisPressed();
                    Log.d(TAG, "onClick: Cardview D: Pressed");
                }
            });
        }catch (Exception e){
            Log.d(TAG, "onCreateView: error: " + e);
        }

        return view;
    }

    public void cardViewAisPressed() {
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

    public void cardViewBisPressed() {
        try {
            fm = getActivity().getSupportFragmentManager();
            ft = fm.beginTransaction();

            HyperBWorkoutFragment hyperBWorkoutFragment = new HyperBWorkoutFragment();
            ft.replace(R.id.fragment_container, hyperBWorkoutFragment);
            ft.commit();
        }catch (Exception e){
            Log.d(TAG, "hyperBButtonPressed: error: " + e);
        }
    }

    public void cardViewCisPressed() {
        try {
            fm = getActivity().getSupportFragmentManager();
            ft = fm.beginTransaction();

            HyperCWorkoutFragment hyperCWorkoutFragment = new HyperCWorkoutFragment();
            ft.replace(R.id.fragment_container, hyperCWorkoutFragment);
            ft.commit();
        }catch (Exception e){
            Log.d(TAG, "hyperAButtonPressed: error: " + e);
        }
    }

    public void cardViewDisPressed() {
        // Strength A
        try {
            fm = getActivity().getSupportFragmentManager();
            ft = fm.beginTransaction();

            StrengthAWorkoutFragment strengthAWorkoutFragment = new StrengthAWorkoutFragment();
            ft.replace(R.id.fragment_container, strengthAWorkoutFragment);
            ft.commit();
        }catch (Exception e){
            Log.d(TAG, "StrengthAButtonPressed: error: " + e);
        }

    }

    public void cardViewEisPressed() {
        // Strength B
        try {
            fm = getActivity().getSupportFragmentManager();
            ft = fm.beginTransaction();

            StrengthBWorkoutFragment strengthBWorkoutFragment = new StrengthBWorkoutFragment();
            ft.replace(R.id.fragment_container, strengthBWorkoutFragment);
            ft.commit();
        }catch (Exception e){
            Log.d(TAG, "StrengthAButtonPressed: error: " + e);
        }

    }

    public void cardViewFisPressed() {
        // Hiit
        try {
            fm = getActivity().getSupportFragmentManager();
            ft = fm.beginTransaction();

            HiitWorkoutFragment hiitWorkoutFragment = new HiitWorkoutFragment();
            ft.replace(R.id.fragment_container, hiitWorkoutFragment);
            ft.commit();
        }catch (Exception e){
            Log.d(TAG, "hyperAButtonPressed: error: " + e);
        }

    }
}
