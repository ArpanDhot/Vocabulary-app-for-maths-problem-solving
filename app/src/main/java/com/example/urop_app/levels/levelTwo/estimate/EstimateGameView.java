package com.example.urop_app.levels.levelTwo.estimate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.urop_app.R;
import com.example.urop_app.gameObjects.Banner;
import com.example.urop_app.gameObjects.Block;
import com.example.urop_app.gameObjects.Characters;
import com.example.urop_app.gameObjects.Sound;
import com.example.urop_app.levels.levelTwo.SubMenuTwo;
import com.example.urop_app.levels.levelTwo.intersects.IntersectsTwo;
import com.example.urop_app.levels.levelTwo.reflection.ReflectionGameLoop;

public class EstimateGameView extends SurfaceView implements SurfaceHolder.Callback {

    //Setting up required classes by the this class
    private EstimateGameLoop estimateGameLoop;
    private Context mContext;

    //Banner and voiceover
    private Sound sound;
    private Banner banner;
    boolean soundBoolean = true;

    //Setting up the background
    private Bitmap mainBackground;

    //Monsters to place
    private Point monsterPointOne;
    private Characters monstersOne[] = new Characters[3];
    private int spriteRectSize = 50;

    //Block trigger
    private Point blockTriggerPointOne;
    private Block blockTrigger;




    public EstimateGameView(Context context) {
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        mContext = context;

        mainBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg10);

        //Setting up the game loop
        estimateGameLoop = new EstimateGameLoop(this, surfaceHolder);

        //Block trigger
        blockTriggerPointOne = new Point(1320, 1025);
        blockTrigger = new Block(new Rect(0, 0, 150, 60), Color.argb(70, 255, 255, 255), blockTriggerPointOne);

        //Creating the first block object to avoid to have any index issues
        monsterPointOne = new Point(1566, 406);
        monstersOne[0] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 2); //User control monster
        monsterPointOne.set(200, 1025);
        monstersOne[1] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 2);
        monsterPointOne.set(2350, 1025);
        monstersOne[2] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 7);

        setFocusable(true);
    }

    //CUSTOM METHODS
    private  void monsterMidPointIntersect(){

        if(Rect.intersects(monstersOne[0].getRectangle(),blockTrigger.getRectangle())){
            Intent intent = new Intent(mContext, SubMenuTwo.class);
            mContext.startActivity(intent);
        }

    }



    //ORIGINAL METHODS
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        //This method allows to scale the image size
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(mainBackground, 2560, 1500, true);
        //Drawing the Bitmap on to the canvas
        canvas.drawBitmap(resizedBitmap, 0, 0, null);


        //Monster
        for(Characters characters: monstersOne){
            characters.draw(canvas);
        }

        //loading up the sound and the banner
        if (soundBoolean) {
            soundBoolean = false;

            sound = new Sound(getContext(), 2);
            banner = new Banner(getContext(), 2);

        }

        //drawing the banner until the voiceover is on
        if (sound.getSoundLoad().isPlaying()) {
            banner.draw(canvas);
        }


    }

    public void update() {

        monsterMidPointIntersect();


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //Only letting the user play once the voice over is done
        if (!sound.getSoundLoad().isPlaying()) {
            // Handle user input touch event actions
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:

                case MotionEvent.ACTION_MOVE:
                    //Logic for the monster main
                    monstersOne[0].movement(event);

                    return true;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("Game.java", "surfaceCreated()");
        if (estimateGameLoop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            estimateGameLoop = new EstimateGameLoop(this, surfaceHolder);
        }
        estimateGameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("Game.java", "surfaceChanged()");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("Game.java", "surfaceDestroyed()");
    }


    public void pause() {
        estimateGameLoop.stopLoop();

    }
}