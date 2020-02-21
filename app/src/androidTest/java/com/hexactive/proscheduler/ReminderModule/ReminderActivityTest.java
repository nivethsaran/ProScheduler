package com.hexactive.proscheduler.ReminderModule;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import com.hexactive.proscheduler.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class ReminderActivityTest {


    public IntentsTestRule<ReminderActivity> mLoginActivityActivityTestRule =
            new IntentsTestRule<>(ReminderActivity.class);

    private String username = "cb.en.u4cse17337";
    private String password = "qwer1234";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test_reminder_activity(){
        // input username
        // Type email and password
        onView(withId(R.id.edittext_email))
                .perform(ViewActions.typeText(username), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.edittext_password))
                .perform(ViewActions.typeText(password), ViewActions.closeSoftKeyboard());

        // Click login button
        onView(withId(R.id.login_button)).perform(click());

        onView(withId(R.id.mainactivity)).check(matches(isDisplayed()));

        onView(withId(R.id.alarm_btn)).perform(click());

        onView(withId(R.id.reminderactivity)).check(matches(isDisplayed()));

        onView(withId(R.id.addReminder_Btn)).perform(click());

        onView(withId(R.id.activity_add_edit_reminder)).check(matches(isDisplayed()));

    }

    @Test
    public void test_edit_reminderactivity(){

        //not completed

        onView(withId(R.id.edittext_email))
                .perform(ViewActions.typeText(username), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.edittext_password))
                .perform(ViewActions.typeText(password), ViewActions.closeSoftKeyboard());

        // Click login button
        onView(withId(R.id.login_button)).perform(click());

        onView(withId(R.id.mainactivity)).check(matches(isDisplayed()));

        onView(withId(R.id.alarm_btn)).perform(click());

        onView(withId(R.id.reminderactivity)).check(matches(isDisplayed()));



    }

    @After
    public void tearDown() throws Exception {
    }
}