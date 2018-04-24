package edu.illinois.cs.cs125.mp7;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class PastLaunches extends AppCompatActivity {
    Button b1, b2, b3, b4, b5, b6, b7, b8;
    Typeface tfc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_launches);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        b1 = (Button) findViewById(R.id.button2);
        b2 = (Button) findViewById(R.id.button3);
        b3 = (Button) findViewById(R.id.button4);
        b4 = (Button) findViewById(R.id.button5);
        b5 = (Button) findViewById(R.id.button6);
        b6 = (Button) findViewById(R.id.button7);
        b7 = (Button) findViewById(R.id.button8);
        b8 = (Button) findViewById(R.id.button9);
        tfc = Typeface.createFromAsset(getAssets(), "fonts/Prototype.ttf");
        b1.setTypeface(tfc);
        b2.setTypeface(tfc);
        b3.setTypeface(tfc);
        b4.setTypeface(tfc);
        b5.setTypeface(tfc);
        b6.setTypeface(tfc);
        b7.setTypeface(tfc);
        b8.setTypeface(tfc);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void newLaunch(View view) {
        Intent intent = new Intent(this, launch_data.class);
        startActivity(intent);
    }

}
