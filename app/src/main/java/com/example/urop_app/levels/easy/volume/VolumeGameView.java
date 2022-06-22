package com.example.urop_app.levels.easy.volume;

import android.content.Context;
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


public class VolumeGameView extends SurfaceView implements SurfaceHolder.Callback {

    //Setting up required classes by the this class
    private VolumeGameLoop volumeGameLoop;
    private Context mContext;

    //Setting up the background
    private Bitmap mainBackground;

    //Cup objects
    private Block cupBlock;
    private Point cupPoint;



    public VolumeGameView(Context context) {
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        mContext = context;

        mainBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg01);


        //Setting up the game loop
        volumeGameLoop = new VolumeGameLoop(this, surfaceHolder);


        cupPoint = new Point(200,200);
        cupBlock = new Block(new Rect(0,0,50,50), Color.argb(100,255,0,0),cupPoint);



        setFocusable(true);
    }

    //CUSTOM METHODS

    //
    private void containerVolume(){

    }



    //ORIGINAL METHODS
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        cupBlock.draw(canvas);



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