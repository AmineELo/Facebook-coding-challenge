package com.example.amineelouattar.codingchallenge.pictures_grid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.amineelouattar.codingchallenge.BaseApplication;
import com.example.amineelouattar.codingchallenge.R;
import com.example.amineelouattar.codingchallenge.login.LoginActivity;
import com.example.amineelouattar.codingchallenge.picture_fullscreen.FullScreenActivity;
import com.example.amineelouattar.codingchallenge.pictures_grid.adapter.ImageGridAdapter;
import com.example.amineelouattar.codingchallenge.pictures_grid.component.DaggerGridPicturesComponent;
import com.example.amineelouattar.codingchallenge.pictures_grid.model.Picture;
import com.example.amineelouattar.codingchallenge.pictures_grid.module.GridPicturesModule;
import com.example.amineelouattar.codingchallenge.utils.module.ContextModule;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class GridPicturesActivity extends AppCompatActivity implements GridPictureContract.GridPictureView {

    private CallbackManager mCallBackManager;
    private GridView grid;
    private ImageGridAdapter adapter;
    @Inject GridPicturesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_pictures);

        injectDaggerGridPicturesComponent();
        setUpPictureGrid();
        presenter.getPictures();
        mCallBackManager = CallbackManager.Factory.create();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallBackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout :
                startActivity(new Intent(GridPicturesActivity.this, LoginActivity.class));
                LoginManager.getInstance().logOut();
                finish();
                return true;

            default:
                return true;
        }
    }

    @Override
    public void updatePictureGrid(List<Picture> pictureList) {
        adapter.updatePictures(pictureList);
    }

    private void injectDaggerGridPicturesComponent(){
        DaggerGridPicturesComponent.builder()
                .appComponent(((BaseApplication)getApplicationContext()).getAppComponent())
                .contextModule(new ContextModule(this))
                .gridPicturesModule(new GridPicturesModule(this))
                .build()
                .inject(this);
    }

    private void setUpPictureGrid(){
        grid = findViewById(R.id.grid);
        adapter = new ImageGridAdapter(this, new ArrayList<Picture>());
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(GridPicturesActivity.this, FullScreenActivity.class);
                intent.putExtra("id", String.valueOf(l));
                startActivity(intent);
            }
        });
    }
}
