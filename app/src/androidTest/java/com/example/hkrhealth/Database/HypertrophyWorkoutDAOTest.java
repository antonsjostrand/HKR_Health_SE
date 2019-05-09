package com.example.hkrhealth.Database;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.hkrhealth.Models.HypertrophyWorkout;
import com.example.hkrhealth.Util.LiveDataTestUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class HypertrophyWorkoutDAOTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private HkrHealthDatabase mHkrHealthDatase;
    private HypertrophyWorkoutDAO mHypertrophyWorkoutDAO;

    @Before
    public void initDb() throws Exception{
        mHkrHealthDatase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), HkrHealthDatabase.class).allowMainThreadQueries().build();
        mHypertrophyWorkoutDAO = mHkrHealthDatase.getHypertrophyWorkoutDAO();
    }

    @After
    public void closeDb() throws Exception{
        mHkrHealthDatase.close();
    }

    @Test
    public void checkInsertAndRetrieveMaxID() throws Exception{
        int expectedID = 1;
        int actualID;
        HypertrophyWorkout hypertrophyWorkout = new HypertrophyWorkout("test", 10, "test", "A");

        for (int i = 0; i < 1; i++){
            mHypertrophyWorkoutDAO.insertHypertrophyWorkout(hypertrophyWorkout);
        }

        actualID = LiveDataTestUtil.getValue(mHypertrophyWorkoutDAO.retrieveMaxWorkoutID());

        Assert.assertEquals(expectedID, actualID);
    }

}