package com.example.amineelouattar.codingchallenge.picture_fullscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.amineelouattar.codingchallenge.BaseApplication;
import com.example.amineelouattar.codingchallenge.R;
import com.example.amineelouattar.codingchallenge.login.LoginActivity;
import com.example.amineelouattar.codingchallenge.picture_fullscreen.component.DaggerFullScreenComponent;
import com.example.amineelouattar.codingchallenge.picture_fullscreen.module.FullScreenModule;
import com.example.amineelouattar.codingchallenge.utils.module.ContextModule;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class FullScreenActivity extends AppCompatActivity implements FullScreenContract.FullScreenView {

    private CallbackManager mCallBackManager;
    private ImageView imageView;
    @Inject FullScreenPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        injectDaggerFullScreenComponent();
        bindViews();

        mCallBackManager = CallbackManager.Factory.create();

        presenter.getPicture();
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
                startActivity(new Intent(FullScreenActivity.this, LoginActivity.class));
                LoginManager.getInstance().logOut();
                finish();
                return true;

            default:
                return true;
        }
    }

    @Override
    public void updateView(String pictureUrl) {
        Picasso.get()
                .load(pictureUrl)
                .placeholder(R.drawable.placeholderthumbnail)
                .error(R.drawable.androiderror)
                .into(imageView);
    }

    private void bindViews(){
        imageView = findViewById(R.id.fullscreen);
    }

    private void injectDaggerFullScreenComponent(){
        DaggerFullScreenComponent.builder()
                .appComponent(((BaseApplication)getApplicationContext()).getAppComponent())
                .contextModule(new ContextModule(this))
                .fullScreenModule(new FullScreenModule(this))
                .build()
                .inject(this);
    }
}
