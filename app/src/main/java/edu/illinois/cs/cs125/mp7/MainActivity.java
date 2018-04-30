package edu.illinois.cs.cs125.mp7;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    /** button stuff. */
    Typeface tfc;

    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "MP7:main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // onCreate stuff
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // buttons initializations
        Button recent = findViewById(R.id.get_launch);
        Button past = findViewById(R.id.past_launches);
        tfc = Typeface.createFromAsset(getAssets(), "fonts/Prototype.ttf");
        recent.setTypeface(tfc);
        past.setTypeface(tfc);

        recent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newLaunch();
            }
        });
        past.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pastLaunch();
            }
        });

        // opening message to log
        Log.d(TAG, "Application Opened");
    }

    public void newLaunch() {
        Intent intent = new Intent(this, launch_data.class);
        startActivity(intent);
    }

    public void pastLaunch() {
        Intent intent = new Intent(this, PastLaunches.class);
        startActivity(intent);
    }
}
