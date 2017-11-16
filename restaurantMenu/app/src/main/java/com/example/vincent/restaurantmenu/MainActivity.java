package com.example.vincent.restaurantmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Objects;



public class MainActivity extends AppCompatActivity {
    public int state = 0;
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
    public ArrayList unJSONify(String JSONstring, int type, String category) throws JSONException {
        ArrayList results = new ArrayList();
        if (type == 1) {
            try {
                //Hier nog fixen dat hij namen van het menu eruit haalt
                results = new ArrayList<JSONObject>();
                JSONObject object = (JSONObject) new JSONTokener(JSONstring).nextValue();
                JSONArray subArray = object.getJSONArray("items");
                for (int i = 0; i < subArray.length(); i++) {
                    if (Objects.equals(subArray.getJSONObject(i).getString("category"), category)) {
                        results.add(subArray.getJSONObject(i).getString("name"));
                    }
                }
            } catch (JSONException e) {
            }
        } else if (type == 2) {
            try {
                results = new ArrayList<String>();
                JSONObject object = (JSONObject) new JSONTokener(JSONstring).nextValue();
                JSONArray subArray = object.getJSONArray("categories");
                for (int i = 0; i < subArray.length(); i++) {
                    results.add( subArray.get(i).toString());
                }
            } catch (JSONException e) {
            }
        }
        return results;
    }
    public void createListCategory(ArrayList names){
        ArrayAdapter<String> preparations = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, names);
        ListView list = (ListView) findViewById(R.id.listing);
        list.setAdapter(preparations);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getMenu(String.valueOf(adapterView.getItemAtPosition(i)));
                state=1;
            }
        });

    }
    public void createListMenu(ArrayList names, final String category){
        ArrayAdapter<String> preparations = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, names);
        ListView list = (ListView) findViewById(R.id.listing);
        list.setAdapter(preparations);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, mealInfo.class);
                intent.putExtra("meal",String.valueOf(adapterView.getItemAtPosition(i)));
                intent.putExtra("category", category);
                startActivity(intent);
                finish();
            }
        });

    }


    public void getCategories(){
        state=0;
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://resto.mprog.nl/categories";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            ArrayList result= unJSONify(response, 2, " ");
                            createListCategory(result);

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
    public void getMenu(final String category){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://resto.mprog.nl/menu";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            ArrayList result= unJSONify(response, 1, category);
                            createListMenu(result, category);

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
        Intent intent = getIntent();
        String category = "empty";
        if(intent.hasExtra("category")){
        category = intent.getStringExtra("category");}
        if (category=="empty"){
        getCategories();}
        else{
            getMenu(category);
        }

        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    @Override
    public void onBackPressed(){
        if (state==1){
            getCategories();
        }
        else{
            super.onBackPressed();

        }

    }

}
