package com.example.vincent.myapplication;

import android.support.v4.content.res.ConfigurationHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

public class MrPotatoHead extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mr_potato_head);
    }

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
        Outstate.putInt("Arms",findViewById(R.id.imageArms).getVisibility());
        Outstate.putInt("Ears",findViewById(R.id.imageEars).getVisibility());
        Outstate.putInt("Eyebrows",findViewById(R.id.imageEyebrows).getVisibility());
        Outstate.putInt("Eyes",findViewById(R.id.imageEyes).getVisibility());
        Outstate.putInt("Glasses",findViewById(R.id.imageGlasses).getVisibility());
        Outstate.putInt("Hat",findViewById(R.id.imageHat).getVisibility());
        Outstate.putInt("Mouth",findViewById(R.id.imageMouth).getVisibility());
        Outstate.putInt("Mustache",findViewById(R.id.imageMustache).getVisibility());
        Outstate.putInt("Nose",findViewById(R.id.imageNose).getVisibility());
        Outstate.putInt("Shoes",findViewById(R.id.imageShoes).getVisibility());

    }
    public void onRestoreInstanceState(Bundle Instate){
        Integer ArmsR = Instate.getInt("Arms");
        Integer EarsR = Instate.getInt("Ears");
        Integer EyesR = Instate.getInt("Eyes");
        Integer EyebrowsR = Instate.getInt("Eyebrows");
        Integer GlassesR = Instate.getInt("Glasses");
        Integer HatR = Instate.getInt("Hat");
        Integer MouthR = Instate.getInt("Mouth");
        Integer MustacheR = Instate.getInt("Mustache");
        Integer NoseR = Instate.getInt("Nose");
        Integer ShoesR = Instate.getInt("Shoes");
        if(ArmsR==0){
            ImageView image = (ImageView) findViewById(R.id.imageArms);
            CheckBox checkBox = (CheckBox) findViewById(R.id.Arms);
            checkBox.setChecked(!checkBox.isChecked());
            image.setVisibility(View.VISIBLE);
        }
        if(EarsR==0){
            ImageView image = (ImageView) findViewById(R.id.imageEars);
            CheckBox checkBox = (CheckBox) findViewById(R.id.Ears);
            checkBox.setChecked(!checkBox.isChecked());
            image.setVisibility(View.VISIBLE);

        }
        if(EyesR==0){
            ImageView image = (ImageView) findViewById(R.id.imageEyes);
            CheckBox checkBox = (CheckBox) findViewById(R.id.Eyes);
            checkBox.setChecked(!checkBox.isChecked());
            image.setVisibility(View.VISIBLE);
        }
        if(EyebrowsR==0){
            ImageView image = (ImageView) findViewById(R.id.imageEyebrows);
            CheckBox checkBox = (CheckBox) findViewById(R.id.Eyebrows);
            checkBox.setChecked(!checkBox.isChecked());
            image.setVisibility(View.VISIBLE);
        }
        if(GlassesR==0){
            ImageView image = (ImageView) findViewById(R.id.imageGlasses);
            CheckBox checkBox = (CheckBox) findViewById(R.id.Glasses);
            checkBox.setChecked(!checkBox.isChecked());
            image.setVisibility(View.VISIBLE);
        }
        if(HatR==0){
            ImageView image = (ImageView) findViewById(R.id.imageHat);
            CheckBox checkBox = (CheckBox) findViewById(R.id.Hat);
            checkBox.setChecked(!checkBox.isChecked());
            image.setVisibility(View.VISIBLE);
        }
        if(MouthR==0){
            ImageView image = (ImageView) findViewById(R.id.imageMouth);
            CheckBox checkBox = (CheckBox) findViewById(R.id.Mouth);
            checkBox.setChecked(!checkBox.isChecked());
            image.setVisibility(View.VISIBLE);
        }
        if(MustacheR==0){
            ImageView image = (ImageView) findViewById(R.id.imageMustache);
            CheckBox checkBox = (CheckBox) findViewById(R.id.Mustache);
            checkBox.setChecked(!checkBox.isChecked());
            image.setVisibility(View.VISIBLE);
        }
        if(NoseR==0){
            ImageView image = (ImageView) findViewById(R.id.imageNose);
            CheckBox checkBox = (CheckBox) findViewById(R.id.Nose);
            checkBox.setChecked(!checkBox.isChecked());
            image.setVisibility(View.VISIBLE);
        }
        if(ShoesR==0){
            ImageView image = (ImageView) findViewById(R.id.imageShoes);
            CheckBox checkBox = (CheckBox) findViewById(R.id.Shoes);
            checkBox.setChecked(!checkBox.isChecked());
            image.setVisibility(View.VISIBLE);
        }
    }



}
