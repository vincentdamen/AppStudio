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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    Intent intent1 = new Intent(Order.this, MainActivity.class);
                    startActivity(intent1);
                    finish();
                    return true;
                case R.id.navigation_notifications:
                    Intent intent2 = new Intent(Order.this, Order.class);
                    startActivity(intent2);
                    finish();
                    return true;}
            return false;
        }
    };

    public ArrayList unJSONify(String JSONstring) throws JSONException {
        ArrayList results = new ArrayList();
        try {
            //Hier nog fixen dat hij namen van het menu eruit haalt
            results = new ArrayList<JSONObject>();
            JSONObject object = (JSONObject) new JSONTokener(JSONstring).nextValue();
            JSONArray subArray = object.getJSONArray("items");
            for (int i = 0; i < subArray.length(); i++) {
                    results.add(subArray.getJSONObject(i).getString("name"));
                }

        } catch (JSONException e) {
        }
        return results;
    }

    public void getOrder(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://resto.mprog.nl/menu";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            ArrayList result= unJSONify(response);
                            retrieveOrder(result);
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
    public void retrieveOrder(ArrayList Names) throws JSONException {
        ArrayList names= new ArrayList<JSONArray>();
        ListView orders = (ListView) findViewById(R.id.orderslist);
        SharedPreferences output = this.getSharedPreferences("order",MODE_PRIVATE);
        ArrayList results = new ArrayList<JSONArray>();
        int totalprice = 0;
        for (int i = 0;Names.size() > i; i++ ){
            if (output.contains(Names.get(i).toString())) {
                String JSONstring = output.getString(Names.get(i).toString(),null);
                JSONArray subArray = (JSONArray) new JSONTokener(JSONstring).nextValue();
                int total = subArray.getInt(1)*subArray.getInt(2);
                subArray.put(total);
                totalprice+=total;
                names.add(subArray.get(0).toString());
                String billing = subArray.get(0).toString()+" - €"+ subArray.get(1).toString()+" x "+ subArray.get(2).toString()+" = €" + subArray.get(3).toString();
                results.add(billing);

            }
        }
        String totalOrder = "Total: €"+ totalprice;
        createOrderList(results,names,totalOrder);
        }
    public void createOrderList(final ArrayList strings, final ArrayList names, String total){

        ArrayAdapter<String> preparations = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, strings);
        ListView list = (ListView) findViewById(R.id.orderslist);
        list.setAdapter(preparations);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String name = names.get(i).toString();
            Context context = getApplicationContext();
            CharSequence text = "Removed: " + names.get(i).toString();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            removeOrder(name);


            }
        });
        TextView text =(TextView) findViewById(R.id.Total);
        text.setText(total);
    }
    public void removeOrder(String name){
        SharedPreferences yourOrder = this.getSharedPreferences("order",this.MODE_PRIVATE);
        SharedPreferences.Editor orderEditor = yourOrder.edit();
        orderEditor.remove(name).commit();
        getOrder();
    }
    public void clearOrder(View view){
        SharedPreferences yourOrder = this.getSharedPreferences("order",this.MODE_PRIVATE);
        SharedPreferences.Editor orderEditor = yourOrder.edit();
        orderEditor.clear().commit();
        getOrder();

    }
    public void finalizeOrder(View view){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://resto.mprog.nl/order";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONArray orderArray;
                        try {
                            JSONObject JSONobject = new JSONObject(response);
                            Context context = getApplicationContext();
                            CharSequence text = "You have ordered, you will get your food in: " + JSONobject.getString("preparation_time");
                            int duration = Toast.LENGTH_LONG;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();

                        }
                        catch (JSONException e){
                            String errors = e.toString();
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
        setContentView(R.layout.activity_order);
        getOrder();
        BottomNavigationView navigation = findViewById(R.id.navigation );
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    public void onBackPressed() {
        Intent intent1 = new Intent(Order.this, MainActivity.class);
        startActivity(intent1);
        finish();}
}
