package com.example.vincent.pokebattler;


import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfoDialog extends DialogFragment {

    public UserInfoDialog newInstance(String Name,float HighScore) {
        UserInfoDialog f = new UserInfoDialog();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("Name", Name);
        args.putFloat("score",HighScore);
        f.setArguments(args);

        return f;
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(false);

        final String Name = getArguments().getString("Name");
        final Float HighScore = getArguments().getFloat("score");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference nDatabase= database.getReference("Userinfo");
        nDatabase.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    User information = noteDataSnapshot.getValue(User.class);
                    String temp = String.format("%.2f", information.HighScore);
                    if (Objects.equals(information.Name, Name) &&
                            Float.parseFloat(String.format("%.2f", information.HighScore).replace(",",".")) ==HighScore) {
                        updateInfo(information);


                    }
                }


            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_info_dialog, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void updateInfo(User input){
        String PokePhoto = "sprites/0"+input.Favorites.get(0).no+".png";
        TextView name = getView().findViewById(R.id.UserNameD);
        TextView Age = getView().findViewById(R.id.Age);
        TextView Gen = getView().findViewById(R.id.Gen);
        TextView High = getView().findViewById(R.id.HighScoreD);
        TextView Favorites = getView().findViewById(R.id.Favorites);
        ImageView Picture = getView().findViewById(R.id.ImageFav);
        PlacePicture(Picture,PokePhoto);
        name.setText(input.Name);
        High.setText("Highscore: "+String.format("%.2f", input.HighScore));
        Age.setText("Age: "+input.Age);
        Gen.setText("Favorite Generation: "+input.Gen);
        Favorites.setText(GetFavorites(input.Favorites));
        getDialog().setCanceledOnTouchOutside(true);


    }
public void PlacePicture(final ImageView placeholder, String location){
    StorageReference mStorageRef = FirebaseStorage.getInstance().getReference().child(location.toLowerCase());
    mStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
        @Override
        public void onSuccess(Uri uri) {
            String imageURL = uri.toString();
            Glide.with(getContext()).load(imageURL).into(placeholder);
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception exception) {
            // Handle any errors
        }
    });
}

public String GetFavorites(ArrayList<pokemon> favorites){
    String Result = "Favorite pokemon: ";
    for(int i =0;i<favorites.size();i++){
        if(favorites.size()-i!=1){
            Result += favorites.get(i).Name+", ";}
        else{
            Result += favorites.get(i).Name;
        }

    }
    return Result;
}
    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            Intent goToNextActivity = new Intent(getContext(), Authentication.class);
            startActivity(goToNextActivity);
        }
    }

}
