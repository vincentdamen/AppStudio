package com.example.vincent.vincentdamen_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
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
    public Story getStory(String inFile) {
        try {
            InputStream Stream = getAssets().open(inFile);
            return new Story(Stream);
        } catch (IOException e) {
            return null;
        }
    }
    public String randomFile(String[] listOfFiles ){

        int length = listOfFiles.length;
        Random r = new Random();
        int chosenOne = r.nextInt(length);
        return listOfFiles[chosenOne];
    }
    public void getTextRandom(View view){
        String Files[]= {"madlib0_simple.txt","madlib1_tarzan.txt","madlib2_university.txt",
                "madlib3_clothes.txt","madlib4_dance.txt",};
        String file = randomFile(Files);
        Story story = getStory(file);
        Intent intent = new Intent(this, Input_words.class);
        intent.putExtra("file", file);
        intent.putExtra("story", story);
        startActivity(intent);
        finish();

    }
}
