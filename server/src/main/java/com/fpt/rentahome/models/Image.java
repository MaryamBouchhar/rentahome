package com.fpt.rentahome.models;

public class Image {
    private int id;
    private String url;
    private int property_id;

    public Image() {
    }
    public Image(int id, String url, int property_id) {
        this.id = id;
        this.url = url;
        this.property_id = property_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getProperty_id() {
        return property_id;
    }

    public void setProperty_id(int property_id) {
        this.property_id = property_id;
    }
}
