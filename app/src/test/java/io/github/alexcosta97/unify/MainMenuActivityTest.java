package io.github.alexcosta97.unify;


import android.content.Intent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.TestCase.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class MainMenuActivityTest {
    MainMenu activity;

    @Before
    public void setUp() throws Exception{
        activity = Robolectric.setupActivity(MainMenu.class);
    }

    @Test
    public void newOrderButtonLaunchesNewActivity() throws Exception{
        //Arrange
        Class clazz = NewOrder.class;
        Intent expectedIntent = new Intent(activity, clazz);

        //Act
        activity.newOrderCard.callOnClick();

        //Assert
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(expectedIntent.filterEquals(actualIntent));
    }

    @Test
    public void templateOrderButtonLaunchesNewActivity() throws Exception{
        //Arrange
        Class clazz = TemplateOrder.class;
        Intent expectedIntent = new Intent(activity, clazz);

        //Act
        activity.templateOrderCard.callOnClick();

        //Assert
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(expectedIntent.filterEquals(actualIntent));
    }

    @Test
    public void reportsButtonLaunchesNewActivity() throws Exception{
        //Arrange
        Class clazz = Reports.class;
        Intent expectedIntent = new Intent(activity, clazz);

        //Act
        activity.reportsCard.callOnClick();

        //Assert
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(expectedIntent.filterEquals(actualIntent));
    }
}
