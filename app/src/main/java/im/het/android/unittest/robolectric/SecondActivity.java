package im.het.android.unittest.robolectric;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import im.het.android.unittest.R;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
}
