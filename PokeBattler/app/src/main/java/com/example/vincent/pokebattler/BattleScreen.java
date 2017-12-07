package com.example.vincent.pokebattler;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class BattleScreen extends Fragment {
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public BattleScreen() {
        // Required empty public constructor
    }
    public ArrayList<battles> getABattle(final View view,int amountOfBattles ) {
        final ArrayList<battles> notes = new ArrayList<battles>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference nDatabase= database.getReference("Battles");
        nDatabase.addListenerForSingleValueEvent(new ValueEventListener(){
        @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    battles battles = noteDataSnapshot.getValue(battles.class);
                    TextView temp = view.findViewById(R.id.Name1);
                    temp.setText(noteDataSnapshot.toString());
                    notes.add(battles);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        return notes;
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_battle_screen, container, false);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        ArrayList<battles> battles = getABattle(view,3);
        return inflater.inflate(R.layout.fragment_battle_screen, container, false);
    }

}
