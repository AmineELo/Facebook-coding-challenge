package com.example.amineelouattar.codingchallenge.picture_fullscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.amineelouattar.codingchallenge.R;
import com.example.amineelouattar.codingchallenge.login.LoginActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FullScreenActivity extends AppCompatActivity implements FullScreenContract.FullScreenView {

    private CallbackManager mCallBackManager;
    private ImageView imageView;
    private String photoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        imageView = findViewById(R.id.fullscreen);
        mCallBackManager = CallbackManager.Factory.create();
        photoId = getIntent().getStringExtra("id");

        GraphRequest request = GraphRequest.newGraphPathRequest(
                AccessToken.getCurrentAccessToken(),
                "/"+ photoId +"/?fields=images",
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        Log.d("FACEBOOK RESPONSE", response.toString());
                        try {
                            JSONObject dataResponse = response.getJSONObject();
                            JSONArray data = dataResponse.getJSONArray("images");

                            Picasso.get()
                                    .load(data.getJSONObject(0).getString("source"))
                                    .placeholder(R.drawable.placeholderthumbnail)
                                    .error(R.drawable.androiderror)
                                    .into(imageView);



                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }
        );

        request.executeAsync();
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
}
