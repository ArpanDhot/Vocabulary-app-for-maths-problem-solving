package com.example.urop_app.levels.levelOne.increase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.WindowManager;
import android.widget.Button;

import com.example.urop_app.R;
import com.example.urop_app.gameObjects.Sound;
import com.example.urop_app.levels.levelOne.volume.VolumeTwo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class IncreaseOne extends AppCompatActivity {

    private TextToSpeech textToSpeech;
    private FloatingActionButton readButton;
    private Button nextButton;

    private String text = "Hello";

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
        setContentView(R.layout.activity_increase_one);

        //Assigning button var to button
        readButton = findViewById(R.id.readButton);

        //Calling the textSpeech method
        textSpeech();

        //Assigning button var to button
        nextButton = findViewById(R.id.intersectsOneNextButton);

        nextButton.setOnClickListener(e -> {

            //To stop the speech before we move to the next activity
            textToSpeech.stop();
            textToSpeech.shutdown();


            //Moving to the Menu activity
            //Inorder to intent from a class in a package you must import the exact class
            Intent intent = new Intent(getApplicationContext(), IncreaseTwo.class);
            startActivity(intent);
        });

    }

    private void textSpeech() {

        //Assigning new instance of "TextToSpeech" to the variable "textToSpeech".

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                //Setting up the language of the reader.
                textToSpeech.setLanguage(Locale.GERMAN);

                //Setting up the speech pitch
                textToSpeech.setPitch(0.8f);

                //Setting up the speech rate
                textToSpeech.setSpeechRate(0.6f);
            }
        });

        //Setting up the button to do x when the button is pressed
        readButton.setOnClickListener(e -> {

           textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        });

    }
}