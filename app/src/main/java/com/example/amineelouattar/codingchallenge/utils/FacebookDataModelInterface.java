package com.example.amineelouattar.codingchallenge.utils;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;

public interface FacebookDataModelInterface {
    void executeGraphRequest(AccessToken accessToken, String graphPath, GraphRequest.Callback callback);
}
