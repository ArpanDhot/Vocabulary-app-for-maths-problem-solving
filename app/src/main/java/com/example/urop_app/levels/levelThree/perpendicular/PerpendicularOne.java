package com.example.urop_app.levels.levelThree.perpendicular;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.WindowManager;
import android.widget.Button;

import com.example.urop_app.R;
import com.example.urop_app.gameObjects.Sound;
import com.example.urop_app.levels.levelOne.increase.IncreaseTwo;
import com.example.urop_app.levels.levelOne.volume.VolumeTwo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class PerpendicularOne extends AppCompatActivity {

    private FloatingActionButton readButton;
    private Button nextButton;
    private Sound sound;

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
        setContentView(R.layout.activity_perpendicular_one);

        //Assigning button var to button
        readButton = findViewById(R.id.readButton);

        //Setting up the button to do x when the button is pressed
        readButton.setOnClickListener(e -> {
            sound = new Sound(getApplicationContext(), 1);

        });

        //Assigning button var to button
        nextButton = findViewById(R.id.intersectsOneNextButton);

        nextButton.setOnClickListener(e -> {

            //To stop the speech before we move to the next activity
            sound.getSoundLoad().pause();
            sound.getSoundLoad().stop();


            //Moving to the Menu activity
            //Inorder to intent from a class in a package you must import the exact class
            Intent intent = new Intent(getApplicationContext(), IncreaseTwo.class);
            startActivity(intent);
        });

    }


}