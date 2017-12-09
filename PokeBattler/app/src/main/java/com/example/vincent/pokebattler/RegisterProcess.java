package com.example.vincent.pokebattler;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;
import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterProcess extends DialogFragment implements View.OnClickListener{



    public boolean CheckInfo (CharSequence target,CharSequence password) {
        return !(target == null || password == null) && password.length() > 6 &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public RegisterProcess() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_process, container, false);
        getDialog().setCanceledOnTouchOutside(false);
        Button register =  view.findViewById(R.id.RegisterButton);
        register.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case(R.id.RegisterButton):

                EditText mail =  (EditText) getDialog().findViewById(R.id.EmailRegister);
                EditText password =(EditText) getDialog().findViewById(R.id.PasswordRegister);
                EditText control =(EditText) getDialog().findViewById(R.id.PasswordControl);
                String controls = control.getText().toString();
                String email = mail.getText().toString();
                String passwords = password.getText().toString();
                if(email.length()>0 & passwords.length()>0 & control.length()>0 &
                        Objects.equals(passwords, controls)){
                    if (CheckInfo(email,passwords)) {
                        createAccount(email,passwords);
                    }
                    else{
                        TextView errorBlock = getDialog().findViewById(R.id.ErrorR);
                        errorBlock.setText(R.string.wrongInput);
                        errorBlock.setTextColor(getResources().getColor(R.color.error));
                    }
                }else{
                    TextView errorBlock = getDialog().findViewById(R.id.ErrorR);
                    errorBlock.setText(R.string.error_empty_fields);
                    errorBlock.setTextColor(getResources().getColor(R.color.error));
                }

                break;
        }
    }

    public void createAccount(final String email, final String Passwords){
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, Passwords)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            checkLogin(email,Passwords);
                            StartPersonalize();
                            // Sign in success, update UI with the signed-in user's information


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
    public void checkLogin(String email, String Passwords){
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, Passwords)
                .addOnCompleteListener( getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                        } else {
                            // If sign in fails, display a message to the user.
                            TextView errorBlock = getDialog().findViewById(R.id.ErrorR);
                            errorBlock.setText(R.string.loginFailed);
                            errorBlock.setTextColor(getResources().getColor(R.color.error));
                        }

                    }
                });
    }
    public void StartPersonalize(){
        getDialog().dismiss();
        FragmentManager fm = getFragmentManager();
        Userinfo fragment = new Userinfo();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.Frame_layout, fragment);
        ft.commit();

    }
}
