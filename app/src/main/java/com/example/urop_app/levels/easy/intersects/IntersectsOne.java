package com.example.urop_app.levels.easy.intersects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.WindowManager;

import com.example.urop_app.R;

import java.util.Locale;

public class IntersectsOne extends AppCompatActivity {

    private TextToSpeech textToSpeech;

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
        setContentView(R.layout.activity_intersects_one);

        //Calling the textSpeech method
        textSpeech();
    }

    private void textSpeech(){

        //Assigning new instance of "TextToSpeech" to the variable "textToSpeech".
        //Setting up the language of the reader.
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                textToSpeech.setLanguage(Locale.ENGLISH);
            }
        });


    }
}