package com.example.vincent.pokebattler;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;


class dexAdapter extends ArrayAdapter<pokemon>{
        private Context context;
        private ArrayList<pokemon> pokemons;

        public dexAdapter(Context context,int resource, ArrayList<pokemon> pokemons) {
            super(context,resource, pokemons);
            this.context = context;
            this.pokemons = pokemons;
        }

    @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // make other way


            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.row_layout,null);

            TextView Name = (TextView)view.findViewById(R.id.PokeName);
            TextView Number=(TextView)view.findViewById(R.id.No);
                long no = pokemons.get(position).no;
                Name.setText(pokemons.get(position).Name);
                Number.setText(no + "");
                return view;

        }
    }