package com.neonudrafloy.oldpeoplehelper;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Instructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        Glide.with(getApplicationContext()).load(R.drawable.instruction0).into((ImageView) findViewById(R.id.imageView2));
        Glide.with(getApplicationContext()).load(R.drawable.instruction1).into((ImageView) findViewById(R.id.imageView3));
    }
}