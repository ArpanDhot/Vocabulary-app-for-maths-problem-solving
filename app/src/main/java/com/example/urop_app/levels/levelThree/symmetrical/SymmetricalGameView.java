package com.example.urop_app.levels.levelThree.symmetrical;

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
import com.example.urop_app.gameObjects.Block;
import com.example.urop_app.gameObjects.Characters;

import java.util.LinkedList;

public class SymmetricalGameView extends SurfaceView implements SurfaceHolder.Callback {

    //Setting up required classes by the this class
    private SymmetricalGameLoop symmetricalGameLoop;
    private Context mContext;

    //Setting up the background
    private Bitmap mainBackground;

    //
    private Bitmap rock;

    //Monster place block
    private Point placeBlockPointOne;
    private Block[] placeBlockOne = new Block[10];
    private boolean intersectCheck[] = new boolean[10];

    //Draw
    private Point pointDraw;
    private LinkedList<Block> blocksDraw = new LinkedList<>();

    //Score
    private int score = 0;
    private int countTrace = 0;

    //Monster One
    private Point pointMonsterOne;
    private Characters monsterOne;
    private int spriteRectSize = 50;


    public SymmetricalGameView(Context context) {
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        mContext = context;

        mainBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg13);

        rock = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.rock), 530, 450, true);


        //Setting up the game loop
        symmetricalGameLoop = new SymmetricalGameLoop(this, surfaceHolder);

        //Draw point
        pointDraw = new Point(1250, 880);

        //First and second  row
        int index = 0;
        placeBlockPointOne = new Point(1260, 880);
        for (int i = 0; i < 10; i++) {
            placeBlockOne[index] = new Block(new Rect(0, 0, 50, 50), Color.argb(150, 255, 255, 255), placeBlockPointOne);

            index++;

            placeBlockPointOne.set(placeBlockPointOne.x , placeBlockPointOne.y+ 51);

        }


        for (int i = 0; i < 10; i++) {
            intersectCheck[i] = false;
        }

        //Monster
        pointMonsterOne = new Point(1110, 860);
        monsterOne = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), pointMonsterOne, context, 4, 1);


        setFocusable(true);
    }

    //CUSTOM METHODS
    //Method to check the amount line drawn.
    private void traceCheck() {

        try {
            for (int i = 0; i < 10; i++) {
                for (Block block : blocksDraw) {
                    if (Rect.intersects(placeBlockOne[i].getRectangle(), block.getRectangle())) {
                        intersectCheck[i] = true;
                    }

                }
            }
        } catch (Exception e) {
        }

        for (Boolean bool : intersectCheck) {
            if (bool) {
                countTrace++;
            }
        }

        score = countTrace;
        countTrace = 0;

        System.out.println(score);
        // Draw Game over if the player is dead
        if (score == 10) {

            //Pausing the game loop
            Intent intent = new Intent(mContext, IntersectsThree.class);
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

        canvas.drawBitmap(rock, 1025, 890, null);

            try {
                //Place Block
                for (Block block : placeBlockOne) {
                    block.draw(canvas);
                }

                //Draw Box
                for (Block block : blocksDraw) {
                    block.draw(canvas);
                }
            } catch (Exception e) {
                //System.out.println(e);
            }







        //Monster
        monsterOne.draw(canvas);

    }

    public void update() {

        traceCheck();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // Handle user input touch event actions
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //Checking if the monsters has crossed the paths
                //Checking if the boxes are overlapping
                //Setting a limit on user draw box
                pointDraw.set((int) event.getX(), (int) event.getY());
                blocksDraw.add(new Block(new Rect(0, 0, 50, 50), Color.rgb(255, 255, 255), pointDraw));

                pointMonsterOne.set((int) event.getX(), (int) event.getY());
                monsterOne.setxPos(pointMonsterOne.x);
                monsterOne.setyPos(pointMonsterOne.y);

                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("Game.java", "surfaceCreated()");
        if (symmetricalGameLoop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            symmetricalGameLoop = new SymmetricalGameLoop(this, surfaceHolder);
        }
        symmetricalGameLoop.startLoop();
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
        symmetricalGameLoop.stopLoop();

    }
}