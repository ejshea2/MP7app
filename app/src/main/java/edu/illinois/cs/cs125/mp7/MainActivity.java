package edu.illinois.cs.cs125.mp7;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;

// import networking modules
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    /** button stuff. */
    Button recent, past;
    Typeface tfc;

    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "MP7:main";

    /** Request queue for our API requests. */
    private static RequestQueue requestQueue;

    /** Url tags for requesting different things from the api. */
    private static String launches = "/launches";
    private static String latest = "/latest";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // onCreate stuff
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // buttons and text and shit
        recent = (Button) findViewById(R.id.get_launch);
        past = (Button) findViewById(R.id.past_launches);
        tfc = Typeface.createFromAsset(getAssets(), "fonts/Prototype.ttf");
        recent.setTypeface(tfc);
        past.setTypeface(tfc);

        // assign the request queue
        requestQueue = Volley.newRequestQueue(this);

        // opening message to log
        Log.d(TAG, "Application Opened");

        // Test to see if get launch button works. Check log after running.
        final Button getLaunch = recent;
        getLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Get latest launch button clicked");
                startAPICall(launches, latest);
            }
        });

        // Test to see if get past launches button works. Check log after running.
        Button getPastLaunches = past;
        getPastLaunches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Get past launches button clicked");
                startAPICall(launches);
            }
        });

    }

    private void startAPICall(final String ... tags) {

        // create the url tag using all the passed in tags
        StringBuilder builder = new StringBuilder();
        for (String tag: tags) { builder.append(tag); }
        String url_tag = builder.toString();

        Log.d(TAG, url_tag);

        // API GET request
        try {
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    "https://api.spacexdata.com/v2/launches" ,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(final JSONArray response) {
                                Log.d(TAG, response.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.w(TAG, error.toString());
                }
            });
            requestQueue.add(jsonArrayRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
