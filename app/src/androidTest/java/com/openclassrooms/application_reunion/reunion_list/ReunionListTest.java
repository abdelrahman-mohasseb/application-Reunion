package com.openclassrooms.application_reunion.reunion_list;

import android.view.View;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.openclassrooms.application_reunion.DI.DI;
import com.openclassrooms.application_reunion.R;
import com.openclassrooms.application_reunion.UI.Activities.MainActivity;
import com.openclassrooms.application_reunion.service.ReunionApiService;
import com.openclassrooms.application_reunion.utils.DeleteViewAction;
import com.openclassrooms.application_reunion.utils.SetTextinTextViewAction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.core.internal.deps.guava.base.Preconditions.checkNotNull;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static com.openclassrooms.application_reunion.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class ReunionListTest  {

        private static int ITEMS_COUNT = 2 ;

        private MainActivity mActivity;
        private ReunionApiService mApiService;

        @Rule
        public ActivityTestRule<MainActivity> mActivityRule =
                new ActivityTestRule(MainActivity.class);

        @Before
        public void setUp() {
            mActivity = mActivityRule.getActivity();
            assertThat(mActivity, notNullValue());
            mApiService = DI.getReunionApiService();
            assertThat(mApiService, notNullValue());
        }

        /**
         * We ensure that our recyclerview is displaying at least on item
         */
        @Test
        public void ReunionList_shouldNotBeEmpty() {
            // First scroll to the position that needs to be matched and click on it.
            onView(withId(R.id.list_reunions))
                    .check(matches(hasMinimumChildCount(1)));
        }

        /**
         * When we delete an item, the item is no more shown
         */
        @Test
        public void ReunionList_deleteAction_shouldRemoveItem() {
            // Given : We remove the element at position 2
            onView(withId(R.id.list_reunions)).check(withItemCount(ITEMS_COUNT));
            // When perform a click on a delete icon
            onView(withId(R.id.list_reunions))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
            onView(withId(R.id.list_reunions)).check(withItemCount(ITEMS_COUNT-1));
        }

    @Test

    public void ReunionList_addAction_shouldAddItem() {
        // Given : We remove the element at position 2
        onView(withId(R.id.list_reunions)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withId(R.id.buttonAdd))
                .perform(click());
        onView(withId(R.id.text_date)).perform(replaceText("14h00"));
        onView(withId(R.id.til_lieu1)).perform(replaceText("test"));
        onView(withId(R.id.til_sujet1)).perform(replaceText("test"));
        onView(withId(R.id.til_listedesparticipants1)).perform(replaceText("test,test"));
        onView(withText("Ajouter")).perform(click());
        onView(withId(R.id.list_reunions)).check(withItemCount(ITEMS_COUNT+1));
    }

    @Test

    public void ReunionList_filterAction_shouldFilterwithDate() {
        // Given : We remove the element at position 2
        onView(withId(R.id.list_reunions)).check(withItemCount(ITEMS_COUNT));
        onView(withContentDescription("Filtrer")).perform(click());
        onView(withId(R.id.checkbox_date)).perform(click());
        onView(withId(R.id.text_date)).perform(new SetTextinTextViewAction("14h00"));
        onView(withText("Filter la liste")).perform(click());
        onView(withId(R.id.list_reunions)).check(withItemCount(1));
    }

}

