package com.example.vincent.vincentdamen_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class select_file extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_file);
    }
    String Files[]= {"madlib0_simple.txt","madlib1_tarzan.txt","madlib2_university.txt",
            "madlib3_clothes.txt","madlib4_dance.txt",};

    public Story getStory(String inFile) {
        try {
            InputStream Stream = getAssets().open(inFile);
            return new Story(Stream);
        } catch (IOException e) {
            return null;
        }
    }
    public void onClick(View view){
        Spinner mySpinner=(Spinner) findViewById(R.id.Spin);
        String text = mySpinner.getSelectedItem().toString();
        String file = "";
        if (Objects.equals(text, "Simple")){
            file = Files[0];
        } else if (Objects.equals(text, "Tarzan")){
            file = Files[1];
        } else if (Objects.equals(text, "University")){
            file = Files[2];
        } else if (Objects.equals(text, "Clothes")){
            file = Files[3];
        } else if (Objects.equals(text, "Dance")){
            file = Files[4];
        }
        Story story = getStory(file);
        Intent intent = new Intent(this, Input_words.class);
        intent.putExtra("file", file);
        intent.putExtra("story",story);
        startActivity(intent);
        finish();
    }
    @Override
    public void  onBackPressed(){
        Intent intent = new Intent(this, Initial_screen.class);
        startActivity(intent);
        finish();
    }
}
