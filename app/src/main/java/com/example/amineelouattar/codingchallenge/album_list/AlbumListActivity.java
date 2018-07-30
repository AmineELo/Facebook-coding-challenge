package com.example.amineelouattar.codingchallenge.album_list;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AlbumListActivity extends AppCompatActivity implements AlbumListContract.AlbumListView {

    private CallbackManager mCallBackManager;
    private RecyclerView albumList;
    private Toolbar toolbar;
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
        setTitle(user.getName());

        Picasso.get()
                .load(user.getPictureUrl())
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
                        drawable.setCornerRadius(Math.max(bitmap.getWidth(), bitmap.getHeight()) / 2.0f);
                        if(getSupportActionBar() != null){
                            getSupportActionBar().setIcon(drawable);
                            getSupportActionBar().setDisplayShowHomeEnabled(true);
                        }
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
    }

    private void setUpAlbumList(){
        adapter = new AlbumListAdapter(new ArrayList<Album>(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        albumList.setLayoutManager(layoutManager);
        albumList.setAdapter(adapter);
    }

    private void bindViews(){
        albumList = findViewById(R.id.album_list);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
