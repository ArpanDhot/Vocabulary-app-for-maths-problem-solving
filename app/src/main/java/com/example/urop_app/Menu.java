package com.example.urop_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import com.example.urop_app.levels.levelOne.increase.IncreaseOne;
import com.example.urop_app.levels.levelThree.translate.TranslateOne;
import com.example.urop_app.levels.levelTwo.reflection.ReflectionOne;

public class Menu extends AppCompatActivity {

    private Button levelOne;
    private Button levelTwo;
    private Button levelThree;
    private Button settings;
    private Button help;


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
        setContentView(R.layout.activity_menu);

        //LEVEL ONE
        //Assigning button var to button
        levelOne = findViewById(R.id.easyLevelButton);

        //Setting up the button to do x when the button is pressed
        levelOne.setOnClickListener(e->{

            //Moving to the Menu activity
            //Inorder to intent from a class in a package you must import the exact class
            Intent intent = new Intent(getApplicationContext(), IncreaseOne.class);
            startActivity(intent);
        });

        //LEVEL TWO
        //Assigning button var to button
        levelTwo = findViewById(R.id.mediumLevelButton);

        //Setting up the button to do x when the button is pressed
        levelTwo.setOnClickListener(e->{

            //Moving to the Menu activity
            //Inorder to intent from a class in a package you must import the exact class
            Intent intent = new Intent(getApplicationContext(), ReflectionOne.class);
            startActivity(intent);
        });

        //LEVEL THREE
        //Assigning button var to button
        levelThree = findViewById(R.id.hardLevelButton);

        //Setting up the button to do x when the button is pressed
        levelThree.setOnClickListener(e->{

            //Moving to the Menu activity
            //Inorder to intent from a class in a package you must import the exact class
            Intent intent = new Intent(getApplicationContext(), TranslateOne.class);
            startActivity(intent);
        });



        //SETTINGS
        //Assigning button var to button
        settings = findViewById(R.id.settingsButton);

        //Setting up the button to do x when the button is pressed
        settings.setOnClickListener(e->{

            //Moving to the Menu activity
            //Inorder to intent from a class in a package you must import the exact class
            Intent intent = new Intent(getApplicationContext(), Settings.class);
            startActivity(intent);
        });

        //SETTINGS
        //Assigning button var to button
        help = findViewById(R.id.help);

        //Setting up the button to do x when the button is pressed
        help.setOnClickListener(e->{

            //Moving to the Menu activity
            //Inorder to intent from a class in a package you must import the exact class
            Intent intent = new Intent(getApplicationContext(), Help.class);
            startActivity(intent);
        });


    }
}