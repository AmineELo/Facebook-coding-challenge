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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {

    private CallbackManager mCallBackManager;
    private ImageView profilpic_holder;
    private TextView textArea;
    private ListView album_list;
    private String[] album_titles, album_covers, album_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCallBackManager = CallbackManager.Factory.create();
        profilpic_holder = (ImageView) findViewById(R.id.profile_picture);
        album_list = (ListView) findViewById(R.id.album_list);
        textArea = (TextView) findViewById(R.id.textArea);

        GraphRequest request = GraphRequest.newGraphPathRequest(
                AccessToken.getCurrentAccessToken(),
                "/me?fields=about,name,picture",
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        Log.d("FACEBOOK RESPONSE", response.toString());
                        try {
                            JSONObject graphObject = response.getJSONObject();

                            textArea.setText(graphObject.getString("name"));
                            Log.d("FACEBOOK RESPONSE", graphObject.getJSONObject("picture").getJSONObject("data").getString("url"));
                            Picasso.get()
                                    .load(graphObject.getJSONObject("picture").getJSONObject("data").getString("url"))
                                    .into(profilpic_holder);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
        );

        request.executeAsync();

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
                            album_titles = new String[data.length()];
                            album_covers = new String[data.length()];
                            album_id = new String[data.length()];

                            for(int i = 0; i < data.length(); i++){
                                album_titles[i] = data.getJSONObject(i).getString("name");
                                album_id[i] = data.getJSONObject(i).getString("id");
                                album_covers[i] = data.getJSONObject(i).getJSONObject("picture").getJSONObject("data").getString("url");
                            }

                            CustomListAdapter adapter = new CustomListAdapter(MainActivity.this, album_titles, album_covers);
                            album_list.setAdapter(adapter);

                            album_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Toast.makeText(MainActivity.this, i + " Clicked", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, GridPictures.class);
                                    intent.putExtra("id", album_id[i]);
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
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
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
}
