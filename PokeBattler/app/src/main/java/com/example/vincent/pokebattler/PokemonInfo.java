package com.example.vincent.pokebattler;


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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonInfo extends DialogFragment {
    private Firebase firebase;

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
        TextView name = getView().findViewById(R.id.Name);
        TextView Hp = getView().findViewById(R.id.Hp);
        TextView Legendary = getView().findViewById(R.id.Legendary);
        TextView No = getView().findViewById(R.id.No);
        TextView Dexno = getView().findViewById(R.id.DexNo);
        TextView Attack = getView().findViewById(R.id.Attack);
        TextView SpAttack = getView().findViewById(R.id.SpAttack);
        TextView SpDefense = getView().findViewById(R.id.SpDefense);
        TextView Defense = getView().findViewById(R.id.Defense);
        TextView Speed = getView().findViewById(R.id.Speed);
        ImageView Type1 = getView().findViewById(R.id.Type1);
        ImageView Type2 = getView().findViewById(R.id.Type2);
        ImageView Picture = getView().findViewById(R.id.ImagePoke);
        name.setText(input.Name);
        Hp.setText(input.HP+"");
        Legendary.setText(input.Legendary+"");
        No.setText(input.no+"");
        Dexno.setText(input.DexNo+"");
        Attack.setText(input.Attack+ "");
        SpAttack.setText(input.SpAtk+ "");
        Defense.setText(input.Defense+ "");
        SpDefense.setText(input.SpDef+ "");
        Speed.setText(input.Speed+ "");
        if(Objects.equals("None", input.Type2)){

            Type2.setVisibility(View.GONE);
        }



    }
}
