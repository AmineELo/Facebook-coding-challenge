package com.example.amineelouattar.codingchallenge.album_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amineelouattar.codingchallenge.BaseApplication;
import com.example.amineelouattar.codingchallenge.album_list.adapter.AlbumListAdapter;
import com.example.amineelouattar.codingchallenge.album_list.component.DaggerAlbumListComponent;
import com.example.amineelouattar.codingchallenge.album_list.model.Album;
import com.example.amineelouattar.codingchallenge.album_list.model.User;
import com.example.amineelouattar.codingchallenge.album_list.module.AlbumListModule;
import com.example.amineelouattar.codingchallenge.utils.module.ContextModule;
import com.example.amineelouattar.codingchallenge.login.LoginActivity;
import com.example.amineelouattar.codingchallenge.R;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AlbumListActivity extends AppCompatActivity implements AlbumListContract.AlbumListView {

    private CallbackManager mCallBackManager;
    private ImageView profilePictureHolder;
    private TextView textArea;
    private RecyclerView albumList;
    private AlbumListAdapter adapter;
    @Inject
    AlbumListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        injectDaggerComponent();
        bindViews();
        setUpAlbumList();

        mCallBackManager = CallbackManager.Factory.create();

        presenter.getUserInfo();
        presenter.getAlbums();
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
        adapter.updateAlbumList(albumList);
    }

    @Override
    public void updateUserSection(User user) {
        textArea.setText(user.getName());
        Picasso.get()
                .load(user.getPictureUrl())
                .into(profilePictureHolder);
    }

    private void setUpAlbumList(){
        adapter = new AlbumListAdapter(new ArrayList<Album>(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        albumList.setLayoutManager(layoutManager);
        albumList.setAdapter(adapter);
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
