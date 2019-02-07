package com.udacity.gradle.builditbigger.free;

import com.udacity.gradle.builditbigger.R;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(JUnit4.class)
public class EndPointsAsyncTaskTest {
    @Test
    public void checkIfjokeReturnedIsNotNull() {
        onView(withId(R.id.tellJokeBt)).perform(click());

        onView(withId(R.id.jokeTxt)).check(matches(notNullValue()));
    }
}