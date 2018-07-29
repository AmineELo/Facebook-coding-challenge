package com.example.amineelouattar.codingchallenge;

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
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GridPictures extends AppCompatActivity {

    private CallbackManager mCallBackManager;
    private String album_id;
    private String[] album_images, album_ids;
    private GridView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_pictures);

        grid = (GridView) findViewById(R.id.grid);


        mCallBackManager = CallbackManager.Factory.create();

        album_id = getIntent().getStringExtra("id");

        GraphRequest request = GraphRequest.newGraphPathRequest(
                AccessToken.getCurrentAccessToken(),
                "/"+album_id+"/photos?fields=picture",
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        Log.d("FACEBOOK RESPONSE", response.toString());
                        try {
                            JSONObject dataResponse = response.getJSONObject();
                            JSONArray data = dataResponse.getJSONArray("data");

                            album_images = new String[data.length()];
                            album_ids = new String[data.length()];

                            for(int i = 0; i < data.length(); i++){
                                album_images[i] = data.getJSONObject(i).getString("picture");
                                album_ids[i] = data.getJSONObject(i).getString("id");

                                grid.setAdapter(new ImageGridAdapter(GridPictures.this, album_images));
                                grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                        Toast.makeText(GridPictures.this, album_ids[i] + " Clicked", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(GridPictures.this, FullScreenActivity.class);
                                        intent.putExtra("id", album_ids[i]);
                                        startActivity(intent);

                                    }
                                });

                            }

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
                startActivity(new Intent(GridPictures.this, LoginActivity.class));
                LoginManager.getInstance().logOut();
                finish();
                return true;

            default:
                return true;
        }
    }
}
