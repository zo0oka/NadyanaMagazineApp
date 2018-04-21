package com.nadyana.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.nadyana.app.constants.Constants;
import com.nadyana.app.helpers.SingletonVolley;
import com.nadyana.app.objects.Category;
import com.nadyana.app.objects.Post;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    public static final String postId = "id";
    public static final String postTitle = "post_title";
    public static final String postLink = "post_link";
    public ArrayList<Post> recentPosts = new ArrayList<>();
    private ListView listView;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent homeIntent = new Intent(getApplicationContext(), Home.class);
                    startActivity(homeIntent);
                    return true;
                case R.id.navigation_categories:
                    Intent categoriesIntent = new Intent(getApplicationContext(), Category.class);
                    startActivity(categoriesIntent);
                    return true;
                case R.id.navigation_pages:
                    Intent pagesIntent = new Intent(getApplicationContext(), Pages.class);
                    startActivity(pagesIntent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = findViewById(R.id.home_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        JsonArrayRequest parentArray = new JsonArrayRequest(Request.Method.GET, Constants.recent_posts_url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject currentPost = response.getJSONObject(i);
                                int id = currentPost.getInt("id");
                                String link = currentPost.getString("link");
                                JSONObject title = currentPost.getJSONObject("title");
                                String postTitle = title.getString("rendered");
                                JSONObject featuredMedia = currentPost.getJSONObject("better_featured_image");
                                String featuredMediaUrl = featuredMedia.getString("source_url");

                                Post post = new Post(id, postTitle, link, featuredMediaUrl);

                                recentPosts.add(post);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } finally {
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                            RecyclerView recyclerView = findViewById(R.id.home_rv);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setHasFixedSize(true);
                            PostsAdapter adapter = new PostsAdapter(recentPosts);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        SingletonVolley.getInstance(getApplicationContext()).addToRequestQueue(parentArray);
    }

    public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MyViewHolder> {

        public static final String postId = "id";
        public static final String postTitle = "post_title";
        public static final String postLink = "post_link";
        private ArrayList<Post> posts;
        private Context ctx;

        public PostsAdapter(ArrayList<Post> posts) {
            this.posts = posts;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.post_list_item, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            Post post = posts.get(position);
            holder.title.setText(post.getPostTitle());
            Picasso.get().load(post.getFeaturedMediaUrl()).into(holder.thumbnail);
            holder.thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), SinglePost.class);
                    intent.putExtra(postId, posts.get(position).getPostId());
                    intent.putExtra(postTitle, posts.get(position).getPostTitle());
                    intent.putExtra(postLink, posts.get(position).getPostLink());
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return posts.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title;
            public ImageView thumbnail;

            public MyViewHolder(View view) {
                super(view);
                title = view.findViewById(R.id.post_title_text_view);
                thumbnail = view.findViewById(R.id.post_featured_image_view);
            }
        }
    }

}
