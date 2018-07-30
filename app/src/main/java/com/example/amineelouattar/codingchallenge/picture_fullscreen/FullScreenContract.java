package com.example.amineelouattar.codingchallenge.picture_fullscreen;

import com.facebook.GraphResponse;

public interface FullScreenContract {
    interface FullScreenView{
        void updateView(String pictureUrl);
    }
    interface FullScreenPresenter{
        void getPicture();
        void extractPicture(GraphResponse response);
    }
}
