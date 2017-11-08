package com.example.vincent.vincentdamen_pset2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Random;

public class Initial_screen extends AppCompatActivity {
    String Files[]= {"madlib0_simple.txt","madlib1_tarzan.txt","madlib2_university.txt",
                     "madlib3_clothes.txt","madlib4_dance.txt",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_screen);
    }
    public void goToSecond(View view){
        Intent intent = new Intent(this, select_file.class);
        startActivity(intent);
        finish();
    }
    public String randomFile(String[] listOfFiles ){

        int length = listOfFiles.length;
        Random r = new Random();
        int chosenOne = r.nextInt(length);
        return listOfFiles[chosenOne];
    }
//    public InputStream getText(String inFile) {
//        try
//        {
//            InputStream stream = getAssets().open(inFile);
//            return stream;
//        } catch(IOException e) {
//            return null;
//       }
//    }
    public void getTextRandom(View view){
        String Files[]= {"madlib0_simple.txt","madlib1_tarzan.txt","madlib2_university.txt",
                "madlib3_clothes.txt","madlib4_dance.txt",};
        String file = randomFile(Files);
        //InputStream Stream = getText(file);
        Intent intent = new Intent(this, Input_words.class);
        intent.putExtra("file", file);
        startActivity(intent);
        finish();

    }
}
