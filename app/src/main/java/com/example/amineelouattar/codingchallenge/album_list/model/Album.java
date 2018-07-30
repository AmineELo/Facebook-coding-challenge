package com.example.amineelouattar.codingchallenge.album_list.model;

public class Album {
    private String albumId, albumName, albumCoverUrl, albumCount;

    public Album(String albumId, String albumName, String albumCoverUrl) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.albumCoverUrl = albumCoverUrl;
    }

    public Album(String albumId, String albumName, String albumCoverUrl, String albumCount) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.albumCoverUrl = albumCoverUrl;
        this.albumCount = albumCount;
    }

    public String getAlbumId() {
        return albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getAlbumCoverUrl() {
        return albumCoverUrl;
    }

    public String getAlbumCount() {
        return albumCount;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setAlbumCoverUrl(String albumCoverUrl) {
        this.albumCoverUrl = albumCoverUrl;
    }

    public void setAlbumCount(String albumCount) {
        this.albumCount = albumCount;
    }
}
