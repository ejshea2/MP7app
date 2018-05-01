package edu.illinois.cs.cs125.mp7;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class PastLaunches extends AppCompatActivity {

    /** Button stuff. */
    Typeface tfc;

    /** Log tag. */
    private static final String TAG = "PAST LAUNCHES: ";

    /** Intent kwargs. */
    private int numLaunches;
    private boolean latestCalled;
    private int btag;
    private Button butt = new Button(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_past_launches);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get the data from the intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        try {
            if (!bundle.isEmpty()) {
                if (bundle.containsKey("latest") && bundle.containsKey("launches")) {
                    numLaunches = bundle.getInt("launches");
                    latestCalled = bundle.getBoolean("latest");
                }
            }
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }

        newLaunch();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void newLaunch() {
        Intent intent = new Intent(this, launch_data.class);
        startActivity(intent);
    }

}
