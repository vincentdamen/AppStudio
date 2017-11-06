package com.example.vincent.vincentdamen_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Input_words extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_words);
    }
    @Override
    public void  onBackPressed(){
        Intent intent = new Intent(this, Initial_screen.class);
        startActivity(intent);
        finish();
    }
}
