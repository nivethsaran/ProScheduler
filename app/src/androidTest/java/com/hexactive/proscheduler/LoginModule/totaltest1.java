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
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class totaltest1 {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void totaltest1() {
        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.edittext_email),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0)));
        // textInputEditText.perform(scrollTo(), replaceText("cb.en.u4cse17337"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.edittext_password),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0)));
      //  textInputEditText2.perform(scrollTo(), replaceText("qwer1234"), closeSoftKeyboard());

        ViewInteraction appCompatCheckBox = onView(
                allOf(withId(R.id.show_password), withText("Show Password"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                0)));
        //appCompatCheckBox.perform(scrollTo(), click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.login_button), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginactivity),
                                        0),
                                6)));
       // appCompatButton.perform(scrollTo(), click());

        ViewInteraction textView = onView(
                allOf(withText("ProScheduler"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
      //  textView.check(matches(withText("ProScheduler")));

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.calendar_btn),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.GridLayout")),
                                        0),
                                0)));
        appCompatImageButton.perform(scrollTo(), click());

        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.rb2), withText("Weekly"),
                        childAtPosition(
                                allOf(withId(R.id.rdg),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                1)));
        appCompatRadioButton.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.zoomin), withText("+"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.zoomout), withText("-"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.zoomout), withText("-"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1)));
        appCompatButton4.perform(scrollTo(), click());

        ViewInteraction textView2 = onView(
                allOf(withText("ProScheduler"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("ProScheduler")));

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.oncal), withContentDescription("Online Calendar"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withText("ProScheduler"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("ProScheduler")));

        pressBack();

        pressBack();

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.alarm_btn),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.GridLayout")),
                                        1),
                                0)));
        appCompatImageButton2.perform(scrollTo(), click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.startdate_ed),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.reminderactivity),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton5.perform(scrollTo(), click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.enddate_ed),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.reminderactivity),
                                        1),
                                2),
                        isDisplayed()));
        appCompatEditText2.perform(click());
//
//     //   ViewInteraction view = onView(
//                allOf(withContentDescription("28 March 2020"),
//                        childAtPosition(
//                                allOf(IsInstanceOf.<View>instanceOf(android.view.View.class),
//                                        childAtPosition(
//                                                IsInstanceOf.<View>instanceOf(com.android.internal.widget.ViewPager.class),
//                                                1)),
//                                27),
//                        isDisplayed()));
//        view.check(matches(isDisplayed()));

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton6.perform(scrollTo(), click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.startdate_ed), withText("2020-03-8"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.reminderactivity),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText3.perform(click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(android.R.id.button2), withText("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatButton7.perform(scrollTo(), click());

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
        textInputEditText3.perform(scrollTo(), replaceText("rdd"), closeSoftKeyboard());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.edittext_Note),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0)));
        textInputEditText4.perform(scrollTo(), replaceText("nnn"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.edittext_date),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_add_edit_reminder),
                                        0),
                                3)));
        appCompatEditText4.perform(scrollTo(), click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.edittext_date),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_add_edit_reminder),
                                        0),
                                3)));
        appCompatEditText5.perform(scrollTo(), click());

//        ViewInteraction view2;
//        view2 = onView(
//                allOf(withContentDescription("17 March 2020"),
//                        childAtPosition(
//                                allOf(IsInstanceOf.<View>instanceOf(View.class),
//                                        childAtPosition(
//                                                IsInstanceOf.<View>instanceOf(com.android.internal.widget.ViewPager.class),
//                                                1)),
//                                16),
//                        isDisplayed()));
//        view2.check(matches(isDisplayed()));

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton8.perform(scrollTo(), click());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.edittext_time),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_add_edit_reminder),
                                        0),
                                4)));
        appCompatEditText6.perform(scrollTo(), click());

        ViewInteraction radialTimePickerView$RadialPickerTouchHelper = onView(
                allOf(withContentDescription("30"),
                        childAtPosition(
                                allOf(IsInstanceOf.<View>instanceOf(android.view.View.class),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                1)),
                                6),
                        isDisplayed()));
        radialTimePickerView$RadialPickerTouchHelper.check(matches(isDisplayed()));

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton9.perform(scrollTo(), click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner_priority),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_add_edit_reminder),
                                        0),
                                5)));
        appCompatSpinner.perform(scrollTo(), click());

        DataInteraction textView4 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        textView4.perform(click());

        ViewInteraction appCompatCheckBox2 = onView(
                allOf(withId(R.id.notification_check), withText("Check to Enable Notification"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_add_edit_reminder),
                                        0),
                                6)));
        appCompatCheckBox2.perform(scrollTo(), click());

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.submit_btn), withText("Add"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        7),
                                0)));
        appCompatButton10.perform(scrollTo(), click());

        ViewInteraction button = onView(
                allOf(withId(R.id.submit_btn),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        7),
                                0),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        pressBack();

        ViewInteraction textView5 = onView(
                allOf(withText("Reminder"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.reminderactivity),
                                        0),
                                0),
                        isDisplayed()));
        textView5.check(matches(withText("Reminder")));

        pressBack();

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.profile_btn),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.GridLayout")),
                                        2),
                                0)));
        appCompatImageButton3.perform(scrollTo(), click());

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.history),
                        childAtPosition(
                                allOf(withId(R.id.profile_layout),
                                        childAtPosition(
                                                withId(R.id.layout),
                                                2)),
                                1)));
        appCompatImageView.perform(scrollTo(), click());

        pressBack();

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.designation), withText("Assosiate Professor"),
                        childAtPosition(
                                allOf(withId(R.id.profile_layout),
                                        childAtPosition(
                                                withId(R.id.layout),
                                                2)),
                                4),
                        isDisplayed()));
        textView6.check(matches(withText("Assosiate Professor")));

        pressBack();

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withId(R.id.settings_btn),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.GridLayout")),
                                        3),
                                0)));
        appCompatImageButton4.perform(scrollTo(), click());

        ViewInteraction textView7 = onView(
                allOf(withText("Change Password"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.settingsactivity),
                                        2),
                                0),
                        isDisplayed()));
        textView7.check(matches(withText("Change Password")));

        pressBack();
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
