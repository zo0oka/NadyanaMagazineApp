package com.nadyana.app.objects;

public class Category {
    private int mId;
    private String name;

    public Category(int id, String name) {
        mId = id;
        this.name = name;
    }

    public int getCategoryId() {
        return mId;
    }

    public String getCategoryName() {
        return this.name;
    }
}
