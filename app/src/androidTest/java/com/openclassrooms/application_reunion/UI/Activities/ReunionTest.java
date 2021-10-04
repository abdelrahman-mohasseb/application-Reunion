package com.openclassrooms.application_reunion.UI.Activities;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.openclassrooms.application_reunion.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ReunionTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void reunionTest() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab_del),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list_reunions),
                                        0),
                                4),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.fab_del),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list_reunions),
                                        0),
                                4),
                        isDisplayed()));
        floatingActionButton2.perform(click());

        ViewInteraction floatingActionButton3 = onView(
                allOf(withId(R.id.buttonAdd),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton3.perform(click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.til_date1),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.til_date),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(click());

        ViewInteraction materialButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction textInputEditText2 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.til_lieu),
                                0),
                        0),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("salle 3"), closeSoftKeyboard());

        ViewInteraction textInputEditText3 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.til_sujet),
                                0),
                        0),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("developemen"), closeSoftKeyboard());

        ViewInteraction textInputEditText4 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.til_listedesparticipants),
                                0),
                        0),
                        isDisplayed()));
        textInputEditText4.perform(replaceText("test1,test2"), closeSoftKeyboard());

        ViewInteraction textInputEditText5 = onView(
                allOf(withText("test1,test2"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.til_listedesparticipants),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText5.perform(pressImeActionButton());

        ViewInteraction materialButton2 = onView(
                allOf(withId(android.R.id.button1), withText("Ajouter"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton2.perform(scrollTo(), click());
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
