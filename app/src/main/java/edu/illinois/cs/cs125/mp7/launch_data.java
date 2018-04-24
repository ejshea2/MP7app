package edu.illinois.cs.cs125.mp7;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class launch_data extends AppCompatActivity {
    TextView launch_date_num, launch_date_text, launch_num, launch_num_text;
    Typeface tfc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        launch_num_text = (TextView) findViewById(R.id.launchNumTxt);
        launch_num = (TextView) findViewById(R.id.launchNum);
        launch_date_text = (TextView) findViewById(R.id.launchDateTxt);
        launch_date_num = (TextView) findViewById((R.id.launchDateNum));
        tfc = Typeface.createFromAsset(getAssets(), "fonts/Prototype.ttf");
        launch_date_num.setTypeface(tfc);
        launch_date_text.setTypeface(tfc);
        launch_num.setTypeface(tfc);
        launch_num_text.setTypeface(tfc);

    }

}
