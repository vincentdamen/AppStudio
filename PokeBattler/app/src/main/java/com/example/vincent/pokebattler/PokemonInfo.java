package com.example.vincent.pokebattler;


import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonInfo extends DialogFragment {
    private Firebase firebase;
    private StorageReference mStorageRef;

    public PokemonInfo newInstance(String num) {
        PokemonInfo f = new PokemonInfo();
        Long number = new Long(num).longValue();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putLong("num", number);
        f.setArguments(args);

        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Long no = getArguments().getLong("num");
        getDialog().setCanceledOnTouchOutside(false);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference nDatabase= database.getReference("Pokemon");
        nDatabase.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    pokemon Pokemons2 = noteDataSnapshot.getValue(pokemon.class);
                    if (Pokemons2.no == no) {
                        updateInfo(Pokemons2);

                    }
                }


            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_info, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void updateInfo(pokemon input){
        String PokePhoto = "sprites/0"+input.no+".png";
        String PokeType1 = "types/"+input.Type1+".png";
        String PokeType2 = "types/"+input.Type2+".png";
        TextView name = getView().findViewById(R.id.Name);
        TextView Hp = getView().findViewById(R.id.Hp);
        TextView Legendary = getView().findViewById(R.id.Legendary);
        TextView Generation = getView().findViewById(R.id.Generation);
        TextView No = getView().findViewById(R.id.No);
        TextView Dexno = getView().findViewById(R.id.DexNo);
        TextView Attack = getView().findViewById(R.id.Attack);
        TextView SpAttack = getView().findViewById(R.id.SpAttack);
        TextView SpDefense = getView().findViewById(R.id.SpDefense);
        TextView Defense = getView().findViewById(R.id.Defense);
        TextView Speed = getView().findViewById(R.id.Speed);
        ImageView Type1 = getView().findViewById(R.id.Type1);
        final ImageView Type2 = getView().findViewById(R.id.Type2);
        ImageView Picture = getView().findViewById(R.id.ImagePoke);
        PlacePicture(Type1,PokeType1);
        PlacePicture(Picture,PokePhoto);
        name.setText(input.Name);
        Hp.setText("HP: "+input.HP);
        Legendary.setText("Legendary: "+input.Legendary);
        No.setText("No: "+input.no);
        Dexno.setText("Dexno: "+input.DexNo);
        Attack.setText("Attack: "+input.Attack);
        SpAttack.setText("SpAttack: "+input.SpAtk);
        Defense.setText("Defense: "+input.Defense);
        SpDefense.setText("SpDefense: "+input.SpDef);
        Speed.setText("Speed: "+input.Speed);
        Generation.setText("Gen: "+input.Generation);
        if(Objects.equals("None", input.Type2)){

            Type2.setVisibility(View.GONE);
        }
        else{
            PlacePicture(Type2,PokeType2);
        }

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
