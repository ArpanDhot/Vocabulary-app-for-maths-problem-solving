package com.example.urop_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.urop_app.levels.levelOne.axis.AxisTwo;
import com.example.urop_app.levels.levelOne.increase.IncreaseTwo;
import com.example.urop_app.levels.levelOne.ratio.RatioTwo;
import com.example.urop_app.levels.levelOne.volume.VolumeTwo;
import com.example.urop_app.levels.levelThree.perimeter.PerimeterTwo;
import com.example.urop_app.levels.levelThree.symmetrical.SymmetricalTwo;
import com.example.urop_app.levels.levelTwo.estimate.EstimateTwo;
import com.example.urop_app.levels.levelTwo.intersects.IntersectsTwo;
import com.example.urop_app.levels.levelTwo.reflection.ReflectionTwo;
import com.example.urop_app.levels.levelTwo.sequence.SequenceTwo;
import com.google.android.material.transition.MaterialSharedAxis;

public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);

        //Creating a delay for splash screen
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                // Do something after 5s = 5000ms
                Intent intent = new Intent(getApplicationContext(), AxisTwo.class);
                startActivity(intent);
            }
        }, 0);

    }
}