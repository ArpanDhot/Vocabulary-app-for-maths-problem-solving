package com.example.urop_app.levels.levelTwo.intersects;

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
import com.example.urop_app.gameObjects.Block;
import com.example.urop_app.gameObjects.Characters;

import java.util.LinkedList;


public class IntersectsGameView extends SurfaceView implements SurfaceHolder.Callback {


    private IntersectsGameLoop intersectsGameLoop;
    private Context mContext;
    private Point pointY, pointX, pointMonsterOne, pointMonsterTwo, pointDraw;
    private LinkedList<Block> blocksPath = new LinkedList<>();
    private LinkedList<Block> blocksDraw = new LinkedList<>();
    private boolean intersectCheck[] = new boolean[40];
    private Characters monsterOne, monsterTwo;
    private int spriteRectSize = 50;

    private Bitmap mainBackground;
    private Bitmap signPost;

    //Text and score
    private Paint paintText;
    private int score = 0;
    private int countTrace = 0;


    public IntersectsGameView(Context context) {
        super(context);
        this.mContext = context;

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        mainBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg01);
        signPost = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.signpost), 300, 300, true);

        intersectsGameLoop = new IntersectsGameLoop(this, surfaceHolder);

        pointDraw = new Point(600, 600);
        pointY = new Point(1300, 200);
        pointX = new Point(300, 400);
        pointMonsterOne = new Point(1300, 200);
        pointMonsterTwo = new Point(300, 400);

        monsterOne = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), pointMonsterOne, context, 4, 1);
        monsterTwo = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), pointMonsterTwo, context, 4, 1);


        drawBoxes();


        for (int i = 0; i < 40; i++) {
            intersectCheck[i] = false;
        }

        paintText = new Paint();
        paintText.setColor(Color.BLACK);
        paintText.setStyle(Paint.Style.FILL);
        paintText.setTextSize(80);

        setFocusable(true);
    }

    //CUSTOM METHODS

    //Method to check the amount line drawn.
    private void traceCheck() {

        for (int i = 0; i < 40; i++) {
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

        score = (countTrace/4)*10;
        countTrace = 0;


    }


    //Monsters movement horizontal and vertical.
    private void monstersMovement() {

        if (monsterOne.getyPos() <= 1300) {
            monsterOne.update(0, 3);
        }

        if (monsterTwo.getxPos() <= 2250) {
            monsterTwo.update(3, 0);
        }


    }

    //Drawing the boxes
    private void drawBoxes() {

        //Drawing horizontal box
        for (int i = 0; i < 22; i++) {
            blocksPath.add(new Block(new Rect(0, 0, 50, 50), Color.argb(30, 255, 0, 0), pointY));
            pointY.set(pointY.x, pointY.y + 50);
        }

        //Drawing vertical box
        for (int i = 0; i < 40; i++) {
            blocksPath.add(new Block(new Rect(0, 0, 50, 50), Color.argb(30, 255, 255, 0), pointX));
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
        canvas.drawBitmap(signPost, 800, 800, null);

        //Blocks monster path
        for (Block block : blocksPath) {
            block.draw(canvas);
        }

        System.out.println(score);
        canvas.drawText(score+"%",880, 930,paintText);

        //Try catch is required if there are a lot of objects to be built
        try {
            for (Block block : blocksDraw) {
                block.draw(canvas);
            }
        } catch (Exception e) {
            System.out.println(e);
        }


        canvas.drawRect(monsterOne.getRectangle(),paintText);

        monsterOne.draw(canvas);
        monsterTwo.draw(canvas);


    }

    public void update() {

        try {
            traceCheck();
        } catch (Exception e) {

        }

        monstersMovement();


        // Draw Game over if the player is dead
        if (score == 100) {

            //Pausing the game loop
            Intent intent = new Intent(mContext, IntersectsThree.class);
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
        if (intersectsGameLoop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            intersectsGameLoop = new IntersectsGameLoop(this, surfaceHolder);
        }
        intersectsGameLoop.startLoop();
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
        intersectsGameLoop.stopLoop();

    }
}


