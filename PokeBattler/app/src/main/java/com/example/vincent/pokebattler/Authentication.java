package com.example.vincent.pokebattler;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Authentication extends AppCompatActivity {

    public class AuthenticationFlow implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case (R.id.Login):
                    openDialog(false);
                    break;
                case (R.id.Register):
                    openDialog(true);
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        Button login = (Button) findViewById(R.id.Login);
        Button register = (Button) findViewById(R.id.Register);
        login.setOnClickListener(new AuthenticationFlow());
        register.setOnClickListener(new AuthenticationFlow());
    }

    public void openDialog(boolean Register) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (Register) {
            RegisterProcess fragment = new RegisterProcess();
            fragment.show(ft, "dialog");

        } else {
            LoginProcess fragment = new LoginProcess();
            fragment.show(ft, "dialog");
        }
    }

    private boolean exit = false;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

}

