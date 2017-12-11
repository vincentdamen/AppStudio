package com.example.vincent.pokebattler;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.view.MenuItem;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_play:
                    FragmentManager fm = getSupportFragmentManager();
                    StartingFragment fragment = new StartingFragment();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.Fragment_container, fragment);
                    ft.commit();
                    return true;
                case R.id.navigation_highscores:

                    return true;
                case R.id.navigation_signout:
                    SignOut();
                    return false;
                case R.id.navigation_pokedex:
                    FragmentManager fm2 = getSupportFragmentManager();
                    PokeDex fragment2 = new PokeDex();
                    FragmentTransaction ft2 = fm2.beginTransaction();
                    ft2.replace(R.id.Fragment_container, fragment2);
                    ft2.commit();
                    return true;
                case R.id.navigation_train:
                    FragmentManager fm1 = getSupportFragmentManager();
                    BattleScreen fragment1 = new BattleScreen();
                    FragmentTransaction ft1 = fm1.beginTransaction();
                    ft1.replace(R.id.Fragment_container, fragment1);
                    ft1.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        StartingFragment fragment = new StartingFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.Fragment_container, fragment);
        ft.commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            Intent goToNextActivity = new Intent(getApplicationContext(), Authentication.class);
            startActivity(goToNextActivity);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    public void SignOut(){
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setMessage("Do you really want to leave me?");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Stay",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Leave",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        FirebaseAuth.getInstance().signOut();
                        onStart();
                    }
                });
        alertDialog.show();
    }
}
