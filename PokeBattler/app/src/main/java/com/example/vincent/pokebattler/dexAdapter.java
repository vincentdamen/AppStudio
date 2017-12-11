package com.example.vincent.pokebattler;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


class dexAdapter extends ArrayAdapter<pokemon>{
        private Context context;
        private ArrayList<pokemon> pokemons;
        private StorageReference mStorageRef;

        public dexAdapter(Context context,int resource, ArrayList<pokemon> pokemons) {
            super(context,resource, pokemons);
            this.context = context;
            this.pokemons = pokemons;
        }

    @SuppressLint("ResourceAsColor")
    @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
        // make other way
        String Location = "sprites/0" + pokemons.get(position).no + ".png";
        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference().child(Location);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.row_layout, null);
        final ImageView imageView = (ImageView) view.findViewById(R.id.PokePicture);
        mStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String imageURL = uri.toString();
                Glide.with(context).load(imageURL).into(imageView);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
        TextView Name = (TextView) view.findViewById(R.id.PokeName);
        TextView Number = (TextView) view.findViewById(R.id.No);
        long no = pokemons.get(position).no;
        Name.setText(pokemons.get(position).Name);
        Number.setText(no + "");

        return view;
    }}

