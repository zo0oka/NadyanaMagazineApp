package com.nadyana.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.nadyana.app.constants.Constants;
import com.nadyana.app.helpers.PostsAdapter;
import com.nadyana.app.helpers.SingletonVolley;
import com.nadyana.app.objects.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ListView listView = (ListView)findViewById(R.id.);


        final List<Post> recentPosts = new ArrayList<>();

        JsonArrayRequest parentArray = new JsonArrayRequest(Constants.recent_posts_url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject parentObject = response.getJSONObject(i);
                                int postId = parentObject.getInt("id");
                                String postLink = parentObject.getString("link");
                                JSONObject title = parentObject.getJSONObject("title");
                                String postTitle = title.getString("rendered");
                                JSONObject featuredMedia = parentObject.getJSONObject("better_featured_image");
                                String featuredMediaUrl = featuredMedia.getString("source_url");
                                Post post = new Post(postId, postTitle, postLink, featuredMediaUrl);
                                recentPosts.add(post);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } finally {
                            PostsAdapter postsAdapter = new PostsAdapter(getApplicationContext(), recentPosts);

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        SingletonVolley.getInstance(getApplicationContext()).addToRequestQueue(parentArray);
    }
}
