package com.example.vincent.restaurantrevisited;


import android.app.Dialog;
import android.content.Context;
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
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        ListView list = (ListView) view.findViewById(R.id.List1);
        db = RestoDatabase.getInstance(getContext());
        Cursor overview = db.selectAll();
        adapter = new RestoAdapter(getActivity(), overview);
        list.setAdapter(adapter);

        return view;
    }

}
