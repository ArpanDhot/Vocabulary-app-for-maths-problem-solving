package com.example.urop_app.levels.easy.intersects;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.urop_app.gameObjects.Characters;


public class IntersectsGameView extends SurfaceView implements SurfaceHolder.Callback {


    private IntersectsGameLoop intersectsGameLoop;
    private Context mContext;
    private Characters characters;
    private Point point;

    int health = 500;


    public IntersectsGameView(Context context) {
        super(context);
        this.mContext = context;

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        intersectsGameLoop = new IntersectsGameLoop(this, surfaceHolder);


        point = new Point(300, 300);
        characters = new Characters(new Rect(0, 0, 50, 50), point, mContext);


        setFocusable(true);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        characters.draw(canvas);


    }

    public void update() {

        //TODO change its change the way it intents.
        // Draw Game over if the player is dead
        if (health <= 0) {

            //Pausing the game loop
            Intent intent = new Intent(mContext, IntersectsThree.class);
            mContext.startActivity(intent);

        }


        //health--;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // Handle user input touch event actions
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                //Calling the object movement method
                characters.movement(event);
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


