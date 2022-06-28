package com.example.urop_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import com.example.urop_app.levels.levelTwo.intersects.IntersectsOne;

public class Menu extends AppCompatActivity {

    private Button easyLevel;
    private Button mediumLevel;
    private Button hardLevel;
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

        //Assigning button var to button
        easyLevel = findViewById(R.id.easyLevelButton);

        //Setting up the button to do x when the button is pressed
        easyLevel.setOnClickListener(e->{

            //Moving to the Menu activity
            //Inorder to intent from a class in a package you must import the exact class
            Intent intent = new Intent(getApplicationContext(), IntersectsOne.class);
            startActivity(intent);
        });

    }
}