package com.nadyana.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nadyana.app.constants.Constants;
import com.nadyana.app.helpers.SingletonVolley;
import com.nadyana.app.helpers.WebViewLoader;

import org.json.JSONException;
import org.json.JSONObject;

public class SinglePost extends AppCompatActivity {

    public static final String postId = "id";
    public static final String postTitle = "post_title";
    public static final String postLink = "post_link";
    TextView sposttitle;
    WebView webView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_categories:
                    return true;
                case R.id.navigation_pages:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_post);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        int id = getIntent().getExtras().getInt(postId);
        String title = getIntent().getExtras().getString(postTitle);


        sposttitle = findViewById(R.id.single_post_title);
        sposttitle.setText(title);

        webView = findViewById(R.id.webView);
        final WebViewLoader webViewLoader = new WebViewLoader(webView);
        webViewLoader.setWebSettings();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.single_post_content_url.replace("POST_ID", String.valueOf(id)),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject ParentObject = new JSONObject(s);
                            webViewLoader.setLoadDataWithBaseUrl(ParentObject.getJSONObject("content").getString("rendered"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        SingletonVolley.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);


    }

}
