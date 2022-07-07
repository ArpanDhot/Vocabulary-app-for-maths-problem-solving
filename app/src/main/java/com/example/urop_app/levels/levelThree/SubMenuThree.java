package com.example.urop_app.levels.levelThree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import com.example.urop_app.Menu;
import com.example.urop_app.R;
import com.example.urop_app.levels.levelOne.increase.IncreaseOne;
import com.example.urop_app.levels.levelTwo.estimate.EstimateTwo;
import com.example.urop_app.levels.levelTwo.reflection.ReflectionOne;

public class SubMenuThree extends AppCompatActivity {

    private Button nextLevel;
    private Button menu;


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
        setContentView(R.layout.activity_sub_menu_three);



        //Assigning button var to button
        nextLevel = findViewById(R.id.easyLevelButton);

        //Setting up the button to do x when the button is pressed
        nextLevel.setOnClickListener(e->{

            //Moving to the Menu activity
            //Inorder to intent from a class in a package you must import the exact class
            Intent intent = new Intent(getApplicationContext(), IncreaseOne.class);
            startActivity(intent);
        });

        //Assigning button var to button
        menu = findViewById(R.id.easyLevelButton);

        //Setting up the button to do x when the button is pressed
        menu.setOnClickListener(e->{

            //Moving to the Menu activity
            //Inorder to intent from a class in a package you must import the exact class
            Intent intent = new Intent(getApplicationContext(), Menu.class);
            startActivity(intent);
        });
    }
}