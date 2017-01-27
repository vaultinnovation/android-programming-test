package com.vaultinnovation.androidcodetest;

import android.content.pm.ActivityInfo;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

@RunWith(AndroidJUnit4.class)
public class ActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    /**
     * Test that an item is added when the user presses the button.
     */
    @Test
    public void testAddItem() throws Exception {
        onView(withId(R.id.editText))
                .perform(typeText("White Rabbit"));
        onView(withId(R.id.button))
                .perform(click());
        onData(is(instanceOf(String.class)))
            .atPosition(0)
            .check(matches(withText("White Rabbit")));
    }

    /**
     * Test that the list items are sorted.
     */
    @Test
    public void testItemsSorted() throws Exception {
        onView(withId(R.id.editText)).perform(typeText("White Rabbit"));
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.editText)).perform(typeText("Cheshire Cat"));
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.editText)).perform(typeText("Dormouse"));
        onView(withId(R.id.button)).perform(click());
        onData(is(instanceOf(String.class)))
                .atPosition(0)
                .check(matches(withText("Cheshire Cat")));
        onData(is(instanceOf(String.class)))
                .atPosition(1)
                .check(matches(withText("Dormouse")));
        onData(is(instanceOf(String.class)))
                .atPosition(2)
                .check(matches(withText("White Rabbit")));
    }

    /**
     * Test that tapping an item advances to the next view correctly
     */
    @Test
    public void testTapItem() throws Exception {
        onView(withId(R.id.editText)).perform(typeText("Cheshire Cat"));
        onView(withId(R.id.button)).perform(click());
        onData(allOf(instanceOf(String.class), equalTo("Cheshire Cat")))
                .perform(click());
        onView(withId(R.id.message))
                .check(matches(withText("Cheshire Cat")));
    }

    /**
     * Test that the title is correct in portrait orientation.
     */
    @Test
    public void testTitleTextPortrait() throws Exception {
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        onView(withId(R.id.title))
                .check(matches(withText("The other side makes you small")));
    }



    /**
     * Test that the title is correct in landscape orientation.
     */
    @Test
    public void testTitleTextLandscape() throws Exception {
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.title))
                .check(matches(withText("One side makes you larger")));
    }
}
