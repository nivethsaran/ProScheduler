package com.hexactive.proscheduler.MainModule;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import com.hexactive.proscheduler.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MainActivityTest {

    private String username = "cb.en.u4cse17337";
    private String password = "qwer1234";
    // @Rule
    // public ActivityTestRule<MainActivity> activityRule
    //        = new ActivityTestRule<>(MainActivity.class);
    public IntentsTestRule<MainActivity> mLoginActivityActivityTestRule =
            new IntentsTestRule<>(MainActivity.class);
    @Before
    public void setUp() throws Exception {
    }
    @Test
    public void test_calendar_activity(){
        Espresso.onView(withId(R.id.edittext_email))
                .perform(ViewActions.typeText(username), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.edittext_password))
                .perform(ViewActions.typeText(password), ViewActions.closeSoftKeyboard());

        // Click login button
        Espresso.onView(withId(R.id.login_button)).perform(ViewActions.click());
        //check main activity
        Espresso.onView(withId(R.id.mainactivity)).check(matches(isDisplayed()));

        Espresso.onView(withId(R.id.calendar_btn)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.calendaractivity)).check(matches(isDisplayed()));

    }


    @Test
    public void test_reminder_activity(){
        Espresso.onView(withId(R.id.edittext_email))
                .perform(ViewActions.typeText(username), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.edittext_password))
                .perform(ViewActions.typeText(password), ViewActions.closeSoftKeyboard());

        // Click login button
        Espresso.onView(withId(R.id.login_button)).perform(ViewActions.click());
        //check main activity
        Espresso.onView(withId(R.id.mainactivity)).check(matches(isDisplayed()));

        Espresso.onView(withId(R.id.alarm_btn)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.reminderactivity)).check(matches(isDisplayed()));

    }



    @Test
    public void test_settings_activity(){
        Espresso.onView(withId(R.id.edittext_email))
                .perform(ViewActions.typeText(username), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.edittext_password))
                .perform(ViewActions.typeText(password), ViewActions.closeSoftKeyboard());

        // Click login button
        Espresso.onView(withId(R.id.login_button)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.mainactivity)).check(matches(isDisplayed()));

        Espresso.onView(withId(R.id.settings_btn)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.settingsactivity)).check(matches(isDisplayed()));
    }
    @Test
    public void test_profile_activity(){
        Espresso.onView(withId(R.id.edittext_email))
                .perform(ViewActions.typeText(username), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.edittext_password))
                .perform(ViewActions.typeText(password), ViewActions.closeSoftKeyboard());

        // Click login button
        Espresso.onView(withId(R.id.login_button)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.mainactivity)).check(matches(isDisplayed()));

        Espresso.onView(withId(R.id.profile_btn)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.profileactivity)).check(matches(isDisplayed()));
    }
    @After
    public void tearDown() throws Exception {
    }
}