package com.example.amineelouattar.codingchallenge.utils.facebook_data;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;

public interface FacebookDataModelInterface {
    void executeGraphRequest(AccessToken accessToken, String graphPath, GraphRequest.Callback callback);
}
