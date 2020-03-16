package com.hexactive.proscheduler.LoginModule;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.hexactive.proscheduler.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest3 {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void loginActivityTest3() {
        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.edittext_email),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0)));
        textInputEditText.perform(scrollTo(), replaceText("cb.en.u4cse17337"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.edittext_password),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0)));
        textInputEditText2.perform(scrollTo(), replaceText("qwer1234"), closeSoftKeyboard());

        pressBack();

        ViewInteraction button = onView(
                allOf(withId(R.id.login_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginactivity),
                                        0),
                                6),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.login_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginactivity),
                                        0),
                                6),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.login_button), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginactivity),
                                        0),
                                6)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.calendar_btn),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.GridLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction imageButton2 = onView(
                allOf(withId(R.id.alarm_btn),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.GridLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        imageButton2.check(matches(isDisplayed()));

        ViewInteraction imageButton3 = onView(
                allOf(withId(R.id.profile_btn),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.GridLayout.class),
                                        2),
                                0),
                        isDisplayed()));
        imageButton3.check(matches(isDisplayed()));

        ViewInteraction imageButton4 = onView(
                allOf(withId(R.id.settings_btn),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.GridLayout.class),
                                        3),
                                0),
                        isDisplayed()));
        imageButton4.check(matches(isDisplayed()));

        ViewInteraction imageButton5 = onView(
                allOf(withId(R.id.settings_btn),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.GridLayout.class),
                                        3),
                                0),
                        isDisplayed()));
        imageButton5.check(matches(isDisplayed()));

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.alarm_btn),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.GridLayout")),
                                        1),
                                0)));
        appCompatImageButton.perform(scrollTo(), click());

        ViewInteraction linearLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.reminder_rv),
                                childAtPosition(
                                        withId(R.id.swipeRefreshLayout),
                                        0)),
                        0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.query_rem_btn),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.reminderactivity),
                                        1),
                                3),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withText("Sort By:"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.reminderactivity),
                                        2),
                                0),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.addReminder_Btn),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.reminderactivity),
                                        3),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.edittext_title),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0)));
        textInputEditText3.perform(scrollTo(), replaceText("Testjng"), closeSoftKeyboard());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.edittext_Note),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0)));
        textInputEditText4.perform(scrollTo(), replaceText("UI"), closeSoftKeyboard());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.edittext_date),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_add_edit_reminder),
                                        0),
                                3)));
        appCompatEditText.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.edittext_time),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_add_edit_reminder),
                                        0),
                                4)));
        appCompatEditText2.perform(scrollTo(), click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatCheckBox = onView(
                allOf(withId(R.id.notification_check), withText("Check to Enable Notification"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_add_edit_reminder),
                                        0),
                                6)));
        appCompatCheckBox.perform(scrollTo(), click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner_priority),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_add_edit_reminder),
                                        0),
                                5)));
        appCompatSpinner.perform(scrollTo(), click());

        DataInteraction textView2 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        textView2.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.submit_btn), withText("Add"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        7),
                                0)));
        appCompatButton4.perform(scrollTo(), click());

        pressBack();

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.addReminder_Btn),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.reminderactivity),
                                        3),
                                1),
                        isDisplayed()));
        floatingActionButton2.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
