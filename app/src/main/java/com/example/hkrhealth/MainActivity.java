package com.example.hkrhealth;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.hkrhealth.Database.HkrHealthRepository;
import com.example.hkrhealth.Fragments.GoalsFragment;
import com.example.hkrhealth.Fragments.HyperAWorkoutFragment;
import com.example.hkrhealth.Fragments.StartUpFragment;
import com.example.hkrhealth.Fragments.StatisticsFragment;
import com.example.hkrhealth.Fragments.WorkoutHistoryFragment;
import com.example.hkrhealth.Fragments.WorkoutMenuFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    //UI
    private NavigationView mNavigationMenu;
    private DrawerLayout mDrawerLayout;
    private Button mGetStartedButton;

    //Fragment variables
    private FragmentManager fm;
    private FragmentTransaction ft;

    //Database
    private HkrHealthRepository mHkrHealthRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);




        mDrawerLayout = findViewById(R.id.main_menu_drawerlayout);
        popUpWindow();
        setNavigationMenuListener();

        ft = fm.beginTransaction();

        StartUpFragment startUpFragment = new StartUpFragment();
        ft.replace(R.id.fragment_container, startUpFragment);
        ft.commit();

        /*mGetStartedButton = findViewById(R.id.getStartedButton);

        mGetStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGetStartedButtonPressed();
                Log.d(TAG, "onClick: getStarted Button: Pressed");
            }
        });*/

        mHkrHealthRepository = new HkrHealthRepository(this);

        //adjust the keyboard so it doesn't disturb the layouts.
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    public void setNavigationMenuListener(){
        try {
            mNavigationMenu = findViewById(R.id.navigationMenu);
            mNavigationMenu.setNavigationItemSelectedListener(this);

            fm = MainActivity.this.getSupportFragmentManager();

        }catch (Exception e){
            Log.d(TAG, "setNavigationMenuListener: ERROR");
            Log.d(TAG, "setNavigationMenuListener: " + e);
        }
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    public void onGetStartedButtonPressed() {
        try {
            fm = MainActivity.this.getSupportFragmentManager();
            ft = fm.beginTransaction();

            setContentView(R.layout.activity_main);
            ft.commit();
        }catch (Exception e){
            Log.d(TAG, "getStarted button: error: " + e);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        try{
            ft = fm.beginTransaction();
            
            switch (item.getItemId()){
                case R.id.nav_workout_selection:
                    Log.d(TAG, "onNavigationItemSelected: Workout selection pressed");

                    WorkoutMenuFragment workoutMenuFragment = new WorkoutMenuFragment();
                    ft.replace(R.id.fragment_container, workoutMenuFragment);
                    ft.commit();

                    break;


                case R.id.nav_goalsetting:
                    Log.d(TAG, "onNavigationItemSelected: Goal Setting selection pressed");

                    GoalsFragment goalsFragment = new GoalsFragment();
                    ft.replace(R.id.fragment_container, goalsFragment);
                    ft.commit();
                    
                    break;

                case R.id.nav_workout_history:
                    Log.d(TAG, "onNavigationItemSelected: Workout history pressed");

                    WorkoutHistoryFragment workoutHistoryFragment = new WorkoutHistoryFragment();
                    ft.replace(R.id.fragment_container, workoutHistoryFragment);
                    ft.commit();

                    break;
                case R.id.nav_statistics:
                    Log.d(TAG, "onNavigationItemSelected: Statistics pressed");

                    StatisticsFragment statisticsFragment = new StatisticsFragment();
                    ft.replace(R.id.fragment_container, statisticsFragment);

                    ft.commit();

                    break;
            }

        }catch (Exception e){

        }
        return true;
    }

    public void popUpWindow(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Information");
        builder.setMessage("Go to Goal Settings and enter your weight");
        builder.setCancelable(true);
        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getApplicationContext(), " ", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }


}
