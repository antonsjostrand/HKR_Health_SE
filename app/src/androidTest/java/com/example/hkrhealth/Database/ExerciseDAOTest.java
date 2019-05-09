package com.example.hkrhealth.Database;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.hkrhealth.Models.Exercise;
import com.example.hkrhealth.Util.LiveDataTestUtil;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ExerciseDAOTest {


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private HkrHealthDatabase mHkrHealthDatase;
    private ExerciseDAO mExerciseDAO;

    @Before
    public void initDb() throws Exception{
        mHkrHealthDatase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), HkrHealthDatabase.class).allowMainThreadQueries().build();
        mExerciseDAO = mHkrHealthDatase.getExerciseDAO();
    }

    @After
    public void closeDb() throws Exception {
        mHkrHealthDatase.close();
    }

    @Test
    public void checkInsertExerciseAndRetriveSpecificExercise() throws Exception{
        String exerciseName = "Bench press";
        Exercise exercise = new Exercise(1,exerciseName,10,10,1);
        mExerciseDAO.insertExercise(exercise);

        Exercise retrievedExercise = LiveDataTestUtil.getValue(mExerciseDAO.retrieveSpecificExercise(1));
        String actualName = retrievedExercise.getExerciseName();

        Assert.assertEquals(exerciseName, actualName);
    }

}