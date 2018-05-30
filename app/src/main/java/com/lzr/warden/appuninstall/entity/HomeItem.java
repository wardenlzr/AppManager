package com.lzr.warden.appuninstall.entity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class HomeItem {
    private String title;
    private String imageResource;
    private int filter;

    public int getFilter() {
        return filter;
    }

    public void setFilter(int filter) {
        this.filter = filter;
    }

    public String getImageResource() {
        return imageResource;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}