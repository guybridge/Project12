package au.com.wsit.project12.ui;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import au.com.wsit.project12.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by guyb on 7/03/17.
 */

@RunWith(AndroidJUnit4.class)

public class EarthTest
{

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void openEarthActivity() throws Exception
    {
        onView(withId(R.id.earthImageView)).perform(click());

        onView(withId(R.id.map)).check(matches(notNullValue()));

    }
}
