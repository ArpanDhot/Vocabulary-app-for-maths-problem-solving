package com.example.urop_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.urop_app.levels.easy.axis.AxisTwo;
import com.example.urop_app.levels.easy.increase.IncreaseTwo;
import com.example.urop_app.levels.easy.volume.VolumeTwo;

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
//                // Do something after 5s = 5000ms
//                Intent intent = new Intent(getApplicationContext(), Login.class);
//                startActivity(intent);

                // Do something after 5s = 5000ms
                Intent intent = new Intent(getApplicationContext(), AxisTwo.class);
                startActivity(intent);
            }
        }, 50);

    }
}