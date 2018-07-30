package com.example.amineelouattar.codingchallenge.pictures_grid;

import com.example.amineelouattar.codingchallenge.pictures_grid.model.Picture;
import com.facebook.GraphResponse;

import java.util.List;

public interface GridPictureContract {
    interface GridPictureView{
        void updatePictureGrid(List<Picture> pictureList);
    }
    interface GridPicturePresenter{
        void getPictures();
        void extractPictures(GraphResponse response);
    }
}
