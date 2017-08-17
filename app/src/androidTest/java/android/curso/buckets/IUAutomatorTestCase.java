package android.curso.buckets;

/**
 * Created by rulosan on 8/17/17.
 */

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class IUAutomatorTestCase {

    private static final String BASIC_SAMPLE_PACKAGE = "android.curso.buckets";
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String STRING_TO_BE_TYPED = "UiAutomator";
    private UiDevice mDevice;

    @Before
    public void startMainActivityFromHomeScreen()
    {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        mDevice.pressHome();

        final String launcherPackage = mDevice.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);

        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);


        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT);
    }

    @Test
    public void testStartButtonClick() throws UiObjectNotFoundException {

        UiObject starUIObject = mDevice.findObject(
                (new UiSelector())
                        .className("android.widget.Button")
                        .text("start")
        );

        UiObject stopUIObject = mDevice.findObject(
                (new UiSelector())
                        .className("android.widget.Button")
                        .text("stop")
        );
        UiObject sendJobUIObject = mDevice.findObject(
                (new UiSelector())
                    .className("android.widget.Button")
                    .text("Send Job")
        );

        if(starUIObject.exists() && starUIObject.isEnabled())
        {
            starUIObject.click();
        }

        if (sendJobUIObject.exists() && sendJobUIObject.isEnabled()) {
            int count = 10;
            while(count > 0) {
                sendJobUIObject.click();
                count--;
            }
        }

        if(stopUIObject.exists() && stopUIObject.isEnabled())
        {
            stopUIObject.click();
        }
    }
}
