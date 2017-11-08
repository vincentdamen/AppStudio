package com.example.vincent.vincentdamen_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;

public class Input_words extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_words);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Initial_screen.class);
        startActivity(intent);
        finish();
    }
    public void changeText(View view){
        Intent intent= getIntent();
        String file = intent.getStringExtra("file");
        TextView textViewToChange = (TextView) findViewById(R.id.textView2);
        textViewToChange.setText(file);}
}
