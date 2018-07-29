package com.example.amineelouattar.codingchallenge.album_list;

import com.example.amineelouattar.codingchallenge.album_list.model.Album;

import java.util.List;

public interface AlbumListContract {
    interface AlbumListPresenter{

    }

    interface AlbumListView{
        void updateAlbumList(List<Album> albumList);
        void updateUserSection();
    }
}
