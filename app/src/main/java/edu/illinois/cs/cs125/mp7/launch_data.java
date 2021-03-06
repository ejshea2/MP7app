package edu.illinois.cs.cs125.mp7;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class launch_data extends AppCompatActivity {

    /** Log tag. */
    private static final String TAG = "Launch Data";

    /** strings to display. */
    private String launchNumber = "";
    private final String success = "Launch Result: Success";
    private final String fail = "Launch Result: Failure";
    private final String rocket_type = "Rocket: ";
    private final String launch_site = "Launch Site: ";
    private String video_url = "";
    private String reddit_url = "";
    private String article_url = "";

    /** Views */
    TextView launchNum, launchDateNum, rocket, site, result, detail;
    ImageView patch;

    /** Request queue for our API requests. */
    private static RequestQueue requestQueue;

    /** launch code for PAI call. */
    private int launch_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // onCreate stuff
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_data);

        // View initializations
        launchNum = findViewById(R.id.launch_number);
        launchDateNum = findViewById(R.id.launch_date);
        patch = findViewById(R.id.mission_patch);
        rocket = findViewById(R.id.rocketName);
        site = findViewById(R.id.launchSite);
        result = findViewById(R.id.launchResult);
        detail = findViewById(R.id.launch_details);

        // assign the request queue
        requestQueue = Volley.newRequestQueue(this);

        // have no idea how this works but it is used to set the launch patch
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        System.out.println("LAUNCH CODE: "+launch_code);

        // call latest launch
        startAPICall(launch_code);

        // connect the buttons to the links
        Button youTube = findViewById(R.id.video);
        Button reddit_button = findViewById(R.id.reddit);
        Button article_button = findViewById(R.id.article);
        youTube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                goToURL(video_url);
            }
        });
        reddit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                goToURL(reddit_url);
            }
        });
        article_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                goToURL(article_url);
            }
        });

    }

    // get the API data for ONE launch (does not work for past launches which returns an array)
    void startAPICall(final int flightNum) {

        // API GET request
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://api.spacexdata.com/v2/launches/latest",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {

                            // print the launch data to log
                            Log.d(TAG, response.toString());

                            // parse the JSON for relevant information
                            try {

                                // relevant JSON data
                                int num = response.getInt("flight_number");
                                String date = response.getString("launch_date_utc").split("T")[0];
                                String patch_url = response.getJSONObject("links").
                                        getString("mission_patch_small");
                                String rocketName = rocket_type + response.getJSONObject("rocket").getString("rocket_name");
                                String launchSite = launch_site + response.getJSONObject("launch_site").getString("site_name_long");
                                boolean launchResult = response.getBoolean("launch_success");
                                String launchDetails = response.getString("details");
                                video_url = response.getJSONObject("links").getString("video_link");
                                reddit_url = response.getJSONObject("links").getString("reddit_campaign");
                                article_url = response.getJSONObject("links").getString("article_link");

                                // set the view objects
                                if (num < 10) {
                                    launchNumber = "0" + num;
                                } else { launchNumber = "" + num; }
                                launchNum.setText(launchNumber);
                                launchDateNum.setText(date);
                                patch.setImageBitmap(loadImage(patch_url));
                                rocket.setText(rocketName);
                                site.setText(launchSite);
                                if (launchResult) {
                                    result.setText(success);
                                } else { result.setText(fail); }
                                detail.setText(launchDetails);


                            } catch (Exception e) {
                                Log.d(TAG, "Non-existent data element(s)");
                                Log.e(TAG, e.toString());
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.w(TAG, error.toString());
                    Log.d(TAG, "Should be a JSONArray");
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "Should be a JSONArray");
        }
    }

    // method to get the mission patch png
    private Bitmap loadImage(String patch_url) {
        try {
            URL url = new URL(patch_url);
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
                Log.d(TAG, e.toString());
        } catch (IOException e) {
            Log.d(TAG, e.toString());
        }
        return null;
    }

    //Method to help go to a website
    private void goToURL(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }


}
