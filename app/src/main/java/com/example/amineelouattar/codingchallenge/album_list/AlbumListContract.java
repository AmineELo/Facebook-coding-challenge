package com.example.amineelouattar.codingchallenge.album_list;

import com.example.amineelouattar.codingchallenge.album_list.model.Album;
import com.example.amineelouattar.codingchallenge.album_list.model.User;
import com.facebook.GraphResponse;

import java.util.List;

public interface AlbumListContract {
    interface AlbumListPresenter{
        void getUserInfo();
        void getAlbums();
        void extractAlbums(GraphResponse response);
        void extractUserInfo(GraphResponse response);
    }

    interface AlbumListView{
        void updateAlbumList(List<Album> albumList);
        void updateUserSection(User user);
    }
}
