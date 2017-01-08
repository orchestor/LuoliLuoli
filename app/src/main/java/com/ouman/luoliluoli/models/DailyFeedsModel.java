package com.ouman.luoliluoli.models;

/**
 * Created by jfg on 17-1-5.
 */

public class DailyFeedsModel {

    private String imageUrl;
    private String title;
    private String describe;

    private String author;
    private String tag;
    private String like;
    private String articleUrl;

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return imageUrl;
    }

    public String getDescribe() {
        return describe;
    }

    public String getAuthor() {
        return author;
    }

    public String getTag() {
        return tag;
    }

    public String getLike() {
        return like;
    }


    public void setImage(String image) {
        this.imageUrl = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getArticleUrl() {

        return articleUrl;
    }
}
