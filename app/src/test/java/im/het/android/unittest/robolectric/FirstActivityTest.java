package im.het.android.unittest.robolectric;

import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

import im.het.android.unittest.R;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class FirstActivityTest {

    @Test
    public void goSecondActivity() {
        FirstActivity firstActivity = Robolectric.setupActivity(FirstActivity.class);
        firstActivity.findViewById(R.id.goSecond).performClick();

        Intent expectedIntent = new Intent(firstActivity, SecondActivity.class);
        Intent actualIntent = Shadows.shadowOf(firstActivity).getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actualIntent.getComponent());
    }
}