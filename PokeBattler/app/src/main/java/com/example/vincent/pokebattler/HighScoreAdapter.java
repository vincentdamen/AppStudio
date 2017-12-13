package com.example.vincent.pokebattler;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

class HighScoreAdapter extends ArrayAdapter<User> {
    private Context context;
    private ArrayList<User> Users;
    private StorageReference mStorageRef;

    public HighScoreAdapter(Context context,int resource, ArrayList<User> Users) {
            super(context,resource, Users);
            this.context = context;
            this.Users = Users;
            }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // make other way
        String Location = "sprites/0" + Users.get(position).Favorites.get(0).no + ".png";

        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference().child(Location);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.row_layout_highscore, null);
        final ImageView imageView = (ImageView) view.findViewById(R.id.FavoritePokemon);
        mStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String imageURL = uri.toString();
                Glide.with(context).load(imageURL).into(imageView);
                }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
        public void onFailure(@NonNull Exception exception) {}});
        TextView Name = (TextView) view.findViewById(R.id.UserName);
        final TextView Number = (TextView) view.findViewById(R.id.Place);
        TextView Score = (TextView) view.findViewById(R.id.Score);
        Name.setText(Users.get(position).Name);
        Number.setText(position +1 + "");
        Score.setText(String.format("%.2f", Users.get(position).HighScore)+"");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference nDatabase = database.getReference("Userinfo");
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser userId = mAuth.getCurrentUser();
        nDatabase.child(userId.getUid()).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User information = dataSnapshot.getValue(User.class);
                        if(information.HighScore==Users.get(position).HighScore){
                            LinearLayout row = view.findViewById(R.id.Complete);
                            row.setBackgroundColor(getContext().getColor(R.color.colorAccent));
                        }}
                    @Override
                    public void onCancelled(DatabaseError databaseError) {}});
        return view;
        }}