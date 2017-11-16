package com.example.vincent.restaurantmenu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;


public class mealInfo extends AppCompatActivity {
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };
    public ArrayList unJSONify(String JSONstring, String name) throws JSONException {
        ArrayList results = new ArrayList();
        try {
            //Hier nog fixen dat hij namen van het menu eruit haalt
            results = new ArrayList<JSONObject>();
            JSONObject object = (JSONObject) new JSONTokener(JSONstring).nextValue();
            JSONArray subArray = object.getJSONArray("items");
            for (int i = 0; i < subArray.length(); i++) {
                if (Objects.equals(subArray.getJSONObject(i).getString("name"), name)) {
                    results.add(subArray.getJSONObject(i).getString("category"));
                    results.add(subArray.getJSONObject(i).getString("description"));
                    results.add(subArray.getJSONObject(i).getString("price"));
                    results.add(subArray.getJSONObject(i).getString("image_url"));
                    results.add(subArray.getJSONObject(i).getString("name"));
                }
            }
            } catch (JSONException e) {
            }
        return results;
    }
    public void createListMenu(ArrayList names) {
        TextView name = (TextView) findViewById(R.id.Name);
        TextView description = (TextView) findViewById(R.id.Description);
        TextView price = (TextView) findViewById(R.id.Price);
        name.setText(names.get(4).toString());
        price.setText("€"+names.get(2).toString());
        description.setText(names.get(1).toString());
        ImageView ImageView = (ImageView) findViewById(R.id.mealplaatje);
        Picasso.with(this).load(names.get(3).toString()).into(ImageView);



    }

    public void getMenu(final String name){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://resto.mprog.nl/menu";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            ArrayList result= unJSONify(response, name);
                            createListMenu(result);

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_info);
        Intent intent=getIntent();
        String meal = intent.getStringExtra("meal");
        getMenu(meal);
    }
    @Override
    public void onBackPressed() {
        Intent intent=getIntent();
        String category=intent.getStringExtra("category");
        Intent intent1 = new Intent(mealInfo.this, MainActivity.class);
        intent1.putExtra("category", category);
        startActivity(intent1);
        finish();}

    }