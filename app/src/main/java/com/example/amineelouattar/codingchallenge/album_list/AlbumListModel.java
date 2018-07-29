package com.example.amineelouattar.codingchallenge.album_list;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;

public class AlbumListModel implements AlbumListContract.AlbumListModel {
    @Override
    public void executeGraphRequest(AccessToken accessToken, String graphPath, GraphRequest.Callback callback) {
        GraphRequest request = GraphRequest.newGraphPathRequest(accessToken,
                graphPath,
                callback);
        request.executeAsync();
    }
}
