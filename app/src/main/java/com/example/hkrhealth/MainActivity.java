package com.example.hkrhealth;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.hkrhealth.Database.HkrHealthRepository;
import com.example.hkrhealth.Fragments.WorkoutMenuFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    //UI
    private NavigationView mNavigationMenu;
    private DrawerLayout mDrawerLayout;

    //Fragment variables
    private FragmentManager fm;
    private FragmentTransaction ft;

    //Database
    private HkrHealthRepository mHkrHealthRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHkrHealthRepository = new HkrHealthRepository(this);

        mDrawerLayout = findViewById(R.id.main_menu_drawerlayout);
        setNavigationMenuListener();


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
            }

        }catch (Exception e){

        }
        return true;
    }
}
