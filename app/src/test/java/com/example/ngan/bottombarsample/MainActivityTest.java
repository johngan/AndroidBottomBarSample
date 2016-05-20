package com.example.ngan.bottombarsample;

import android.content.Intent;
import android.os.Build;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class MainActivityTest {
    // ActivityController is a Robolectric class that drives the Activity lifecycle
    private ActivityController<MainActivity> controller;
    private MainActivity activity;

    @Before
    public void setUp() {
        // Call the "buildActivity" method so we get an ActivityController which we can use
        // to have more control over the activity lifecycle
        activity = Robolectric.setupActivity(MainActivity.class);
        controller = Robolectric.buildActivity(MainActivity.class);
    }

    // Activity creation that allows intent extras to be passed in
    private void createWithIntent(String extra) {
        Intent intent = new Intent(RuntimeEnvironment.application, MainActivity.class);
        intent.putExtra("activity_extra", extra);
        activity = controller
                .withIntent(intent)
                .create()
                .start()
                .resume()
                .visible()
                .get();
    }

    // Test that simulates the full lifecycle of an activity
    @Test
    public void createsAndDestroysActivity() {
        createWithIntent("my extra_value");

        // ... add assertions ...
    }

    // @After => JUnit 4 annotation that specifies this method should be run after each test
    @After
    public void tearDown() {
        // Destroy activity after every test
        controller
                .pause()
                .stop()
                .destroy();
    }
}
