package com.example.vincent.restaurantrevisited;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = this.getArguments();
        final String category = arguments.getString("category");
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://resto.mprog.nl/menu";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList results = new ArrayList();
                        try {
                            results = new ArrayList<JSONObject>();
                            JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
                            JSONArray subArray = object.getJSONArray("items");
                            for (int i = 0; i < subArray.length(); i++) {
                                if (Objects.equals(subArray.getJSONObject(i).getString("category"), category)) {
                                    results.add(subArray.getJSONObject(i).getString("name"));
                                }
                            }
                            setAdapter(results);
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
    public void setAdapter(ArrayList<String> results){
        this.setListAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, results ));
    }
}

