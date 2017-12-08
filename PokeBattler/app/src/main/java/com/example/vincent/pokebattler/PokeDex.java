package com.example.vincent.pokebattler;


import android.app.DialogFragment;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokeDex extends ListFragment {
    private Firebase firebase;
    private dexAdapter DexAdapter;
    public PokeDex() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_poke_dex, container, false);

        return view;
    }


    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference nDatabase= database.getReference("Pokemon");
        nDatabase.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<pokemon> notes = new ArrayList<pokemon>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    pokemon Pokemons2 = noteDataSnapshot.getValue(pokemon.class);
                    notes.add(Pokemons2);}
                    DexAdapter = new dexAdapter(getContext(),1,notes);
                    makeList(DexAdapter);

            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        }

    public void makeList(dexAdapter DexAdapter){
        this.setListAdapter(DexAdapter);
        getListView().setOnItemClickListener(new ShowDetails());


    }

    private class ShowDetails implements AdapterView.OnItemClickListener {
        @Override
        //makes a onItemClick event to add a meal to your order
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Context context = getContext();
            TextView textviw=view.findViewById(R.id.No);
            String text = textviw.getText().toString();
            openDialog(text);


        }
        }
    public void openDialog(String no){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        PokemonInfo fragment3 = new PokemonInfo().newInstance(no);
        fragment3.show(ft,"dialog");
    }
}


