package com.example.urop_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //This is hiding the hide the OS bar
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        //To make the windows full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Setting up the activity
        setContentView(R.layout.activity_help);
    }
}