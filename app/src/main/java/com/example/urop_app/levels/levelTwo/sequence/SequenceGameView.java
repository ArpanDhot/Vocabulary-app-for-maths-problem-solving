package com.example.urop_app.levels.levelTwo.sequence;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SequenceGameView extends SurfaceView implements SurfaceHolder.Callback {


    private SequenceGameLoop sequenceGameLoop;

    private Context mContext;


    public SequenceGameView(Context context) {
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        mContext = context;


        //Setting up the game loop
        sequenceGameLoop = new SequenceGameLoop(this, surfaceHolder);


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
