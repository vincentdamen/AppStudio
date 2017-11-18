package com.example.vincent.to_dolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToDoDatabase db = ToDoDatabase.getInstance(getApplicationContext());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        EditText editor = (EditText) findViewById(R.id.Editor);
        String text = editor.getText().toString();
        outState.putString("editor",text);
    }

    @Override
    public void onRestoreInstanceState(Bundle inState){
        super.onRestoreInstanceState(inState);
        EditText editor = (EditText) findViewById(R.id.Editor);
        String text = inState.getString("editor");
        editor.setText(text);
    }
}
