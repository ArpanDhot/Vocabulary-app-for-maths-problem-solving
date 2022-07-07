package com.example.urop_app.levels.levelThree.perpendicular;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.SurfaceView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.urop_app.R;
import com.example.urop_app.gameObjects.Block;
import com.example.urop_app.gameObjects.Characters;
import com.example.urop_app.levels.levelThree.SubMenuThree;
import com.example.urop_app.levels.levelTwo.SubMenuTwo;
import com.example.urop_app.levels.levelTwo.intersects.IntersectsOne;

import java.util.LinkedList;

public class PerpendicularGameView extends SurfaceView implements SurfaceHolder.Callback {


    private PerpendicularGameLoop perpendicularGameLoop;
    private Context mContext;
    private Point pointY, pointX, pointMonsterOne, pointMonsterTwo, pointDraw;
    private LinkedList<Block> blocksPath = new LinkedList<>();
    private LinkedList<Block> blocksDraw = new LinkedList<>();
    private boolean intersectCheck[] = new boolean[38];
    private Characters monsterOne, monsterTwo;
    private int spriteRectSize = 50;

    private Bitmap mainBackground;
    private Bitmap signPost;
    private Bitmap house;

    //Text and score
    private int score = 0;
    private int countTrace = 0;


    public PerpendicularGameView(Context context) {
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        mContext = context;

        mainBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg08);
        signPost = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.signpost), 300, 300, true);
        house = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.house), 450, 400, true);


        //Setting up the game loop
        perpendicularGameLoop = new PerpendicularGameLoop(this, surfaceHolder);


        pointDraw = new Point(600, 600);
        pointY = new Point(1610, 420);
        pointX = new Point(760, 1420);
        pointMonsterOne = new Point(1610, 410);
        pointMonsterTwo = new Point(1515, 1380);

        monsterOne = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), pointMonsterOne, context, 4, 1);
        monsterTwo = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), pointMonsterTwo, context, 4, 2);

        drawBoxes();


        for (int i = 0; i < 38; i++) {
            intersectCheck[i] = false;
        }


        setFocusable(true);
    }

    //CUSTOM METHODS
//Method to check the amount line drawn.
    private void traceCheck() {

        for (int i = 0; i < 38; i++) {
            for (Block block : blocksDraw) {
                if (Rect.intersects(blocksPath.get(i).getRectangle(), block.getRectangle())) {
                    intersectCheck[i] = true;
                }

            }
        }

        for (Boolean bool : intersectCheck) {
            if (bool) {
                countTrace++;
            }
        }

        score = countTrace;
        countTrace = 0;
    }


    //Monsters movement horizontal and vertical.
    private void monstersMovement() {

        //Logic movement auto monster one
        if (monsterOne.getyPos() <= 1415 && monsterOne.getxPos() == 1610) {
            monsterOne.update(0, 6);
        }

        if (monsterOne.getyPos() >= 1415 && monsterOne.getxPos() >= 757) {
            monsterOne.update(-4, 0);
        }

        if (monsterOne.getyPos() >= 1260 && monsterOne.getxPos() == 754) {
            monsterOne.update(0, -4);
        }

        //Logic movement auto monster two
        if (monsterOne.getyPos() >= 1360) {
            if (monsterTwo.getyPos() <= 1516 && monsterTwo.getxPos() >= 757) {
                monsterTwo.update(-4, 0);
            }

            if (monsterTwo.getyPos() >= 1260 && monsterTwo.getxPos() == 755) {
                monsterTwo.update(0, -4);
            }
        }

    }

    //Drawing the boxes
    private void drawBoxes() {

        //Drawing horizontal box
        for (int i = 0; i < 20; i++) {
            blocksPath.add(new Block(new Rect(0, 0, 50, 50), Color.argb(200, 255, 0, 0), pointY));
            pointY.set(pointY.x, pointY.y + 50);
        }

        //Drawing vertical box
        for (int i = 0; i < 18; i++) {
            blocksPath.add(new Block(new Rect(0, 0, 50, 50), Color.argb(200, 255, 0, 0), pointX));
            pointX.set(pointX.x + 50, pointX.y);
        }

    }


    //ORIGINAL METHODS
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        //This method allows to scale the image size
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(mainBackground, 2600, 1500, true);
        //Drawing the Bitmap on to the canvas
        canvas.drawBitmap(resizedBitmap, 0, 0, null);

        //Sing post
        // canvas.drawBitmap(signPost, 800, 800, null);

        //Blocks monster path
        for (Block block : blocksPath) {
            block.draw(canvas);
        }

        //Try catch is required if there are a lot of objects to be built
        try {
            for (Block block : blocksDraw) {
                block.draw(canvas);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        monsterOne.draw(canvas);
        monsterTwo.draw(canvas);

        //House
        canvas.drawBitmap(house, 600, 1000, null);
    }

    public void update() {
        try {
            traceCheck();
        } catch (Exception e) {

        }

        monstersMovement();


        // Draw Game over if the player is dead
        if (score == 38) {

            //Pausing the game loop
            Intent intent = new Intent(mContext, SubMenuThree.class);
            mContext.startActivity(intent);

        }


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

                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("Game.java", "surfaceCreated()");
        if (perpendicularGameLoop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            perpendicularGameLoop = new PerpendicularGameLoop(this, surfaceHolder);
        }
        perpendicularGameLoop.startLoop();
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
        perpendicularGameLoop.stopLoop();

    }
}