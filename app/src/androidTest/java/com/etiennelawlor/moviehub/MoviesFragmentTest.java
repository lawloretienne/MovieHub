package com.etiennelawlor.moviehub;

import android.support.test.espresso.Espresso;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.etiennelawlor.moviehub.ui.main.MainActivity;
import com.etiennelawlor.moviehub.util.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by etiennelawlor on 2/23/17.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MoviesFragmentTest {

    // region Member Variables
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
            MainActivity.class);
    // endregion

    @Before
    public void setUp() {
        // Register BackgroundWork IdlingResource
        Espresso.registerIdlingResources(EspressoIdlingResource.getIdlingResource());
    }

    // region Test Methods
    @Test
    public void onMovieClick_shouldOpenToMovieDetails() {
        // 1. (Given) Set up conditions required for the test

        // 2. (When) Then perform one or more actions
//        onView(withId(R.id.rv)).perform(RecyclerViewActions.scrollToPosition(1));
        onView(withId(R.id.rv)).perform(actionOnItemAtPosition(2, click()));

        // 3. (Then) Afterwards, verify that the state you are expecting is actually achieved
        onView(withId(R.id.backdrop_iv)).check(matches(isDisplayed()));
    }
    // endregion

    @After
    public void tearDown() {
        // Unregister BackgroundWork IdlingResource
        Espresso.unregisterIdlingResources(EspressoIdlingResource.getIdlingResource());
    }
}
