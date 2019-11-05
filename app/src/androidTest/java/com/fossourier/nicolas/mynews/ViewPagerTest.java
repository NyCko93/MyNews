package com.fossourier.nicolas.mynews;


import androidx.test.rule.ActivityTestRule;

import com.fossourier.nicolas.mynews.Controllers.Activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


public class ViewPagerTest {

    private MainActivity mMainActivity;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    
    @Test
    public void viewPagerTest() {
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()));
        onView(withId(R.id.tabs)).check(matches(isDisplayed()));
        onView(withText(R.string.tab_text_1)).check(matches(isDisplayed()));
        onView(withText(R.string.tab_text_2)).check(matches(isDisplayed()));

        onView(allOf(withText(R.string.tab_text_1), isDescendantOfA(withId(R.id.tabs))))
                .perform(click())
                .check(matches(isSelected()));
        swipeLeft();

        onView(allOf(withText(R.string.tab_text_2), isDescendantOfA(withId(R.id.tabs))))
                .perform(click())
                .check(matches(isSelected()));
        swipeLeft();

    }
}
