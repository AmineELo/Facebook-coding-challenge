package com.example.amineelouattar.codingchallenge.album_list;

import android.content.Context;
import android.util.Log;

import com.example.amineelouattar.codingchallenge.album_list.model.Album;
import com.example.amineelouattar.codingchallenge.album_list.model.User;
import com.example.amineelouattar.codingchallenge.utils.facebook_data.FacebookDataModelInterface;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AlbumListPresenter implements AlbumListContract.AlbumListPresenter {

    private AlbumListContract.AlbumListView view;
    private FacebookDataModelInterface model;
    @Inject
    Context context;

    @Inject
    public AlbumListPresenter(AlbumListContract.AlbumListView view, FacebookDataModelInterface model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void getUserInfo() {
        String graphPath = "/me?fields=about,name,picture.type(large)";

        GraphRequest.Callback responseCallback = new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse response) {
                extractUserInfo(response);
            }
        };

        model.executeGraphRequest(AccessToken.getCurrentAccessToken(), graphPath, responseCallback);
    }

    @Override
    public void getAlbums() {
        String graphPath = "/me/albums?fields=cover_photo,picture,name,count";
        GraphRequest.Callback responseCallback = new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse response) {
                extractAlbums(response);
            }
        };
        model.executeGraphRequest(AccessToken.getCurrentAccessToken(), graphPath, responseCallback);

    }

    @Override
    public void extractAlbums(GraphResponse response) {
        List<Album> albumList = new ArrayList<>();
        Log.d("FACEBOOK RESPONSE", response.toString());
        try{
            JSONObject dataResponse = response.getJSONObject();
            JSONArray data = dataResponse.getJSONArray("data");

            for(int i = 0; i < data.length(); i++){
                albumList.add(new Album(
                        data.getJSONObject(i).getString("id"),
                        data.getJSONObject(i).getString("name"),
                        data.getJSONObject(i).getJSONObject("picture").getJSONObject("data").getString("url")
                ));
            }
            view.updateAlbumList(albumList);

        }catch (JSONException e){
            e.printStackTrace();

        }
    }

    @Override
    public void extractUserInfo(GraphResponse response) {
        Log.d("FACEBOOK RESPONSE", response.toString());
        try {
            JSONObject graphObject = response.getJSONObject();
            User user = new User(
                    graphObject.getString("name"),
                    graphObject.getJSONObject("picture").getJSONObject("data").getString("url")
            );
            view.updateUserSection(user);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
