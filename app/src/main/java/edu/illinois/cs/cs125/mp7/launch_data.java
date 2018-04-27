package edu.illinois.cs.cs125.mp7;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class launch_data extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "MP7:main";

    /** Request queue for our API requests. */
    private static RequestQueue requestQueue;

    TextView launch_date_num, launch_date_text, launch_num, launch_num_text;
    Typeface tfc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Set up a queue for our Volley requests
        requestQueue = Volley.newRequestQueue(this);

        // display the application frame
        setContentView(R.layout.activity_launch_data);

        // set textviews and fonts and shit
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

        Log.d(TAG, "hi");

        // call the functions
        startAPICall();
    }

    void startAPICall() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://api.spacexdata.com/v2/launches",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            try {
                                Log.d(TAG, response.toString());
                            } catch (Exception e) { }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.w(TAG, error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
