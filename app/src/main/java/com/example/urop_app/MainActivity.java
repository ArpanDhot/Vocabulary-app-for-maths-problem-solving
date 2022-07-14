package com.example.urop_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.urop_app.levels.levelOne.axis.AxisOne;
import com.example.urop_app.levels.levelOne.axis.AxisTwo;
import com.example.urop_app.levels.levelOne.increase.IncreaseOne;
import com.example.urop_app.levels.levelOne.increase.IncreaseTwo;
import com.example.urop_app.levels.levelOne.ratio.RatioOne;
import com.example.urop_app.levels.levelOne.ratio.RatioTwo;
import com.example.urop_app.levels.levelOne.volume.VolumeOne;
import com.example.urop_app.levels.levelOne.volume.VolumeTwo;
import com.example.urop_app.levels.levelThree.perimeter.PerimeterOne;
import com.example.urop_app.levels.levelThree.perimeter.PerimeterTwo;
import com.example.urop_app.levels.levelThree.perpendicular.PerpendicularTwo;
import com.example.urop_app.levels.levelThree.symmetrical.SymmetricalTwo;
import com.example.urop_app.levels.levelThree.translate.TranslateGameView;
import com.example.urop_app.levels.levelThree.translate.TranslateOne;
import com.example.urop_app.levels.levelThree.translate.TranslateTwo;
import com.example.urop_app.levels.levelTwo.estimate.EstimateTwo;
import com.example.urop_app.levels.levelTwo.intersects.IntersectsOne;
import com.example.urop_app.levels.levelTwo.intersects.IntersectsTwo;
import com.example.urop_app.levels.levelTwo.reflection.ReflectionOne;
import com.example.urop_app.levels.levelTwo.reflection.ReflectionTwo;
import com.example.urop_app.levels.levelTwo.sequence.SequenceOne;
import com.example.urop_app.levels.levelTwo.sequence.SequenceTwo;
import com.google.android.material.transition.MaterialSharedAxis;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progressBarStatus = 0;
    private final Handler progressBarHandler = new Handler();

    private long fileSize = 0;

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
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(getApplicationContext(), SequenceOne.class);
        startActivity(intent);

//        progressBar = findViewById(R.id.gameLoadingBar);
//        progressBarEngine();

    }

    /**
     * Method to run the progress bar
     */
    public void progressBarEngine() {


        // prepare for a progress bar dialog
        progressBar.setProgress(0);
        progressBar.setMax(100);

        //reset progress bar status
        progressBarStatus = 0;

        //reset file size
        fileSize = 0;

        new Thread(new Runnable() {
            public void run() {
                while (progressBarStatus < 100) {

                    // process some tasks
                    progressBarStatus = progressBarStatusUpdate();

                    // your computer is too fast, sleep 1 second
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Update the progress bar
                    progressBarHandler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressBarStatus); //Getting this value updated by calling the doSomeTasks() method
                        }
                    });
                }

                //EXECUTE THE CODE YOU WANT
                if (progressBarStatus >= 100) {
                    Intent intent = new Intent(getApplicationContext(),Menu.class);
                    startActivity(intent);
                }
            }
        }).start();

    }

    /**
     * Thread running count when one of the condition phases reached it will return a decimal value that is going to get to the progress bar status
     * @return
     */
    public int progressBarStatusUpdate() {
        while (fileSize <= 1000000) {
            fileSize++;
            if (fileSize == 100000) {
                return 10;
            } else if (fileSize == 200000) {
                return 20;
            } else if (fileSize == 300000) {
                return 30;
            }else if (fileSize == 400000) {
                return 40;
            }else if (fileSize == 500000) {
                return 50;
            }else if (fileSize == 600000) {
                return 60;
            }else if (fileSize == 700000) {
                return 80;
            }
        }
        return 100;
    }


}