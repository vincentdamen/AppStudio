package com.example.vincent.restaurantmenu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.Objects;


public class mealInfo extends AppCompatActivity {
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    Intent intent1 = new Intent(mealInfo.this, MainActivity.class);
                    startActivity(intent1);
                    finish();
                    return true;
                case R.id.navigation_notifications:
                    Intent intent2 = new Intent(mealInfo.this, Order.class);
                    startActivity(intent2);
                    finish();
                    return true;}
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
        price.setText(names.get(2).toString());
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
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    @Override
    public void onBackPressed() {
        Intent intent=getIntent();
        String category=intent.getStringExtra("category");
        Intent intent1 = new Intent(mealInfo.this, MainActivity.class);
        intent1.putExtra("category", category);
        startActivity(intent1);
        finish();}

    public String JSONify(String Name, String Price, String Amount){
        JSONArray results = new JSONArray();
        results.put(Name);
        results.put(Price);
        results.put(Amount);
        return results.toString();
    }
    public void fixOrder(View view){
        TextView name = (TextView) findViewById(R.id.Name);
        Spinner Amount = (Spinner) findViewById(R.id.Amount);
        TextView price = (TextView) findViewById(R.id.Price);
        String message= JSONify(name.getText().toString(),price.getText().toString(),Amount.getSelectedItem().toString());

        SharedPreferences yourOrder = this.getSharedPreferences("order",this.MODE_PRIVATE);
        SharedPreferences.Editor orderEditor = yourOrder.edit();
        orderEditor.putString(name.getText().toString(),message);
        orderEditor.commit();
        Context context = getApplicationContext();
        CharSequence text = "You have ordered: "+ Amount.getSelectedItem().toString() + "times the "+ name.getText().toString();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    }