package com.mpm.investcalculator;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.mpm.investcalculator.R;
import com.mpm.investcalculator.modules.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void espressoTest() {
        // Set initial amount
        onView(withId(R.id.homeApplyLabelInput)).perform(replaceText("R$ 1000.00")).perform(pressImeActionButton());

        // Set maturity date
        onView(allOf(withClassName(is("android.support.v7.widget.AppCompatTextView")), withText("2018"), isDisplayed())).perform(click());

        onData(anything()).inAdapterView(withClassName(is("android.widget.YearPickerView"))).atPosition(120).perform(scrollTo(), click());

        onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(scrollTo(), click());

        // Set CDI rate
        onView(withId(R.id.homePercentageInput)).perform(replaceText("120%")).perform(closeSoftKeyboard());

        // Confirm
        onView(withId(R.id.simulateButton)).perform(click());

        // Wait 5 seconds
        waitFor(5000);

        // Check if next window is opened
        onView(withId(R.id.resultTitleLabel)).check(matches(isDisplayed()));
    }

    private void waitFor(long timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
