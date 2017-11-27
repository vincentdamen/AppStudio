package com.example.vincent.restaurantrevisited;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class RestoAdapter extends CursorAdapter {
    public RestoAdapter(Context context, Cursor cursor) {
        super(context, cursor, R.layout.row_layout);

    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView item = view.findViewById(R.id.item);
        item.setText(cursor.getString(cursor.getColumnIndex("name")));

    }

}
