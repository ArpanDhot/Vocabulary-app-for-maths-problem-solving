package com.example.urop_app.levels.levelOne.volume;

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
import com.example.urop_app.levels.levelTwo.intersects.IntersectsTwo;

import java.util.ArrayList;


public class VolumeGameView extends SurfaceView implements SurfaceHolder.Callback {

    //Setting up required classes by the this class
    private VolumeGameLoop volumeGameLoop;
    private Context mContext;

    //Setting up the background
    private Bitmap mainBackground;

    //Cup one (example) objects
    private Block cupOneBlock;
    private Point cupOnePoint;
    //Cup two (user interact) objects
    private Block cupTwoBlock;
    private Point cupTwoPoint;

    //Water one (example) objects
    private Block waterOneBlock;
    private Point waterOnePoint;
    //Water two (user interact) objects
    private Block waterTwoBlock;
    private Point waterTwoPoint;
    //Water three (user interact) objects
    private Block waterThreeBlock;
    private Point waterThreePoint;

    //Monsters to place
    private Point monsterPointOne;
    private ArrayList<Characters> monstersOne = new ArrayList<>();
    private int spriteRectSize = 50;
    private boolean intersectCheck[] = new boolean[7];
    private int sumIntersect = 0;

    //Monster static
    private Point monsterPointTwo;
    private ArrayList<Characters> monstersTwo = new ArrayList<>();

    public VolumeGameView(Context context) {
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        mContext = context;

        mainBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg03);

        //Setting up the game loop
        volumeGameLoop = new VolumeGameLoop(this, surfaceHolder);

        //Setting up the objects for the cups
        cupOnePoint = new Point(900, 1100);
        cupOneBlock = new Block(new Rect(0, 0, 400, 600), Color.argb(200, 220, 220, 220), cupOnePoint);
        cupTwoPoint = new Point(1700, 1100);
        cupTwoBlock = new Block(new Rect(0, 0, 400, 600), Color.argb(200, 220, 220, 220), cupTwoPoint);

        //Setting up the objects for the water
        waterOnePoint = new Point(900, 1100);
        waterOneBlock = new Block(new Rect(0, 0, 350, 550), Color.argb(170, 135, 206, 235), waterOnePoint);
        waterTwoPoint = new Point(1700, 1235);
        waterTwoBlock = new Block(new Rect(0, 0, 350, 275), Color.argb(170, 135, 206, 235), waterTwoPoint);
        waterThreePoint = new Point(1700, 1235);
        waterThreeBlock = new Block(new Rect(0, 0, 350, 275), Color.argb(170, 135, 206, 235), waterThreePoint);

        //Creating the first block object to avoid to have any index issues
        monsterPointOne = new Point(600, 600);
        monstersOne.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 1));
        for (int i = 0; i < 7; i++) {
            intersectCheck[i] = false;
        }

        //Creating objects of the static monster
        monsterPointTwo = new Point(0,0);
        monstersTwo.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo, getContext(), 8, 1));
        monsterPointTwo.set(0,0);
        monstersTwo.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo, getContext(), 8, 1));
        monsterPointTwo.set(0,0);
        monstersTwo.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo, getContext(), 8, 1));
        monsterPointTwo.set(0,0);
        monstersTwo.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo, getContext(), 8, 1));
        monsterPointTwo.set(0,0);
        monstersTwo.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo, getContext(), 8, 1));

        setFocusable(true);
    }

    //CUSTOM METHODS

    //This method contains the logic for monster intersect and rect moving (water level increasing)
    private void containerVolume() {

        //Logic for when the monster touched and the water moves
        //The for each did not work because I need the index of the monster I am on. In order to determine if the monster has intersected with water and the new monster has been added
        for (int i = 0; i < monstersOne.size(); i++) {
            if (Rect.intersects(waterTwoBlock.getRectangle(), monstersOne.get(i).getRectangle())) {
                //Checking if the intersectCheck boolean is false. It means that the monster has not intersected and new monster has not been added to the linked list
                if (intersectCheck[i] == false) {
                    //Creating adding new monster
                    // Making the intersectCheck boolean true. Meaning that new object has been added
                    monsterPointOne.set(600, 600);
                    if (monstersOne.size() < 7) {
                        monstersOne.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 1));
                    }
                    intersectCheck[i] = true;
                    //Storing the intersections of the water with the monster
                    sumIntersect++;
                    //Moving the Rect to increase the water level
                    for (int ii = 0; ii < 50; ii++) {
                        //Condition will block the rect to go outside the grey container
                        if (waterTwoBlock.getyPos() >= 965) {
                            waterTwoBlock.update(0, -1);
                        }
                    }

                }
            }
        }

        if (sumIntersect == 6) {
            Intent intent = new Intent(mContext, IntersectsTwo.class);
            mContext.startActivity(intent);
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

        cupOneBlock.draw(canvas);
        cupTwoBlock.draw(canvas);

        waterOneBlock.draw(canvas);

        for (Characters characters : monstersOne) {
            characters.draw(canvas);
        }

        cupTwoBlock.draw(canvas);
        waterTwoBlock.draw(canvas);
        waterThreeBlock.draw(canvas);

    }

    public void update() {

        containerVolume();


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // Handle user input touch event actions
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:

                monstersOne.get(monstersOne.size() - 1).movement(event);

                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("Game.java", "surfaceCreated()");
        if (volumeGameLoop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            volumeGameLoop = new VolumeGameLoop(this, surfaceHolder);
        }
        volumeGameLoop.startLoop();
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
        volumeGameLoop.stopLoop();

    }
}