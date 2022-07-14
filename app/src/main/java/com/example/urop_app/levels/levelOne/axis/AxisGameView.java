package com.example.urop_app.levels.levelOne.axis;

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
import com.example.urop_app.levels.levelOne.SubMenuOne;
import com.example.urop_app.levels.levelTwo.intersects.IntersectsTwo;

import java.util.ArrayList;

public class AxisGameView extends SurfaceView implements SurfaceHolder.Callback {


    //Setting up required classes by the this class
    private AxisGameLoop axisGameLoop;
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
    private Block[] placeBlockTwo = new Block[17];

    //Monsters to place
    private Point monsterPointOne;
    private ArrayList<Characters> monstersOne = new ArrayList<>();
    private int spriteRectSize = 50;
    private boolean intersectCheck[] = new boolean[8];
    private int sumIntersect = 0;


    public AxisGameView(Context context) {
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        mContext = context;

        mainBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg09);


        //Setting up the game loop
        axisGameLoop = new AxisGameLoop(this, surfaceHolder);


        //Monster place blocks one to be placed
        //Third row
        placeBlockPointOne = new Point(190, 1180);
        for (int i = 0; i < 8; i++) {
            placeBlockOne[i] = new Block(new Rect(0, 0, 300, 150), Color.argb(70, 218, 165, 32), placeBlockPointOne);
            placeBlockPointOne.set(placeBlockPointOne.x + 310, placeBlockPointOne.y);
        }
        //First and second  row
        int index = 0;
        placeBlockPointTwo = new Point(190, 1015);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                placeBlockTwo[index] = new Block(new Rect(0, 0, 300, 150), Color.argb(70, 255, 255, 255), placeBlockPointTwo);
                placeBlockPointTwo.set(placeBlockPointTwo.x + 310, placeBlockPointTwo.y);
                index++;
            }

            placeBlockPointTwo.set(190, placeBlockPointOne.y - 330);
        }


        //Creating the first block object to avoid to have any index issues
        monsterPointOne = new Point(600, 600);
        monstersOne.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 1));
        for (int i = 0; i < 8; i++) {
            intersectCheck[i] = false;
        }


        setFocusable(true);
    }

    //CUSTOM METHODS

    //This method contains the logic of monster intersecting with the block and spawning new monster
    //This method also record how many boxes do have a monster in it
    private void blockMonsterIntersect() {
        for (int i = 0; i < 8; i++) {
            for (Characters characters : monstersOne) {
                if (Rect.intersects(placeBlockOne[i].getRectangle(), characters.getRectangle())) {
                    //Checking if the intersectCheck boolean is false. It means that the monster has not intersected and new monster has not been added to the linked list
                    if (intersectCheck[i] == false) {
                        //Creating adding new monster
                        // Making the intersectCheck boolean true. Meaning that new object has been added
                        if (monstersOne.size() < 8) {
                            monstersOne.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 1));
                        }

                        intersectCheck[i] = true;
                        //Storing the intersections of the block in index i;
                        sumIntersect++;
                    }
                }
            }
        }
        if (sumIntersect == 8) {
            Intent intent = new Intent(mContext, SubMenuOne.class);
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
            System.out.println(e);
        }

        //Printing a of the monster objects
        for (Characters monster : monstersOne) {
            monster.draw(canvas);
        }

        //loading up the sound and the banner
        if (soundBoolean) {
            soundBoolean = false;

            sound = new Sound(getContext(), 8);
            banner = new Banner(getContext(), 8);

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

                    return true;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("Game.java", "surfaceCreated()");
        if (axisGameLoop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            axisGameLoop = new AxisGameLoop(this, surfaceHolder);
        }
        axisGameLoop.startLoop();
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
        axisGameLoop.stopLoop();

    }
}