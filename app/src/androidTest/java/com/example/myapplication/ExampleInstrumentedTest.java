package com.example.myapplication;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.example.myapplication.entity.Login;


@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityScenarioRule<Login> activityScenarioRule =
            new ActivityScenarioRule<Login>(Login.class);

    @Test
    public void testEmptyInput(){
        onView(withId(R.id.loginEditText)).perform(typeText(""));
        closeSoftKeyboard();
        onView(withId(R.id.editTextTextPassword)).perform(typeText("123"));
        closeSoftKeyboard();
        onView(withId(R.id.button_Login)).perform(click());
        onView(withText("Username can not be blank!"))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }


}