package com.example.vincent.vincentdamen_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

public class Result_page extends AppCompatActivity {
    TextView resultBox;
    String file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        Intent intent = getIntent();
        String storyTime = intent.getStringExtra("story");
        file = intent.getStringExtra("file");
        resultBox = (TextView) findViewById(R.id.results);
        resultBox.setText(Html.fromHtml(storyTime));
    }
    public void restart(View view){
        Intent intent = new Intent(this, Initial_screen.class);
        startActivity(intent);
        finish();
    }
    public void oneBack(View view){
        Intent intent= new Intent(this,Input_words.class);
        intent.putExtra("file", file);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Initial_screen.class);
        startActivity(intent);
        finish();
    }
}
