package com.openclassrooms.application_reunion.utils;

import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import org.hamcrest.Matcher;

import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.allOf;

public class SetTextinTextViewAction implements ViewAction  {
    final String value;

    public SetTextinTextViewAction(String value) {
        this.value = value;
    }

    @Override
    public Matcher<View> getConstraints() {
        return allOf(isDisplayed(), isAssignableFrom(TextView.class));
    }

    @Override
    public String getDescription() {
        return "replace text";
    }

    @Override
    public void perform(UiController uiController, View view) {
        ((TextView) view).setText(value);
    }
}
