package com.example.urop_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;

public class Settings extends AppCompatActivity {


    private Switch aSwitch;
    private SeekBar seekBar;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setting up the database
        SharedPreferences sharedPreferences = getSharedPreferences("gameSettings",0); //0 makes the data private mode
        SharedPreferences.Editor editor = sharedPreferences.edit(); //Inorder to write into the database need to use the subclass editor

        //This is hiding the hide the OS bar
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        //To make the windows full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Setting up the activity
        setContentView(R.layout.activity_settings);


        //Setting up the views by their ids
        aSwitch= findViewById(R.id.switchMusic);
        seekBar= findViewById(R.id.volumeBarMusic);
        button= findViewById(R.id.defaultSettingsButton);


        aSwitch.setOnClickListener(e->{

            if(aSwitch.isChecked()){
                //Turned On
                editor.putBoolean("musicOnOff",true); //Creating an image of the data to store it in the database
            }else{
                //Turned Off
                editor.putBoolean("musicOnOff",false);
            }
            editor.commit(); //Saving the database
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                editor.putInt("musicVolume",i); //Creating an image of the data to store it in the database
                editor.commit(); //Saving the database
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        button.setOnClickListener(e->{
            editor.putBoolean("musicOnOff",false);
            editor.putInt("musicVolume",0);
            editor.commit(); //Saving the database
        });


    }
}