package com.example.vincent.restaurantrevisited;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.showOrder:
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                OrderFragment fragment = new OrderFragment();
                fragment.show(ft, "dialog");
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        CategoriesFragment fragment = new CategoriesFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragment, "categories");
        ft.commit();
    }
    public void Clear(View view){
        RestoDatabase db = RestoDatabase.getInstance(this);
        db.Clear();
        Context context = getApplicationContext();
        CharSequence text = "Your order has been deleted";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        // cracky way
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        OrderFragment fragment = new OrderFragment();
        fragment.show(ft, "dialog");
    }

}
