package com.example.urop_app.gameObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Characters extends Position implements GameObject {


    private Rect rectangle;
    private Context context;
    Paint myPaint = new Paint(); //TODO remove

    private int spriteCount; //It will vary character to character. Therefore, its value will be assigned in the switch.
    private double oldXPos = 0;
    private double oldYPos = 0;
    private int speed = 1;

    public Characters(Rect rectangle, Point point, Context context) {
        this.rectangle = rectangle;
        this.setxPos(point.x);
        this.setyPos(point.y);

        //TODO will need to change sprite loading into switch so i can load all the characters sprite from one

        this.context = context;
    }

    @Override
    public void draw(Canvas canvas) {

        myPaint.setColor(Color.rgb(255, 0, 255));
        myPaint.setStrokeWidth(10);
        canvas.drawRect(rectangle, myPaint);
    }

    @Override
    public void update() {

    }


    /**
     * I just had to (getXPos() - rectangle.width() / 2)+velX  if I do it the way I code in java fx
     */
    public void update(int velX, int velY) {
        this.setxPos(this.getxPos() + velX);
        this.setyPos(this.getyPos() + velY);
        rectangle.set((this.getxPos() - rectangle.width() / 2) + velX, (this.getyPos() - rectangle.height() / 2) + velY, (this.getxPos() + rectangle.width() / 2) + velX, (this.getyPos() + rectangle.height() / 2) + velY);
    }

    public void movement(MotionEvent event) {

        double yPos = event.getY();
        double xPos = event.getX();


        //Checking if the older values of the y-axis was greater and current value is smaller that means velocity must be subtracted and if its vice versa I must increase it
        if (yPos >= oldYPos) {
            this.setyVel(+speed);
        }
        if (yPos <= oldYPos) { //Checking if the player goes out of bound
            this.setyVel(-speed);
        }

        if (xPos >= oldXPos) {
            this.setxVel(+speed);
            //directionForSprite = +1;
        }
        if (xPos <= oldXPos) { //Checking if the player goes out of bound
            this.setxVel(-speed);
            //directionForSprite = -1;
        }

        //storing the older values to compare it in the conditions
        oldXPos = event.getX();
        oldYPos = event.getY();

        this.update(this.getxVel(), this.getyVel());
        this.setyVel(0);
        this.setxVel(0);
    }

    public Rect getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rect rectangle) {
        this.rectangle = rectangle;
    }
}
