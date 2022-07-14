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
        } else if (soundTrackNumber == 3) {
            soundLoad = MediaPlayer.create(context,R.raw.volumeone); //assigning the track to the MediaPlayer
        } else if (soundTrackNumber == 4) {
            soundLoad = MediaPlayer.create(context,R.raw.volumetwo); //assigning the track to the MediaPlayer
        } else if (soundTrackNumber == 5) {
            soundLoad = MediaPlayer.create(context,R.raw.ratioone); //assigning the track to the MediaPlayer
        } else if (soundTrackNumber == 6) {
            soundLoad = MediaPlayer.create(context,R.raw.ratiotwo); //assigning the track to the MediaPlayer
        } else if (soundTrackNumber == 7) {
            soundLoad = MediaPlayer.create(context,R.raw.axisone); //assigning the track to the MediaPlayer
        } else if (soundTrackNumber == 8) {
            soundLoad = MediaPlayer.create(context,R.raw.axistwo); //assigning the track to the MediaPlayer
        } else if (soundTrackNumber == 9) {
            soundLoad = MediaPlayer.create(context,R.raw.increasetwo); //assigning the track to the MediaPlayer
        } else if (soundTrackNumber == 10 ) {
            soundLoad = MediaPlayer.create(context,R.raw.increasetwo); //assigning the track to the MediaPlayer
        } else if (soundTrackNumber == 11) {
            soundLoad = MediaPlayer.create(context,R.raw.increasetwo); //assigning the track to the MediaPlayer
        } else if (soundTrackNumber == 12) {
            soundLoad = MediaPlayer.create(context,R.raw.increasetwo); //assigning the track to the MediaPlayer
        } else if (soundTrackNumber == 13) {
            soundLoad = MediaPlayer.create(context,R.raw.increasetwo); //assigning the track to the MediaPlayer
        } else if (soundTrackNumber == 14) {
            soundLoad = MediaPlayer.create(context,R.raw.increasetwo); //assigning the track to the MediaPlayer
        } else if (soundTrackNumber == 15) {
            soundLoad = MediaPlayer.create(context,R.raw.increasetwo); //assigning the track to the MediaPlayer
        } else if (soundTrackNumber == 16) {
            soundLoad = MediaPlayer.create(context,R.raw.increasetwo); //assigning the track to the MediaPlayer
        } else if (soundTrackNumber == 17) {
            soundLoad = MediaPlayer.create(context,R.raw.increasetwo); //assigning the track to the MediaPlayer
        } else if (soundTrackNumber == 18) {
            soundLoad = MediaPlayer.create(context,R.raw.increasetwo); //assigning the track to the MediaPlayer
        } else if (soundTrackNumber == 19) {
            soundLoad = MediaPlayer.create(context,R.raw.increasetwo); //assigning the track to the MediaPlayer
        } else if (soundTrackNumber == 20) {
            soundLoad = MediaPlayer.create(context,R.raw.increasetwo); //assigning the track to the MediaPlayer
        } else if (soundTrackNumber == 21) {
            soundLoad = MediaPlayer.create(context,R.raw.increasetwo); //assigning the track to the MediaPlayer
        } else if (soundTrackNumber == 22) {
            soundLoad = MediaPlayer.create(context,R.raw.increasetwo); //assigning the track to the MediaPlayer
        } else if (soundTrackNumber == 23) {
            soundLoad = MediaPlayer.create(context,R.raw.increasetwo); //assigning the track to the MediaPlayer
        } else if (soundTrackNumber == 24) {
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
