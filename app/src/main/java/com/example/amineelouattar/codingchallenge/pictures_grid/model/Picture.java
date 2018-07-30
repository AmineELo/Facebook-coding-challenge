package com.example.amineelouattar.codingchallenge.pictures_grid.model;

public class Picture {
    String id, pictureUrl;

    public Picture(String id, String pictureUrl) {
        this.id = id;
        this.pictureUrl = pictureUrl;
    }

    public String getId() {
        return id;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
