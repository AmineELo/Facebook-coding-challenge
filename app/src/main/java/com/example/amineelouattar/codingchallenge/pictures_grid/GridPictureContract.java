package com.example.amineelouattar.codingchallenge.pictures_grid;

import com.facebook.GraphResponse;

public interface GridPictureContract {
    interface GridPictureView{
        void updatePictureGrid(String[] pictures);
    }
    interface GridPicturePresenter{
        void getPictures();
        void extractPictures(GraphResponse response);
    }
}
