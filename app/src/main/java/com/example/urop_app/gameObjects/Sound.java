package com.example.urop_app.gameObjects;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;

import com.example.urop_app.R;

public class Sound {

    //Context loading
    private Context context;

    //Settings
    private boolean musicOnOff;
    private int musicVolume;
    private float volume;

    //Setting shared pref
    private SharedPreferences sharedPreferences;

    //Sound loading
    private MediaPlayer soundLoad;


    public Sound(Context context, int soundTrackNumber){

        this.context = context;

        loadingSoundToVar(soundTrackNumber);

        loadingSoundSettings();

        starSound();

    }

    private void loadingSoundToVar(int soundTrackNumber){

        //Setting up the sound track
        if (soundTrackNumber == 1) {
            soundLoad = MediaPlayer.create(context,R.raw.increaseone); //assigning the track to the MediaPlayer
        } else if (soundTrackNumber == 2) {
            soundLoad = MediaPlayer.create(context,R.raw.increasetwo); //assigning the track to the MediaPlayer
        }

    }

    private void loadingSoundSettings(){
        //Instance of the music data
        sharedPreferences = context.getSharedPreferences("gameSettings", 0);
        //If the user has not initialised the data base. Therefore it will be returning nothing in that case we will require to have a default value.
        // In this instance we set it to true, therefore the music is going to be On by default.
        musicOnOff = sharedPreferences.getBoolean("musicOnOff", true);
        // In this instance we set it to 50, therefore the music volume is going to have a volume of 50.
        musicVolume = sharedPreferences.getInt("musicVolume", 50);

        int setVolume = musicVolume; //Choosing volume amount
        volume = (float) (1 - (Math.log(100 - setVolume) / Math.log(100))); //formula for int to volume conversion in form of float
    }



    private void starSound(){
        soundLoad.setVolume(volume, volume); //Setting up the volume . The range of the setVolume method is from 0.0f to 1.0f
        if (musicOnOff) {
            soundLoad.start();
        }
    }


    public MediaPlayer getSoundLoad() {
        return soundLoad;
    }

    public void setSoundLoad(MediaPlayer soundLoad) {
        this.soundLoad = soundLoad;
    }
}
