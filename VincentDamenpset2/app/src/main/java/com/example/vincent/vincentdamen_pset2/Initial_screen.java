package com.example.vincent.vincentdamen_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Initial_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_screen);
    }
    public void goToSecond(View view){
        Intent intent = new Intent(this, Input_words.class);
        startActivity(intent);
        finish();

    }
}
