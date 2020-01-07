package com.e.learn;

import android.content.Intent;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.e.learn.firebase.DisplayToken;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(JUnit4.class)
@LargeTest
public class DisplayTokenTest {

    @Rule
    public ActivityTestRule<DisplayToken> activityTestRule = new ActivityTestRule<>(DisplayToken.class);

    @Test
    public void checkemail() {
        activityTestRule.launchActivity(new Intent());
        onView(withId(R.id.emailET)).check(matches(isDisplayed()));
    }

    @Test
    public void checkforEmailvalidation(){
        activityTestRule.launchActivity(new Intent());
    onView(withId(R.id.emailET)).check(matches(isDisplayed())).perform(click());
    onView(withText("Enter Email")).check(matches(isDisplayed()));
    }
}
