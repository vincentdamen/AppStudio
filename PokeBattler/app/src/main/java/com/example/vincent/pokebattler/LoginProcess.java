package com.example.vincent.pokebattler;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginProcess extends DialogFragment implements View.OnClickListener{



    public boolean CheckInfo (CharSequence target,CharSequence password) {
        return !(target == null || password == null) && password.length() > 6 &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public LoginProcess() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_process, container, false);
        getDialog().setCanceledOnTouchOutside(false);
        Button login =  view.findViewById(R.id.ButtonLogin);
        login.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case(R.id.ButtonLogin):

                EditText mail =  (EditText) getDialog().findViewById(R.id.EmailLogin);
                EditText password =(EditText) getDialog().findViewById(R.id.PasswordLogin);
                String email = mail.getText().toString();
                String passwords = password.getText().toString();
                if(email.length()>0 & passwords.length()>0){
                if (CheckInfo(email,passwords)) {
                    checkLogin(email,passwords);
                }
                else{
                    TextView errorBlock = getDialog().findViewById(R.id.Errorblock);
                    errorBlock.setText(R.string.wrongInput);
                    errorBlock.setTextColor(getResources().getColor(R.color.error));
                }
                }else{
                    TextView errorBlock = getDialog().findViewById(R.id.Errorblock);
                    errorBlock.setText(R.string.error_empty_fields);
                    errorBlock.setTextColor(getResources().getColor(R.color.error));
                }

                break;
        }
    }
    public void checkLogin(String email, String Passwords){
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, Passwords)
                .addOnCompleteListener( getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            getActivity().finish();
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            TextView errorBlock = getDialog().findViewById(R.id.Errorblock);
                            errorBlock.setText(R.string.loginFailed);
                            errorBlock.setTextColor(getResources().getColor(R.color.error));
                        }

                    }
                });
    }
}
