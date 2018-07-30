package com.example.amineelouattar.codingchallenge.utils;

import com.example.amineelouattar.codingchallenge.album_list.AlbumListContract;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;

public class FacebookDataModel implements FacebookDataModelInterface {
    @Override
    public void executeGraphRequest(AccessToken accessToken, String graphPath, GraphRequest.Callback callback) {
        GraphRequest request = GraphRequest.newGraphPathRequest(accessToken,
                graphPath,
                callback);
        request.executeAsync();
    }
}
