package com.example.vincent.vincentdamen_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.Objects;

public class Input_words extends AppCompatActivity {
    int totalWords = 0;
    int wordsDone = 0;
    String currentWord = " ";
    TextView placeHolder;
    ProgressBar progressBar;
    EditText editBox;
    Story story;
    String file="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_words);
        Intent intent = getIntent();

        file = intent.getStringExtra("file");
        story= (Story) intent.getSerializableExtra("story");
        placeHolder = (TextView) findViewById(R.id.Input);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        editBox = (EditText) findViewById(R.id.editBox);
        totalWords= story.getPlaceholderRemainingCount();
        currentWord = story.getNextPlaceholder();
        placeHolder.setText(currentWord);
        changeProgress(totalWords,wordsDone,progressBar);
        findViewById(R.id.submit).setOnClickListener(new Listener());}


    public class Listener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            submitWord();
        }
    }

    @Override
    public void onBackPressed() {
        story.clear();
        Intent intent = new Intent(this, Initial_screen.class);
        startActivity(intent);
        finish();
    }
    public void changeProgress(int totalWords, int wordsDone, ProgressBar progressBar){
        progressBar.setMax(totalWords);
        progressBar.setProgress(wordsDone);
    }
    public void submitWord(){

        String newWord = editBox.getText().toString();
        if (!Objects.equals(newWord, "")){
            wordsDone+=1;
            changeProgress(totalWords,wordsDone,progressBar);
            story.fillInPlaceholder(newWord);
            editBox.setText("");
            currentWord = story.getNextPlaceholder();
            placeHolder.setText(currentWord);
            if (totalWords==wordsDone) {
                wordsDone=0;
                totalWords=0;
                String result = story.toString();
                showResult(result,file);
            }
        }
    }
    public void showResult(String result,String file){
        Intent intent = getIntent();
        Intent intent1 = new Intent(this, Result_page.class);
        intent1.putExtra("story1",result);
        intent1.putExtra("story",intent.getSerializableExtra("story"));
        intent1.putExtra("file",file);
        startActivity(intent1);
        finish();
    }
}
