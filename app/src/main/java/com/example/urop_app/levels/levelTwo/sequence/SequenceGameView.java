package com.example.urop_app.levels.levelTwo.sequence;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
import com.example.urop_app.levels.levelOne.increase.IncreaseGameLoop;
import com.example.urop_app.levels.levelTwo.SubMenuTwo;
import com.example.urop_app.levels.levelTwo.estimate.EstimateTwo;
import com.example.urop_app.levels.levelTwo.intersects.IntersectsTwo;

import java.util.ArrayList;

public class SequenceGameView extends SurfaceView implements SurfaceHolder.Callback {


    private SequenceGameLoop sequenceGameLoop;
    private Bitmap mainBackground;

    private Context mContext;

    //Banner and voiceover
    private Sound sound;
    private Banner banner;
    boolean soundBoolean = true;

    private int spriteRectSize = 50;

    //Monster place block
    private Point placeBlockPoint;
    private Block[] placeBlock = new Block[3];

    //BLock place intersect true
    private boolean[] intersectMonsterPlace = new boolean[3];


    //Monsters auto
    private Point[] monsterPointTwo = new Point[5];
    private Characters[] monstersTwo = new Characters[5];

    //Monsters to place
    private Point monsterPointOne;
    private ArrayList<Characters> monstersOne = new ArrayList<>();


    public SequenceGameView(Context context) {
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        mContext = context;

        //Loading the background
        mainBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg07);

        //Setting up the game loop
        sequenceGameLoop = new SequenceGameLoop(this, surfaceHolder);


        //Creating the first block object to avoid to have any index issues
        monsterPointOne = new Point(950, 1000);
        monstersOne.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 6));

        //Monster place blocks
        placeBlockPoint = new Point(1680, 1350); //1800,1350
        for (int i = 0; i < 3; i++) {
            placeBlock[i] = new Block(new Rect(0, 0, 150, 150), Color.argb(70, 255, 255, 255), placeBlockPoint);
            placeBlockPoint.set(placeBlockPoint.x + 270, placeBlockPoint.y);
        }

        //Setting up the auto monster
        monsterPointTwo[0] = new Point(-50, 1350);
        monsterPointTwo[1] = new Point(-50, 1350);
        monsterPointTwo[2] = new Point(-50, 1350);
        monsterPointTwo[3] = new Point(-250, 1350);
        monsterPointTwo[4] = new Point(-250, 1350);

        monstersTwo[0] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo[0], getContext(), 2, 10);
        monstersTwo[1] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo[1], getContext(), 2, 6);
        monstersTwo[2] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo[2], getContext(), 2, 6);
        monstersTwo[3] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo[3], getContext(), 2, 6);
        monstersTwo[4] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo[4], getContext(), 2, 10);


        setFocusable(true);
    }

    //CUSTOM METHODS
    //Auto monster movement method
    private void monsterAutoMove() {


        if (monstersTwo[0].getxPos() <= 300) {
            monstersTwo[0].update(10, 0);
        }

        if (monstersTwo[1].getxPos() <= 560) {
            monstersTwo[1].update(10, 0);
        }

        if (monstersTwo[2].getxPos() <= 830) {
            monstersTwo[2].update(10, 0);
        }
        if (monstersTwo[3].getxPos() <= 1110) {
            monstersTwo[3].update(10, 0);
        }
        if (monstersTwo[4].getxPos() <= 1390) {
            monstersTwo[4].update(10, 0);
        }

    }

    //Method to check if the monsters are placed in the rectangles
    private void monsterPlaceCheck() {

        for (Characters characters : monstersOne) {

            if (Rect.intersects(characters.getRectangle(), placeBlock[0].getRectangle()) && intersectMonsterPlace[0] != true) {

                intersectMonsterPlace[0] = true;
                monstersOne.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 6));
            }
            if (Rect.intersects(characters.getRectangle(), placeBlock[1].getRectangle()) && intersectMonsterPlace[1] != true) {
                intersectMonsterPlace[1] = true;
                monstersOne.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 6));
            }
            if (Rect.intersects(characters.getRectangle(), placeBlock[2].getRectangle()) && intersectMonsterPlace[2] != true) {
                intersectMonsterPlace[2] = true;
                monstersOne.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 6));
            }
        }


        if ((intersectMonsterPlace[0] == true) && (intersectMonsterPlace[1] == true) && (intersectMonsterPlace[2] == true)) {
            Intent intent = new Intent(mContext, EstimateTwo.class);
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
        if (sequenceGameLoop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            sequenceGameLoop = new SequenceGameLoop(this, surfaceHolder);
        }
        sequenceGameLoop.startLoop();
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
        sequenceGameLoop.stopLoop();

    }
}
