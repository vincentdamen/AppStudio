package com.example.vincent.restaurantrevisited;


import android.app.Dialog;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends DialogFragment {
    private RestoAdapter adapter;
    private RestoDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        ListView list = (ListView) view.findViewById(R.id.List);
        db = RestoDatabase.getInstance(getContext());
        Cursor overview = db.selectAll();
        adapter = new RestoAdapter(getContext(), overview);
        db.insert("Italian Salad",5,1,"https://resto.mprog.nl/images/italiansalad.jpg");
        list.setAdapter(adapter);
        Toast.makeText(getContext(), "Total number of Items are:" + list.getAdapter().getCount() , Toast.LENGTH_LONG).show();



        return view;
    }

}

