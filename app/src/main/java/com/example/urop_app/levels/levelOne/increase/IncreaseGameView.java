package com.example.urop_app.levels.levelOne.increase;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.urop_app.R;
import com.example.urop_app.gameObjects.Banner;
import com.example.urop_app.gameObjects.Block;
import com.example.urop_app.gameObjects.Characters;
import com.example.urop_app.gameObjects.Sound;
import com.example.urop_app.levels.levelOne.volume.VolumeOne;
import com.example.urop_app.levels.levelOne.volume.VolumeTwo;
import com.example.urop_app.levels.levelTwo.intersects.IntersectsTwo;

import java.util.ArrayList;

public class IncreaseGameView extends SurfaceView implements SurfaceHolder.Callback {


    private IncreaseGameLoop increaseGameLoop;
    private Bitmap mainBackground;

    private Context mContext;

    //Banner and voiceover
    private Sound sound;
    private Banner banner;
    boolean soundBoolean = true;

    private int spriteRectSize = 50;

    //Monster place block
    private Point placeBlockPoint;
    private Block[] placeBlock = new Block[4];

    //BLock place intersect true
    private boolean[] intersectMonsterPlace = new boolean[4];


    //Monsters auto
    private Point[] monsterPointTwo = new Point[6];
    private Characters[] monstersTwo = new Characters[6];

    //Monsters to place
    private Point monsterPointOne;
    private ArrayList<Characters> monstersOne = new ArrayList<>();



    public IncreaseGameView(Context context) {
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        mContext = context;

        //Loading the background
        mainBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg02);

        //Setting up the game loop
        increaseGameLoop = new IncreaseGameLoop(this, surfaceHolder);


        //Creating the first block object to avoid to have any index issues
        monsterPointOne = new Point(560, 500);
        monstersOne.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 5));

        //Monster place blocks
        placeBlockPoint = new Point(1800, 1350);
        for (int i = 0; i < 4; i++) {
            placeBlock[i] = new Block(new Rect(0, 0, 100, 100), Color.argb(150, 255, 255, 255), placeBlockPoint);
            placeBlockPoint.set(placeBlockPoint.x, placeBlockPoint.y - 110);
        }


        //Setting up the auto monster
        monsterPointTwo[0] = new Point(-50, 1350);
        monsterPointTwo[1] = new Point(-50, 1240);
        monsterPointTwo[2] = new Point(-50, 1130);
        monsterPointTwo[3] = new Point(-250, 1350);
        monsterPointTwo[4] = new Point(-250, 1240);
        monsterPointTwo[5] = new Point(-450, 1350);

        monstersTwo[0] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo[0], getContext(), 2, 5);
        monstersTwo[1] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo[1], getContext(), 2, 5);
        monstersTwo[2] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo[2], getContext(), 2, 5);
        monstersTwo[3] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo[3], getContext(), 2, 5);
        monstersTwo[4] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo[4], getContext(), 2, 5);
        monstersTwo[5] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo[5], getContext(), 2, 5);


        setFocusable(true);
    }

    //CUSTOM METHODS

    //Auto monster movement method
    private void monsterAutoMove() {


        if (monstersTwo[0].getxPos() <= 1300) {
            monstersTwo[0].update(monstersTwo[0].getSpeed(), 0);
            monstersTwo[1].update(monstersTwo[1].getSpeed(), 0);
            monstersTwo[2].update(monstersTwo[2].getSpeed(), 0);
        }

        if (monstersTwo[3].getxPos() <= 800) {
            monstersTwo[3].update(monstersTwo[3].getSpeed(), 0);
            monstersTwo[4].update(monstersTwo[4].getSpeed(), 0);
        }

        if (monstersTwo[5].getxPos() <= 400) {
            monstersTwo[5].update(monstersTwo[5].getSpeed(), 0);
        }

    }


    //Method to check if the monsters are placed in the rectangles
    private void monsterPlaceCheck() {

        //The var i is helping us to get the index of the monster
        int i=0;
        for (Characters characters : monstersOne) {

            if ((Rect.intersects(characters.getRectangle(), placeBlock[0].getRectangle()) && intersectMonsterPlace[0] ==false ) && i == 0) {
                intersectMonsterPlace[0] = true;
                monstersOne.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 5));
            }
            if ((Rect.intersects(characters.getRectangle(), placeBlock[1].getRectangle()) && intersectMonsterPlace[1] ==false) && i == 1) {
                intersectMonsterPlace[1] = true;
                monstersOne.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 5));
            }
            if ((Rect.intersects(characters.getRectangle(), placeBlock[2].getRectangle()) && intersectMonsterPlace[2] ==false) && i == 2) {
                intersectMonsterPlace[2] = true;
                monstersOne.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 5));
            }
            if ((Rect.intersects(characters.getRectangle(), placeBlock[3].getRectangle()) && intersectMonsterPlace[3] ==false) && i == 3) {
                intersectMonsterPlace[3] = true;
                monstersOne.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 5));
            }
            i++;
        }


        if ((intersectMonsterPlace[0] == true) && (intersectMonsterPlace[1] == true) && (intersectMonsterPlace[2] == true) && (intersectMonsterPlace[3] == true)) {
            Intent intent = new Intent(mContext, VolumeOne.class);
            mContext.startActivity(intent);
        }
    }


    //ORIGINAL METHODS
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        //This method allows to scale the image size
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(mainBackground, 2600, 1500, true);
        //drawing the Bitmap on to the canvas
        canvas.drawBitmap(resizedBitmap, 0, 0, null);

        for (Block block : placeBlock) {
            block.draw(canvas);
        }

        for (Characters monster : monstersTwo) {
            monster.draw(canvas);
        }

        //Printing a of the block objects
        for (Characters monster : monstersOne) {
            monster.draw(canvas);
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

        //
        monsterAutoMove();

        //
        monsterPlaceCheck();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //Only letting the user play once the voice over is done
        if (!sound.getSoundLoad().isPlaying()) {
            System.out.println("Trigger");
            // Handle user input touch event actions
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    monstersOne.get(monstersOne.size()-1).movement(event);

                    return true;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("Game.java", "surfaceCreated()");
        if (increaseGameLoop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            increaseGameLoop = new IncreaseGameLoop(this, surfaceHolder);
        }
        increaseGameLoop.startLoop();
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
        increaseGameLoop.stopLoop();

    }
}
