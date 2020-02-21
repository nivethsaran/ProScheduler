package com.hexactive.proscheduler.LoginModule;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;

import com.hexactive.proscheduler.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class LoginActivityTest {

// @Rule
    //  public ActivityTestRule<LoginActivity> activityRule
    //         = new ActivityTestRule<>(LoginActivity.class);

    @Rule
    public IntentsTestRule<LoginActivity> mLoginActivityActivityTestRule =
            new IntentsTestRule<>(LoginActivity.class);

    private String username = "cb.en.u4cse17337";
   private String password = "qwer1234";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test_login_null(){
        //if this testcase fails ..then app is running properly

        onView(withId(R.id.edittext_email))
                .perform(typeText(""));
      //  close soft keyboard
        Espresso.closeSoftKeyboard();
        //input password
        onView(withId(R.id.edittext_password))
                .perform(typeText(""));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.login_button)).perform(click());
    }

    @Test
    public void test_login(){
        // input username
        // Type email and password
        Espresso.onView(ViewMatchers.withId(R.id.edittext_email))
                .perform(ViewActions.typeText(username), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.edittext_password))
                .perform(ViewActions.typeText(password), ViewActions.closeSoftKeyboard());

        // Click login button
        Espresso.onView(ViewMatchers.withId(R.id.login_button)).perform(click());

        Espresso.onView(withId(R.id.mainactivity)).check(matches(isDisplayed()));

    }
    @Test
    public void back_to_login_from_mainactivity(){
        // input username
        // Type email and password
        Espresso.onView(ViewMatchers.withId(R.id.edittext_email))
                .perform(ViewActions.typeText(username), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.edittext_password))
                .perform(ViewActions.typeText(password), ViewActions.closeSoftKeyboard());

        // Click login button
        Espresso.onView(withId(R.id.login_button)).perform(click());
        Espresso.onView(withId(R.id.mainactivity)).check(matches(isDisplayed()));
        pressBack();
        Espresso.onView(withId(R.id.loginactivity)).check(matches(isDisplayed()));

    }

    @After
    public void tearDown() throws Exception {
    }
}