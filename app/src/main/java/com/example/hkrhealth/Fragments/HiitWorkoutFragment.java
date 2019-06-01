package com.example.hkrhealth.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hkrhealth.R;

import java.util.Locale;

public class HiitWorkoutFragment extends Fragment {

    private static final String TAG = "HiitWorkoutFragment";

    private CountDownTimer mCountDownTimer;

    //UI
    private TextView mTimerTV;
    private Button mStartButton,
            mResetButton, mYoutubeButton;

    //Variables
    private static final long startTimeInMillis = 420000;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = startTimeInMillis;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hiit_workout, container, false);

        try {
            youTubeButton(view);

            mTimerTV = view.findViewById(R.id.timerTV);
            mStartButton = view.findViewById(R.id.startCountDownButton);
            mResetButton = view.findViewById(R.id.resetButton);

            mStartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTimerRunning) {
                        pauseTimer();
                    } else {
                        startTimer();
                    }
                }
            });

            mResetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetTimer();

                }
            });

            updateCountDownText();
        }catch (Exception e){
            Log.d(TAG, "onCreateView: error: " + e);
        }
        return view;
    }

    private void startTimer() {
        try {
            mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mTimeLeftInMillis = millisUntilFinished;
                    updateCountDownText();
                }

                @Override
                public void onFinish() {

                    mTimerRunning = false;
                    mStartButton.setText("Start");
                    mStartButton.setVisibility(View.INVISIBLE);
                    mResetButton.setVisibility(View.VISIBLE);

                }
            }.start();
            mTimerRunning = true;
            mStartButton.setText("pause");
            mResetButton.setVisibility(View.INVISIBLE);
        }catch (Exception e){
            Log.d(TAG, "startTimer: error: " + e);
        }
    }

    private void pauseTimer(){
        try {
            mCountDownTimer.cancel();
            mTimerRunning = false;
            mStartButton.setText("Start");
            mResetButton.setVisibility(View.VISIBLE);
        }catch (Exception e){
            Log.d(TAG, "pauseTimer: error: " + e);
        }
    }

    private void resetTimer(){
        try {
            mTimeLeftInMillis = startTimeInMillis;
            updateCountDownText();
            mResetButton.setVisibility(View.INVISIBLE);
            mStartButton.setVisibility(View.VISIBLE);
        }catch (Exception e){
            Log.d(TAG, "resetTimer: error: " + e);
        }
    }

    private void updateCountDownText(){
        try {
            int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
            int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

            mTimerTV.setText(timeLeftFormatted);
        }catch (Exception e){
            Log.d(TAG, "updateCountDownText: error: " + e);
        }
    }

    public void youTubeButton(View view){
        try {
            mYoutubeButton = view.findViewById(R.id.goToYoutubeButton);
            mYoutubeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent webLink = new Intent(Intent.ACTION_VIEW);
                    webLink.setData(Uri.parse("https://www.youtube.com/watch?v=iLcQzW4_izg"));
                    startActivity(webLink);
                }
            });
        }catch (Exception e){
            Log.d(TAG, "youTubeButton: error: " + e);
        }
    }
}
