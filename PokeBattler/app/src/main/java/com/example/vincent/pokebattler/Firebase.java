package com.example.vincent.pokebattler;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Firebase {
    public dexAdapter DexAdapter;


    public Firebase(){

    }
    public ArrayList<battles> getABattle() {
            final ArrayList<battles> notes = new ArrayList<battles>();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference nDatabase= database.getReference("Battles");
            nDatabase.addListenerForSingleValueEvent(new ValueEventListener(){
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                        battles battles = noteDataSnapshot.getValue(battles.class);
                        notes.add(battles);
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });
            return notes;
    }


    public ArrayList<pokemon> getThePokemon() {

        final ArrayList<pokemon> notes = new ArrayList<pokemon>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference nDatabase= database.getReference("Pokemon");
        nDatabase.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    pokemon Pokemons2 = noteDataSnapshot.getValue(pokemon.class);
                    notes.add(Pokemons2);
                    }
                }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        return notes;
    }


}
