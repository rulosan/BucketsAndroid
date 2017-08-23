package android.curso.buckets;

/**
 * Created by rulosan on 8/17/17.
 */


import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;



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