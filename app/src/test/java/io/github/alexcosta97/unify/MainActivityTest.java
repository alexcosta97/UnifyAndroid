package io.github.alexcosta97.unify;

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
public class MainActivityTest {
    MainActivity activity;

    @Before
    public void setUp() throws Exception{
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void signInButtonLaunchesOtherActivity() throws Exception{
        //Arrange
        Class clazz = SignInActivity.class;
        Intent expectedIntent = new Intent(activity, clazz);

        //Act
        activity.signInButton.callOnClick();

        //Assert
        ShadowActivity shadowActivity= Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(expectedIntent.filterEquals(actualIntent));

    }

    @Test
    public void signUpButtonLaunchesOtherActivity() throws Exception{
        //Arrange
        Class clazz = AddCompanyActivity.class;
        Intent expectedIntent = new Intent(activity, clazz);

        //Act
        activity.signUpButton.callOnClick();

        //Assert
        ShadowActivity shadowActivity= Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(expectedIntent.filterEquals(actualIntent));

    }
}
