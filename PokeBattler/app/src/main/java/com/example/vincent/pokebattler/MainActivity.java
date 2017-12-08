package com.example.vincent.pokebattler;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_play:
                    FragmentManager fm = getSupportFragmentManager();
                    StartingFragment fragment = new StartingFragment();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.Fragment_container, fragment);
                    ft.commit();
                    return true;
                case R.id.navigation_highscores:
                    return true;
                case R.id.navigation_settings:
                    FragmentManager fm1 = getSupportFragmentManager();
                    BattleScreen fragment1 = new BattleScreen();
                    FragmentTransaction ft1 = fm1.beginTransaction();
                    ft1.replace(R.id.Fragment_container, fragment1);
                    ft1.commit();
                    return true;
                case R.id.navigation_pokedex:

                    FragmentManager fm2 = getSupportFragmentManager();
                    PokeDex fragment2 = new PokeDex();
                    FragmentTransaction ft2 = fm2.beginTransaction();
                    ft2.replace(R.id.Fragment_container, fragment2);
                    ft2.commit();
                    return true;
                case R.id.navigation_train:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        StartingFragment fragment = new StartingFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.Fragment_container, fragment);
        ft.commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
