package com.example.amineelouattar.codingchallenge.album_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amineelouattar.codingchallenge.BaseApplication;
import com.example.amineelouattar.codingchallenge.album_list.component.DaggerAlbumListComponent;
import com.example.amineelouattar.codingchallenge.album_list.model.Album;
import com.example.amineelouattar.codingchallenge.album_list.model.User;
import com.example.amineelouattar.codingchallenge.album_list.module.AlbumListModule;
import com.example.amineelouattar.codingchallenge.album_list.module.ContextModule;
import com.example.amineelouattar.codingchallenge.pictures_grid.GridPictures;
import com.example.amineelouattar.codingchallenge.login.LoginActivity;
import com.example.amineelouattar.codingchallenge.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

public class AlbumListActivity extends AppCompatActivity implements AlbumListContract.AlbumListView {

    private CallbackManager mCallBackManager;
    private ImageView profilePictureHolder;
    private TextView textArea;
    private ListView albumList;
    private String[] albumTitles, albumCovers, albumId;
    @Inject
    AlbumListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        injectDaggerComponent();
        bindViews();

        mCallBackManager = CallbackManager.Factory.create();

        presenter.getUserInfo();

        GraphRequest albums = GraphRequest.newGraphPathRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/albums?fields=cover_photo,picture,name",
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {

                        Log.d("FACEBOOK RESPONSE", response.toString());
                        try{


                            JSONObject dataResponse = response.getJSONObject();
                            JSONArray data = dataResponse.getJSONArray("data");
                            albumTitles = new String[data.length()];
                            albumCovers = new String[data.length()];
                            albumId = new String[data.length()];

                            for(int i = 0; i < data.length(); i++){
                                albumTitles[i] = data.getJSONObject(i).getString("name");
                                albumId[i] = data.getJSONObject(i).getString("id");
                                albumCovers[i] = data.getJSONObject(i).getJSONObject("picture").getJSONObject("data").getString("url");
                            }

                            CustomListAdapter adapter = new CustomListAdapter(AlbumListActivity.this, albumTitles, albumCovers);
                            albumList.setAdapter(adapter);

                            albumList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Toast.makeText(AlbumListActivity.this, i + " Clicked", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(AlbumListActivity.this, GridPictures.class);
                                    intent.putExtra("id", albumId[i]);
                                    startActivity(intent);
                                }
                            });



                        }catch (JSONException e){
                            e.printStackTrace();

                        }
                    }
                }
        );

        albums.executeAsync();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout :
                startActivity(new Intent(AlbumListActivity.this, LoginActivity.class));
                LoginManager.getInstance().logOut();
                finish();
                return true;

            default:
                return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallBackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void updateAlbumList(List<Album> albumList) {
    }

    @Override
    public void updateUserSection(User user) {
        textArea.setText(user.getName());
        Picasso.get()
                .load(user.getPictureUrl())
                .into(profilePictureHolder);
    }

    private void bindViews(){
        profilePictureHolder = findViewById(R.id.profile_picture);
        albumList = findViewById(R.id.album_list);
        textArea = findViewById(R.id.textArea);
    }

    private void injectDaggerComponent(){
        DaggerAlbumListComponent.builder()
                .appComponent(((BaseApplication)getApplicationContext()).getAppComponent())
                .albumListModule(new AlbumListModule(this))
                .contextModule(new ContextModule(this))
                .build()
                .inject(this);
    }
}
