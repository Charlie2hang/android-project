package com.example.myapplication;


import android.view.WindowManager;

import androidx.test.espresso.Root;

import org.hamcrest.Description;

import org.hamcrest.TypeSafeMatcher;

public class ToastMatcher extends TypeSafeMatcher<Root> {

    @Override
    public void describeTo(Description description) {
        description.appendText(" sss ");

    }

    @Override
    public boolean matchesSafely(Root root) {
        int type = root.getWindowLayoutParams().get().type;
        return type == WindowManager.LayoutParams.TYPE_TOAST;

    }
}
