package com.nadyana.app.helpers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nadyana.app.R;
import com.nadyana.app.objects.Post;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {

    private Context mContext;
    private List<Post> posts;

    public class PostsViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;

        public PostsViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.post_title_text_view);
            thumbnail = view.findViewById(R.id.post_featured_image_view);
        }
    }


    public PostsAdapter(Context mContext, List<Post> posts) {
        this.mContext = mContext;
        this.posts = posts;
    }

    @Override
    public PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_list_item, parent, false);

        return new PostsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PostsViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.title.setText(post.getPostTitle());
        Picasso.get().load(post.getFeaturedMediaUrl()).into(holder.thumbnail);
    }


    @Override
    public int getItemCount() {
        return posts.size();
    }
}