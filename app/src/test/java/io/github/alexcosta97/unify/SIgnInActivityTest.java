package io.github.alexcosta97.unify;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowActivity;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class SIgnInActivityTest {
    SignInActivity activity;

    @Before
    public void setUp() throws Exception{
        activity = Robolectric.setupActivity(SignInActivity.class);
    }

    @Test
    public void correctLoginLaunchesMainMenu() throws Exception{
        //Arrange
        Class clazz = MainMenu.class;
        Intent expectedIntent = new Intent(activity, clazz);
        activity.emailEditText.setText("user@testco.com");
        activity.passwordEditText.setText("Password");

        //Act
        activity.signInButton.callOnClick();

        //Assert
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(expectedIntent.filterEquals(actualIntent));
    }
}
