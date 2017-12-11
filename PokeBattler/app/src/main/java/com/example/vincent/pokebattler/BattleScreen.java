package com.example.vincent.pokebattler;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class BattleScreen extends Fragment {
    private Firebase firebase;
    final ArrayList<battles> chosenBattles = new ArrayList<battles>();
    public String numberA = "34";
    public String numberB = "3";
    private CountDownTimer timer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_battle_screen, container, false);
        TextView start = view.findViewById(R.id.Start);
        start.setOnClickListener(new startGame());
        FloatingActionButton A = view.findViewById(R.id.infoA);
        FloatingActionButton B = view.findViewById(R.id.InfoB);
        B.setOnClickListener(new ShowDetails());
        A.setOnClickListener(new ShowDetails());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference nDatabase = database.getReference("Battles");

        nDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long Amountentries = dataSnapshot.getChildrenCount();
                int total = (int)Amountentries;
                while (chosenBattles.size()<2000){
                    battles chosenBattle = dataSnapshot.child(getRandomInt(total))
                            .getValue(battles.class);
                    chosenBattles.add(chosenBattle);
                }}
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }});
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    public static String getRandomInt(int total) {
        int chosen = new Random().nextInt(total+1);
        return chosen+"";}

    private class startGame implements View.OnClickListener{
        @Override
        public void onClick(final View view) {
            Timerfunction(0);

        }}


    private class ShowDetails implements View.OnClickListener {
        //makes a onClick event to add a meal to your order
        @Override
        public void onClick(View view) {
            ProgressBar bar = getView().findViewById(R.id.Timer);
            switch (view.getId()){
                case(R.id.infoA):
                    openDialog(numberA);
                    break;
                case (R.id.InfoB):
                    openDialog(numberB);
                    break;}}}

    public void Timerfunction(int time){
        if (time==0);{
            time=60000;
        }

        final TextView start = getView().findViewById(R.id.Start);
        final ProgressBar bar = getView().findViewById(R.id.Timer);
        start.setClickable(false);
        bar.setMax(60000);
        start.setText("Who's gonna win this battle?");

        timer = new CountDownTimer(time, 100) {

            public void onTick(long millisUntilFinished) {
                bar.setProgress(60000-(int)millisUntilFinished);
            }

            public void onFinish() {
                start.setText("Wanna play again? Click me");
                start.setClickable(true);

            }
        }.start();
    }
    public void openDialog(String no) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        PokemonInfo fragment3 = new PokemonInfo().newInstance(no);
        fragment3.show(ft, "dialog");
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
