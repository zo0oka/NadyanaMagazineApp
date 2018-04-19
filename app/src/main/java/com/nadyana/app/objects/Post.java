package com.nadyana.app.objects;

public class Post {

    private int mPostId;
    private String mPostTitle;
    private String mFeaturedMediaUrl;
    private String mPostLink;

    public Post(){

    }

    public Post(int postId, String postTitle, String postLink, String featuredMediaUrl){
        mPostId = postId;
        mPostTitle = postTitle;
        mPostLink = postLink;
        mFeaturedMediaUrl = featuredMediaUrl;
    }

    public int getPostId(){
        return mPostId;
    }

    public String getPostTitle(){
        return mPostTitle;
    }

    public String getPostLink(){
        return mPostLink;
    }

    public String getFeaturedMediaUrl(){
        return mFeaturedMediaUrl;
    }

    public void setPostId(int id){
        mPostId = id;
    }

    public void setPostTitle(String postTitle){
        mPostTitle = postTitle;
    }

    public void setPostLink(String link){
        mPostLink = link;
    }

    public void setFeaturedMediaUrl(String featuredMediaUrl){
        mFeaturedMediaUrl = featuredMediaUrl;
    }
}
