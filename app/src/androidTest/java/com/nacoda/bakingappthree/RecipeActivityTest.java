package com.nacoda.bakingappthree;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.nacoda.bakingappthree.StepsClasses.StepsActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecipeActivityTest {

    @Rule
    public ActivityTestRule<RecipeActivity> mActivityTestRule = new ActivityTestRule<>(RecipeActivity.class);

    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.nacoda.bakingappthree", appContext.getPackageName());
    }

    @Test
    public void checkData() {
        onView(withId(R.id.tvAppTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.tvAppTitle)).check(matches(withText("Baking")));

        onView(withRecyclerView(R.id.rvRecipes).atPosition(0))
                .check(matches(hasDescendant(withText("Nutella Pie"))))
                .check(matches(hasDescendant(withText("7 Steps"))))
                .check(matches(hasDescendant(withText("9 Ingredients Needed"))))
                .check(matches(hasDescendant(withText("8"))));

        onView(withRecyclerView(R.id.rvRecipes).atPosition(1))
                .check(matches(hasDescendant(withText("Brownies"))))
                .check(matches(hasDescendant(withText("10 Steps"))))
                .check(matches(hasDescendant(withText("10 Ingredients Needed"))))
                .check(matches(hasDescendant(withText("8"))));

        onView(withRecyclerView(R.id.rvRecipes).atPosition(2))
                .check(matches(hasDescendant(withText("Yellow Cake"))))
                .check(matches(hasDescendant(withText("13 Steps"))))
                .check(matches(hasDescendant(withText("10 Ingredients Needed"))))
                .check(matches(hasDescendant(withText("8"))));

        onView(withRecyclerView(R.id.rvRecipes).atPosition(3))
                .check(matches(hasDescendant(withText("Cheesecake"))))
                .check(matches(hasDescendant(withText("13 Steps"))))
                .check(matches(hasDescendant(withText("9 Ingredients Needed"))))
                .check(matches(hasDescendant(withText("8"))));
        onView(withId(R.id.tvAppTitle)).perform(click());
        onView(withText("This is the RecipeActivity")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        delay();
    }

    @Test
    public void recyclerClick() {
        Espresso.onView(ViewMatchers.withId(R.id.rvRecipes)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        delay();
        onView(withId(R.id.rvSteps)).check(matches(isDisplayed()));
        onView(withId(R.id.lvIngredients)).check(matches(isDisplayed()));
        onView(withText("Nutella Pie")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

        pressBack();

        Espresso.onView(ViewMatchers.withId(R.id.rvRecipes)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        delay();
        onView(withId(R.id.rvSteps)).check(matches(isDisplayed()));
        onView(withId(R.id.lvIngredients)).check(matches(isDisplayed()));
        onView(withText("Brownies")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        pressBack();

        Espresso.onView(ViewMatchers.withId(R.id.rvRecipes)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        delay();
        onView(withId(R.id.rvSteps)).check(matches(isDisplayed()));
        onView(withId(R.id.lvIngredients)).check(matches(isDisplayed()));
        onView(withText("Yellow Cake")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        pressBack();

        Espresso.onView(ViewMatchers.withId(R.id.rvRecipes)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        delay();
        onView(withId(R.id.rvSteps)).check(matches(isDisplayed()));
        onView(withId(R.id.lvIngredients)).check(matches(isDisplayed()));
        onView(withText("Cheesecake")).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        pressBack();
    }

    public void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
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
