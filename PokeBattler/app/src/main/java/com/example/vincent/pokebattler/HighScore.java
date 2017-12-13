package com.example.vincent.pokebattler;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class HighScore extends ListFragment {
    private HighScoreAdapter HighScoreadap;


    public HighScore() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_high_score, container, false);
    }
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference nDatabase = database.getReference("Userinfo");
        nDatabase.orderByChild("HighScore").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<User> notes = new ArrayList<User>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

                    User Users = noteDataSnapshot.getValue(User.class);
                    notes.add(Users);
                }
                Collections.reverse(notes);
                HighScoreadap = new HighScoreAdapter(getContext(), 1, notes);
                makeList(HighScoreadap);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }});}

    public void makeList(HighScoreAdapter highScoreadap) {
        ProgressBar bar = getView().findViewById(R.id.LoadingBar);
        bar.setVisibility(View.GONE);
        this.setListAdapter(highScoreadap);
        getListView().setOnItemClickListener(new ShowDetails());
    }

    private class ShowDetails implements AdapterView.OnItemClickListener {
        @Override
        //makes a onItemClick event to add a meal to your order
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Context context = getContext();
            TextView name = view.findViewById(R.id.UserName);
            TextView HighScore = view.findViewById(R.id.Score);
            float score = Float.parseFloat(HighScore.getText().toString());
            String text = name.getText().toString();
            openDialog(text,score);
        }}

    public void openDialog(String no,float HighScore) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        UserInfoDialog fragment3 = new UserInfoDialog().newInstance(no, HighScore);
        fragment3.show(ft, "dialog");
    }
}
