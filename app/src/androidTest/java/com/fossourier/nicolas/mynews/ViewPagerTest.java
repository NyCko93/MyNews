package com.fossourier.nicolas.mynews;


import androidx.test.rule.ActivityTestRule;

import com.fossourier.nicolas.mynews.Controllers.Activities.MainActivity;
import com.fossourier.nicolas.mynews.Views.PagerAdapter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Objects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.allOf;


// 4 tests of Viewpager and TabLayout
public class ViewPagerTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mMainActivity;
    private PagerAdapter mPagerAdapter;

    public ViewPagerTest(){
    }

    @Before
    public void setUp() {
        mMainActivity = mActivityTestRule.getActivity();
    }

    // Test if the viewpager display the fragments TopStories and MostPopular
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

    // Test of titles of 2 fragments fix: Top Stories and Most Popular
    @Test
    public void getTitleOfFragmentFixTest() {
        assertEquals("Top Stories", Objects.requireNonNull(mMainActivity.viewPager.getAdapter()).getPageTitle(0));
        assertEquals("Most Popular", Objects.requireNonNull(mMainActivity.viewPager.getAdapter()).getPageTitle(1));

    }

    // Test of number of page
    @Test
    public void shouldGetCountReturn3() {
        assertEquals(3, Objects.requireNonNull(mMainActivity.viewPager.getAdapter()).getCount());
    }

    // Test the getItem on position 2
    @Test
    public void getItemTest() throws Throwable {
        getItem();
    }

    private void getItem() throws Throwable {
        mActivityTestRule.runOnUiThread(() -> mMainActivity.viewPager.setCurrentItem(2));
        Thread.sleep(200);
        assertEquals(Objects.requireNonNull(mMainActivity.viewPager.getAdapter()).getPageTitle(2),
                mMainActivity.viewPager.getAdapter().getPageTitle(mMainActivity.viewPager.getCurrentItem()));
    }

}
