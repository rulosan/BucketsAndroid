package android.curso.buckets;

/**
 * Created by rulosan on 8/17/17.
 */

import android.curso.buckets.thread.Bucket;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class EspressoTestCase {

    @Rule
    public  ActivityTestRule<BucketActivity> myActivity = new ActivityTestRule<>(BucketActivity.class);

    @Test
    public void clickButtons() throws InterruptedException {
        onView(withId(R.id.button_start)).perform(click());

        int counter = 30;
        while(counter > 0)
        {
            onView(withId(R.id.send_job)).perform(click());
            counter--;
        }
        onView(withId(R.id.button_stop)).perform(click());

    }


}
