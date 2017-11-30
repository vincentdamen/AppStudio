package com.example.vincent.restaurantrevisited;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class OrderFragment extends DialogFragment implements View.OnClickListener {
    private RestoAdapter adapter;
    private RestoDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        ListView list = (ListView) view.findViewById(R.id.List1);
        TextView total = (TextView) view.findViewById(R.id.total);
        db = RestoDatabase.getInstance(getContext());
        Cursor overview = db.selectAll();
        adapter = new RestoAdapter(getActivity(), overview);
        list.setAdapter(adapter);
        String totalText = getTotal(overview);
        total.setText(totalText);
        Button clear = view.findViewById(R.id.clearOrder);
        clear.setOnClickListener(this);
        Button send = view.findViewById(R.id.orderButton);
        send.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case(R.id.clearOrder):
                db.Clear();
                Context context = getContext();
                CharSequence text = "Your order has been deleted";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                updateData();
            case(R.id.orderButton):
                db.Clear();
                finalizeOrder();
                updateData();
        }
    }
    // Updates the data with swapping cursors
    @SuppressLint("NewApi")
    public void updateData(){
        db = RestoDatabase.getInstance(getContext());
        Cursor overview1 = db.selectAll();
        adapter.swapCursor(overview1);
        ListView list = (ListView) getView().findViewById(R.id.List1);
        TextView total = (TextView) getView().findViewById(R.id.total);
        String totalText = getTotal(overview1);
        total.setText(totalText);
        list.setAdapter(adapter);
    }
    // Prepares total text
    public String getTotal(Cursor cursor){
        int total =0;
        while(cursor.moveToNext()){
            int sum = cursor.getInt(cursor.getColumnIndex("amount")) *
                    cursor.getInt(cursor.getColumnIndex("price"));
            total += sum ;
        }
        String output = "Total: € "+total;
        return output;
    }
    public void finalizeOrder(){
    RequestQueue queue = Volley.newRequestQueue(getContext());
    String url = "https://resto.mprog.nl/order";
    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    JSONArray orderArray;
                    try {
                        JSONObject JSONobject = new JSONObject(response);
                        Context context = getContext();
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
}
