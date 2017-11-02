package com.example.vincent.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

public class MrPotatoHead extends AppCompatActivity {
    CheckBox Arms;
    CheckBox Eyes;
    CheckBox Eyebrows;
    CheckBox Ears;
    CheckBox Nose;
    CheckBox Shoes;
    CheckBox Mustache;
    CheckBox Mouth;
    CheckBox Glasses;
    CheckBox Hat;
    public void onCheckboxClicked(View view) {
        boolean check = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.Arms:
                if (check) {
                    ImageView image = (ImageView) findViewById(R.id.imageArms);
                    image.setVisibility(View.VISIBLE);
                    break;
                }
                else {
                    ImageView image = (ImageView) findViewById(R.id.imageArms);
                    image.setVisibility(View.INVISIBLE);
                    break;
                }
            case R.id.Ears:
                if (check) {
                    ImageView image = (ImageView) findViewById(R.id.imageEars);
                    image.setVisibility(View.VISIBLE);
                    break;
                }
                else {
                    ImageView image = (ImageView) findViewById(R.id.imageEars);
                    image.setVisibility(View.INVISIBLE);
                    break;
                }
            case R.id.Eyebrows:
                if (check) {
                    ImageView image = (ImageView) findViewById(R.id.imageEyebrows);
                    image.setVisibility(View.VISIBLE);
                    break;
                }
                else {
                    ImageView image = (ImageView) findViewById(R.id.imageEyebrows);
                    image.setVisibility(View.INVISIBLE);
                    break;
                }
            case R.id.Eyes:
                if (check) {
                    ImageView image = (ImageView) findViewById(R.id.imageEyes);
                    image.setVisibility(View.VISIBLE);
                    break;
                }
                else {
                    ImageView image = (ImageView) findViewById(R.id.imageEyes);
                    image.setVisibility(View.INVISIBLE);
                    break;
                }
            case R.id.Glasses:
                if (check) {
                    ImageView image = (ImageView) findViewById(R.id.imageGlasses);
                    image.setVisibility(View.VISIBLE);
                    break;
                }
                else {
                    ImageView image = (ImageView) findViewById(R.id.imageGlasses);
                    image.setVisibility(View.INVISIBLE);
                    break;
                }
            case R.id.Hat:
                if (check) {
                    ImageView image = (ImageView) findViewById(R.id.imageHat);
                    image.setVisibility(View.VISIBLE);
                    break;
                }
                else {
                    ImageView image = (ImageView) findViewById(R.id.imageHat);
                    image.setVisibility(View.INVISIBLE);
                    break;
                }
            case R.id.Mouth:
                if (check) {
                    ImageView image = (ImageView) findViewById(R.id.imageMouth);
                    image.setVisibility(View.VISIBLE);
                    break;
                }
                else {
                    ImageView image = (ImageView) findViewById(R.id.imageMouth);
                    image.setVisibility(View.INVISIBLE);
                    break;
                }
            case R.id.Mustache:
                if (check) {
                    ImageView image = (ImageView) findViewById(R.id.imageMustache);
                    image.setVisibility(View.VISIBLE);
                    break;
                }
                else {
                    ImageView image = (ImageView) findViewById(R.id.imageMustache);
                    image.setVisibility(View.INVISIBLE);
                    break;
                }
            case R.id.Nose:
                if (check) {
                    ImageView image = (ImageView) findViewById(R.id.imageNose);
                    image.setVisibility(View.VISIBLE);
                    break;
                }
                else {
                    ImageView image = (ImageView) findViewById(R.id.imageNose);
                    image.setVisibility(View.INVISIBLE);
                    break;
                }
            case R.id.Shoes:
                if (check) {
                    ImageView image = (ImageView) findViewById(R.id.imageShoes);
                    image.setVisibility(View.VISIBLE);
                    break;
                }
                else {
                    ImageView image = (ImageView) findViewById(R.id.imageShoes);
                    image.setVisibility(View.INVISIBLE);
                    break;
                }
        }

    }
    public void onSaveInstanceState(Bundle Outstate){
        super.onSaveInstanceState(Outstate);
        Outstate.putBoolean("Arms",Arms.isChecked());
        Outstate.putBoolean("Ears",Ears.isChecked());
        Outstate.putBoolean("Eyebrows",Eyebrows.isChecked());
        Outstate.putBoolean("Eyes",Eyes.isChecked());
        Outstate.putBoolean("Glasses",Glasses.isChecked());
        Outstate.putBoolean("Hat",Hat.isChecked());
        Outstate.putBoolean("Mouth",Mouth.isChecked());
        Outstate.putBoolean("Mustache",Mustache.isChecked());
        Outstate.putBoolean("Nose",Nose.isChecked());
        Outstate.putBoolean("Shoes",Shoes.isChecked());

    }
    public void onRestoreInstanceState(Bundle Instate){
        Boolean ArmsR = Instate.getBoolean("Arms");
        Boolean EarsR = Instate.getBoolean("Ears");
        Boolean EyesR = Instate.getBoolean("Eyes");
        Boolean EyebrowsR = Instate.getBoolean("Eyebrows");
        Boolean GlassesR = Instate.getBoolean("Glasses");
        Boolean HatR = Instate.getBoolean("Hat");
        Boolean MouthR = Instate.getBoolean("Mouth");
        Boolean MustacheR = Instate.getBoolean("Mustache");
        Boolean NoseR = Instate.getBoolean("Nose");
        Boolean ShoesR = Instate.getBoolean("Shoes");
        if(ArmsR){
            ImageView image = (ImageView) findViewById(R.id.imageArms);
            image.setVisibility(View.VISIBLE);
        }
        if(EarsR){
            ImageView image = (ImageView) findViewById(R.id.imageEars);
            image.setVisibility(View.VISIBLE);

        }
        if(EyesR){
            ImageView image = (ImageView) findViewById(R.id.imageEyes);
            image.setVisibility(View.VISIBLE);
        }
        if(EyebrowsR){
            ImageView image = (ImageView) findViewById(R.id.imageEyebrows);
            image.setVisibility(View.VISIBLE);
        }
        if(GlassesR){
            ImageView image = (ImageView) findViewById(R.id.imageGlasses);
            image.setVisibility(View.VISIBLE);
        }
        if(HatR){
            ImageView image = (ImageView) findViewById(R.id.imageHat);
            image.setVisibility(View.VISIBLE);
        }
        if(MouthR){
            ImageView image = (ImageView) findViewById(R.id.imageMouth);
            image.setVisibility(View.VISIBLE);
        }
        if(MustacheR){
            ImageView image = (ImageView) findViewById(R.id.imageMustache);
            image.setVisibility(View.VISIBLE);
        }
        if(NoseR){
            ImageView image = (ImageView) findViewById(R.id.imageNose);
            image.setVisibility(View.VISIBLE);
        }
        if(ShoesR){
            ImageView image = (ImageView) findViewById(R.id.imageShoes);
            image.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mr_potato_head);
    }

}
