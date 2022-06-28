package com.example.urop_app.levels.levelTwo.reflection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.urop_app.R;
import com.example.urop_app.gameObjects.Characters;
import com.example.urop_app.levels.levelOne.axis.AxisGameLoop;

public class ReflectionGameView extends SurfaceView implements SurfaceHolder.Callback {

    //Setting up required classes by the this class
    private ReflectionGameLoop reflectionGameLoop;
    private Context mContext;

    //Setting up the background
    private Bitmap mainBackground;
    private Bitmap mirror[] = new Bitmap[2];

    //Monsters to place
    private Point monsterPointOne;
    private Characters monstersOne[] = new Characters[4];
    private int spriteRectSize = 50;




    public ReflectionGameView(Context context) {
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        mContext = context;

        mainBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg06);
        mirror[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.mirror), 300, 350, true);
        mirror[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.mirror), 300, 350, true);

        //Setting up the game loop
        reflectionGameLoop = new ReflectionGameLoop(this, surfaceHolder);


        //Creating the first block object to avoid to have any index issues
        monsterPointOne = new Point(1270, 1270);
        for (int i = 0; i < 4; i++) {
            //This condition allows me to load two different sprites at different index start

                monstersOne[i] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 7);

        }



        setFocusable(true);
    }

    //CUSTOM METHODS




    //ORIGINAL METHODS
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        //This method allows to scale the image size
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(mainBackground, 2560, 1500, true);
        //Drawing the Bitmap on to the canvas
        canvas.drawBitmap(resizedBitmap, 0, 0, null);

        //Mirror
        canvas.drawBitmap(mirror[0], 820, 1060, null);
        canvas.drawBitmap(mirror[1], 820, 1060, null);



    }

    public void update() {



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // Handle user input touch event actions
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:

            case MotionEvent.ACTION_UP:

                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("Game.java", "surfaceCreated()");
        if (reflectionGameLoop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            reflectionGameLoop = new ReflectionGameLoop(this, surfaceHolder);
        }
        reflectionGameLoop.startLoop();
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
        reflectionGameLoop.stopLoop();

    }
}