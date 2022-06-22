package com.example.urop_app.gameObjects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Block extends Position implements GameObject {
    private Paint paint;
    private Rect rectangle;
    private Point point;

    private int color;


    public Block(Rect rectangle, int color, Point point) {
        this.rectangle = rectangle;
        this.color = color;
        this.setxPos(point.x);
        this.setyPos(point.y);

        paint = new Paint();

        rectangle.set(point.x - rectangle.width() / 2, point.y - rectangle.height()/2, point.x + rectangle.width() / 2, point.y + rectangle.height()/2);
    }


    @Override
    public void draw(Canvas canvas) {
        //Setting up the rectangle color and drawing it

        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }

    @Override
    public void update() {

    }

    /**
     * I just had to (getXPos() - rectangle.width() / 2)+velX  if I do it the way I code in java fx
     */
    public void update(int velX, int velY) {
        //Setting up the points of the rectangle shape. This will draw the four points of the rectangle
        //left, top, right, bottom
        this.setxPos(this.getxPos() + velX);
        this.setyPos(this.getyPos() + velY);
        rectangle.set((this.getxPos() - rectangle.width() / 2) + velX, (this.getyPos() - rectangle.height() / 2) + velY, (this.getxPos() + rectangle.width() / 2) + velX, (this.getyPos() + rectangle.height() / 2) + velY);
    }

    public Rect getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rect rectangle) {
        this.rectangle = rectangle;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
