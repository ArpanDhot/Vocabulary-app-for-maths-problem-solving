package com.example.urop_app.levels.levelThree.perpendicular;

import android.view.SurfaceView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class PerpendicularGameView extends SurfaceView implements SurfaceHolder.Callback {


    private PerpendicularGameLoop perpendicularGameLoop;

    private Context mContext;




    public PerpendicularGameView(Context context) {
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        mContext = context;


        //Setting up the game loop
        perpendicularGameLoop = new PerpendicularGameLoop(this, surfaceHolder);





        setFocusable(true);
    }

    //CUSTOM METHODS




    //ORIGINAL METHODS
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);




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