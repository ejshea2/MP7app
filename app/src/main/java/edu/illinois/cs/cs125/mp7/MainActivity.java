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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    /** button stuff. */
    Typeface tfc;

    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "MP7:main";

    /** Launches. */
    private static int launchNums;

    /** Request queue for our API requests. */
    private static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // onCreate stuff
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // buttons initializations
        Button recent = findViewById(R.id.get_launch);
        tfc = Typeface.createFromAsset(getAssets(), "fonts/Prototype.ttf");
        recent.setTypeface(tfc);

        // assign the request queue
        requestQueue = Volley.newRequestQueue(this);

        recent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newLaunch(launchNums);
            }
        });


        // opening message to log
        Log.d(TAG, "Application Opened");
    }

    public void newLaunch(int launch_num) {
        Intent intent = new Intent(this, launch_data.class);
        Bundle bundle = new Bundle();
        bundle.putInt("launches", launch_num);
        bundle.putBoolean("latest", true);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
