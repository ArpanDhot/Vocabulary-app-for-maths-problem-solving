package com.example.urop_app.levels.easy.intersects;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;



public class IntersectsGameView extends SurfaceView implements SurfaceHolder.Callback {


    private IntersectsGameLoop intersectsGameLoop;

    private Context mContext;

    int health =500;
    private Rect rectangle;
    private int color;
    int x=100;
    int y=100;


    public IntersectsGameView(Context context) {
        super(context);
        this.mContext = context;

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        intersectsGameLoop = new IntersectsGameLoop(this, surfaceHolder);



        //
        rectangle= new Rect(100, 100, 200, 200);
        color = Color.rgb(255, 0, 0);



        setFocusable(true);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);


    }

    public void update() {
        rectangle.set(x - rectangle.width() / 2, y - rectangle.height()/2, x + rectangle.width() / 2, y + rectangle.height()/2);

    x++;
    y++;
    health--;
    System.out.println(health);

        // Draw Game over if the player is dead
        if (health <= 0) {

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
            case MotionEvent.ACTION_POINTER_DOWN:

            case MotionEvent.ACTION_MOVE:


            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:

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


