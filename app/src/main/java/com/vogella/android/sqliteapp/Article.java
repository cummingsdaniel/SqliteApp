package com.vogella.android.sqliteapp;

public class Article {
    private String url;
    private boolean isSaved;
    private long id;

    public Article(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Article{" + "url='" + url + '\'' + ", sent=" + isSaved + '}';
    }
}
