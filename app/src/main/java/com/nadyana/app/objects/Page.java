package com.nadyana.app.objects;

public class Page {

    private int mPageId;
    private String mPageTitle;
    private String mPageLink;

    public Page(int pageId, String pageTitle, String pageLink){
        mPageId = pageId;
        mPageTitle = pageTitle;
        mPageLink = pageLink;
    }

    public int getPageId(){
        return mPageId;
    }

    public String getPageTitle(){
        return mPageTitle;
    }

    public String getPageLink(){
        return mPageLink;
    }

    public void setPostId(int id){
        mPageId = id;
    }

    public void setPostTitle(String postTitle){
        mPageTitle = postTitle;
    }

    public void setPostLink(String link){
        mPageLink = link;
    }

    public void setFeaturedMediaUrl(String featuredMediaUrl){
        mFeaturedMediaUrl = featuredMediaUrl;
    }
}
