package com.example.vincent.pokebattler;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class Authentication extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        FragmentManager fm = getSupportFragmentManager();
        LoginMenu fragment = new LoginMenu();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.Frame_layout, fragment);
        ft.commit();
    }



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

}

