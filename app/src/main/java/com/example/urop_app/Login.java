package com.example.urop_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {


    private EditText textInput;
    private Button submitButton;


    /**
     * @param savedInstanceState
     */
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
        setContentView(R.layout.activity_login);

        //Setting up the database
        SharedPreferences sharedPreferences = getSharedPreferences("userID", 0); //0 makes the data private mode
        SharedPreferences.Editor editor = sharedPreferences.edit(); //Inorder to write into the database need to use the subclass editor

        //Assigning textView to plain text
        textInput = findViewById(R.id.idInput);

        //Assigning button var to button
        submitButton = findViewById(R.id.loginButton);

        //Setting up the button to do x when the button is pressed
        submitButton.setOnClickListener(e -> {
            //Assigning the inputted value to the and storing it.
            //TODO parse the inputted text make sure that the ID is valid
            System.out.println(textInput.getText().toString());
            //Storing the inputted ID
            editor.putString("userId", textInput.getText().toString());
            editor.commit();

            //Moving to the Menu activity
            Intent intent = new Intent(getApplicationContext(), Menu.class);
            startActivity(intent);
        });
    }
}