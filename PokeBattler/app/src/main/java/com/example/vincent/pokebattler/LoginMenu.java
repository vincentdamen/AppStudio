package com.example.vincent.pokebattler;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginMenu extends Fragment implements View.OnClickListener{

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

    public void openDialog(boolean Register) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (Register) {
            RegisterProcess fragment = new RegisterProcess();
            fragment.show(ft, "dialog");

        } else {
            LoginProcess fragment = new LoginProcess();
            fragment.show(ft, "dialog");
        }
    }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_login_menu, container, false);
            Button login = (Button) view.findViewById(R.id.Login);
            Button register = (Button) view.findViewById(R.id.Register);
            login.setOnClickListener(this);
            register.setOnClickListener(this);
            return view;
        }
}
