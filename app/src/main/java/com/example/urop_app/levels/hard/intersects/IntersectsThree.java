package com.example.urop_app.levels.hard.intersects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.urop_app.R;

public class IntersectsThree extends AppCompatActivity {

    private EditText textInput;
    private Button submitButton;

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
        setContentView(R.layout.activity_intersects_three);

        //Assigning textView to plain text
        textInput = findViewById(R.id.intersectsThreeInputString);

        //Assigning button var to button
        submitButton = findViewById(R.id.loginButton);



    }
}