package edu.illinois.cs.cs125.mp7;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button recent, past;
    Typeface tfc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recent = (Button) findViewById(R.id.get_launch);
        past = (Button) findViewById(R.id.past_launches);
        tfc = Typeface.createFromAsset(getAssets(), "fonts/Prototype.ttf");
        recent.setTypeface(tfc);
        past.setTypeface(tfc);
    }

    public void newLaunch(View view) {
        Intent intent = new Intent(this, launch_data.class);
        startActivity(intent);
    }

    public void pastLaunch(View view) {
        Intent intent = new Intent(this, PastLaunches.class);
        startActivity(intent);
    }
}
