package com.example.urop_app.levels.levelThree.translate;

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
import com.example.urop_app.levels.levelOne.axis.AxisGameLoop;
import com.example.urop_app.levels.levelThree.perimeter.PerimeterOne;
import com.example.urop_app.levels.levelTwo.intersects.IntersectsTwo;

import java.util.ArrayList;

public class TranslateGameView extends SurfaceView implements SurfaceHolder.Callback {

    //Setting up required classes by the this class
    private TranslateGameLoop translateGameLoop;
    private Context mContext;

    //Banner and voiceover
    private Sound sound;
    private Banner banner;
    boolean soundBoolean = true;

    //Setting up the background
    private Bitmap mainBackground;

    //Monster place block
    private Point placeBlockPointOne;
    private Block[] placeBlockOne = new Block[8];
    private Point placeBlockPointTwo;
    private Block[] placeBlockTwo = new Block[100];

    //Monsters to place
    private Point monsterPointOne;
    private ArrayList<Characters> monstersOne = new ArrayList<>();
    private int spriteRectSize = 50;


    public TranslateGameView(Context context) {
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        mContext = context;

        mainBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg09);

        //Setting up the game loop
        translateGameLoop = new TranslateGameLoop(this, surfaceHolder);

        //Monster place blocks one to be placed
        //First - Second - Third - Fourth Row
        int index = 0;
        placeBlockPointTwo = new Point(190, 1015);
        for (int i = 0; i < 8; i++) {

            placeBlockTwo[index] = new Block(new Rect(0, 0, 300, 150), Color.argb(70, 255, 255, 0), placeBlockPointTwo);
            placeBlockPointTwo.set(placeBlockPointTwo.x + 310, placeBlockPointTwo.y);
            index++;

        }

        placeBlockPointTwo.set(190, 850);
        for (int i = 0; i < 8; i++) {

            placeBlockTwo[index] = new Block(new Rect(0, 0, 300, 150), Color.argb(70, 0, 255, 0), placeBlockPointTwo);
            placeBlockPointTwo.set(placeBlockPointTwo.x + 310, placeBlockPointTwo.y);
            index++;

        }

        placeBlockPointTwo.set(190, 685);
        for (int i = 0; i < 8; i++) {

            placeBlockTwo[index] = new Block(new Rect(0, 0, 300, 150), Color.argb(70, 0, 0, 255), placeBlockPointTwo);
            placeBlockPointTwo.set(placeBlockPointTwo.x + 310, placeBlockPointTwo.y);
            index++;

        }

        placeBlockPointOne = new Point(190, 1180);
        for (int i = 0; i < 8; i++) {
            placeBlockOne[i] = new Block(new Rect(0, 0, 300, 150), Color.argb(70, 255, 0, 0), placeBlockPointOne);
            placeBlockPointOne.set(placeBlockPointOne.x + 310, placeBlockPointOne.y);
        }

        //Creating the first block object to avoid to have any index issues
        //Example monster
        monsterPointOne = new Point(480, 1185);
        monstersOne.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 0, 3));
        monsterPointOne.set(185, 1019);
        monstersOne.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 0, 3));

        monsterPointOne = new Point(1108, 1188);
        monstersOne.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 0, 4));
        monsterPointOne.set(803, 679);
        monstersOne.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 0, 4));

        monsterPointOne = new Point(2045, 1179);
        monstersOne.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 0, 7));
        monsterPointOne.set(2045, 1179);
        monstersOne.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 7));

        setFocusable(true);
    }

    //CUSTOM METHODS
    //This method contains the logic of monster intersecting with the block and spawning new monster
    //This method also record how many boxes do have a monster in it
    private void blockMonsterIntersect() {

        if (Rect.intersects(placeBlockTwo[12].getRectangle(), monstersOne.get(monstersOne.size() - 1).getRectangle())) {

            Intent intent = new Intent(mContext, PerimeterOne.class);
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

        for (Block block : placeBlockOne) {
            block.draw(canvas);
        }


        //Try catch is required if there are a lot of objects to be built
        try {
            for (Block block : placeBlockTwo) {
                block.draw(canvas);
            }
        } catch (Exception e) {

        }

        //Printing a of the monster objects
        for (Characters monster : monstersOne) {
            monster.draw(canvas);
        }

        //loading up the sound and the banner
        if (soundBoolean) {
            soundBoolean = false;

            sound = new Sound(getContext(), 18);
            banner = new Banner(getContext(), 18);

        }

        //drawing the banner until the voiceover is on
        if (sound.getSoundLoad().isPlaying()) {
            banner.draw(canvas);
        }
    }

    public void update() {

        blockMonsterIntersect();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //Only letting the user play once the voice over is done
        if (!sound.getSoundLoad().isPlaying()) {
            // Handle user input touch event actions
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    monstersOne.get(monstersOne.size() - 1).movement(event);
                    System.out.println("X:" + event.getX() + " | Y:" + event.getY());
                    return true;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("Game.java", "surfaceCreated()");
        if (translateGameLoop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            translateGameLoop = new TranslateGameLoop(this, surfaceHolder);
        }
        translateGameLoop.startLoop();
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
        translateGameLoop.stopLoop();

    }
}