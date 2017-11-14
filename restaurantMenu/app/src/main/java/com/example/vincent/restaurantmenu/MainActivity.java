package com.example.vincent.restaurantmenu;

import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    public ArrayList unJSONify(String JSONstring, int type) throws JSONException {
        ArrayList<JSONObject> menu = new ArrayList<JSONObject>();
        if (type== 1) {
            try {
                JSONObject object = (JSONObject) new JSONTokener(JSONstring).nextValue();
                JSONArray subArray = object.getJSONArray("items");
                for (int i = 0; i < subArray.length(); i++) {
                    menu.add(subArray.getJSONObject(i));
                }
            } catch (JSONException e) {
            }
        }
        return menu;
    }
    public ArrayList result= new ArrayList();
    public void getMenu(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://resto.mprog.nl/menu";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {final TextView mTextView = findViewById(R.id.textView);
                            result= unJSONify(response, 1);
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

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_menu);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_order);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getMenu();
        final TextView mTextView = findViewById(R.id.textView);
        mTextMessage = findViewById(R.id.message);
        mTextView.setText(result.toString());
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


}
