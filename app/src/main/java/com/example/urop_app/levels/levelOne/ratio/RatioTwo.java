package com.example.urop_app.levels.levelOne.ratio;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class RatioTwo extends AppCompatActivity {
    private RatioGameView ratioGameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity.java", "onCreate()");
        super.onCreate(savedInstanceState);

        //This is hiding the hide the OS bar
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        //To make the windows full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // Set content view to game, so that objects in the Game class can be rendered to the screen
        ratioGameView = new RatioGameView(this);
        setContentView(ratioGameView);


    }

    @Override
    protected void onStart() {
        Log.d("MainActivity.java", "onStart()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("MainActivity.java", "onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("MainActivity.java", "onPause()");
        ratioGameView.pause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("MainActivity.java", "onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("MainActivity.java", "onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        // Comment out "super.onBackPressed()" to disable button
        //super.onBackPressed();
    }
}