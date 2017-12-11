package com.example.vincent.pokebattler;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class BattleScreen extends Fragment {
    private Firebase firebase;
    public ArrayList<battles> chosenBattles = new ArrayList<battles>();
    private CountDownTimer timer;
    public String numberA = "34";
    public String numberB = "3";
    public boolean AWins = true;
    public int correctAnswers = 0;
    public int totalFights = 0;
    public float Score = 0;
    public int Streak = 0;
    public int Question = 0;
    public float Reward = 0;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_battle_screen, container, false);
        TextView start = view.findViewById(R.id.Start);
        start.setOnClickListener(new startGame());
        FloatingActionButton A = view.findViewById(R.id.infoA);
        FloatingActionButton B = view.findViewById(R.id.InfoB);
        ImageView imageA = view.findViewById(R.id.ImageA);
        ImageView imageB = view.findViewById(R.id.ImageB);
        imageA.setClickable(false);
        imageB.setClickable(false);

        B.setOnClickListener(new ShowDetails());
        A.setOnClickListener(new ShowDetails());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference nDatabase = database.getReference("Battles");

        nDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long Amountentries = dataSnapshot.getChildrenCount();
                int total = (int)Amountentries;
                while (chosenBattles.size()<250){
                    battles chosenBattle = dataSnapshot.child(getRandomInt(total))
                            .getValue(battles.class);
                    chosenBattles.add(chosenBattle);
                }
                LinearLayout everything = getView().findViewById(R.id.Everything);
                ConstraintLayout loading = getView().findViewById(R.id.LoadingScreen);
                setFight();
                loading.setVisibility(View.GONE);
                everything.setVisibility(View.VISIBLE);
            }
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
            reset();
            Timerfunction(0);

        }}


    private class ShowDetails implements View.OnClickListener {
        //makes a onClick event to add a meal to your order
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case(R.id.infoA):
                    openDialog(numberA);
                    break;
                case (R.id.InfoB):
                    openDialog(numberB);
                    break;}}}

    public void Timerfunction(int time){
        if (time==0);{
            time=120000;
        }
        final ImageView imageA = getView().findViewById(R.id.ImageA);
        final ImageView imageB = getView().findViewById(R.id.ImageB);
        imageA.setClickable(true);
        imageB.setClickable(true);
        imageA.setOnClickListener(new chooseWinner());
        imageB.setOnClickListener(new chooseWinner());
        final TextView start = getView().findViewById(R.id.Start);
        final ProgressBar bar = getView().findViewById(R.id.Timer);
        start.setClickable(false);
        bar.setMax(120000);
        start.setText("Who's gonna win this battle?");

        timer = new CountDownTimer(time, 100) {

            public void onTick(long millisUntilFinished) {
                bar.setProgress(120000-(int)millisUntilFinished);
            }

            public void onFinish() {
                start.setText("Wanna play again? Click me");
                start.setClickable(true);
                imageA.setClickable(false);
                imageB.setClickable(false);
                setFight();

            }
        }.start();
    }
    public void openDialog(String no) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        PokemonInfo fragment3 = new PokemonInfo().newInstance(no);
        fragment3.show(ft, "dialog");
    }

    private class chooseWinner implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case(R.id.ImageA):

                    if(AWins){
                        if(Streak>4){
                        Score += Reward;}
                        Score += Reward;
                        Streak += 1;
                        correctAnswers += 1;
                        totalFights += 1;
                        setFight();
                    }
                    else{
                        Reward = 0;
                        Streak = 0;
                        totalFights +=1;
                        setFight();
                    }

                    break;
                case (R.id.ImageB):
                    if(!AWins){
                        if(Streak>4){
                            Score += Reward;}
                        Score += Reward;
                        Streak += 1;
                        correctAnswers += 1;
                        totalFights += 1;
                        setFight();
                    }
                    else{
                        Reward = 0;
                        Streak = 0;
                        totalFights +=1;
                        setFight();
                    }
                    break;}
        }
    }

    public void setFight(){
        final battles nextBattle = chosenBattles.get(Question);
        Question+=1;
        Reward = (float) Math.pow(2,nextBattle.Score);
        Log.d("reward",nextBattle.toString());
        AWins = nextBattle.Winner == nextBattle.First_pokemon;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference nDatabase= database.getReference("Pokemon");
        nDatabase.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    pokemon Pokemons2 = noteDataSnapshot.getValue(pokemon.class);
                    if (Pokemons2.DexNo == nextBattle.First_pokemon) {
                        updateInfo(Pokemons2,true); }
                    else if(Pokemons2.DexNo == nextBattle.Second_pokemon){
                        updateInfo(Pokemons2,false);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


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

    public void updateInfo(pokemon input,Boolean A){

        String PokePhoto = "sprites/0"+input.no+".png";
        String PokeType1 = "types/"+input.Type1+".png";
        String PokeType2 = "types/"+input.Type2+".png";
        if(A){
            numberA=""+input.no;
            ImageView Type1 = getView().findViewById(R.id.Type1A);
            final ImageView Type2 = getView().findViewById(R.id.Type2A);
            ImageView Picture = getView().findViewById(R.id.ImageA);
            TextView name = getView().findViewById(R.id.NameA);
            PlacePicture(Type1,PokeType1);
            PlacePicture(Picture,PokePhoto);
            name.setText(input.Name);
            if(Objects.equals("None", input.Type2)){

                Type2.setVisibility(View.GONE);
            }
            else{
                PlacePicture(Type2,PokeType2);
            }
        }
        else{
            Log.d("changeB","enterd");
            numberB=""+input.no;
            ImageView Type1 = getView().findViewById(R.id.Type1B);
            final ImageView Type2 = getView().findViewById(R.id.Type2B);
            ImageView Picture = getView().findViewById(R.id.ImageB);
            TextView name = getView().findViewById(R.id.NameB);
            PlacePicture(Type1,PokeType1);
            PlacePicture(Picture,PokePhoto);
            name.setText(input.Name);
            if(Objects.equals("None", input.Type2)){

                Type2.setVisibility(View.GONE);
            }
            else{
                PlacePicture(Type2,PokeType2);
            }}
        TextView answered = getView().findViewById(R.id.answered);
        TextView score = getView().findViewById(R.id.score);
        TextView correct = getView().findViewById(R.id.correct);
        TextView streak = getView().findViewById(R.id.streak);
        answered.setText("Total: "+totalFights);
        score.setText(String.format("%.2f", Score)+" points");
        correct.setText("Correct: "+correctAnswers);

        if(Streak>4){
            streak.setVisibility(View.VISIBLE);
        }
        else{
            streak.setVisibility(View.INVISIBLE);
        }
    }

    public void reset(){
        Reward=0;
        Score=0;
        correctAnswers=0;
        Streak=0;
        totalFights=0;
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
