package com.example.urop_app.gameObjects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Block implements GameObject {
    private Paint paint;
    private Rect rectangle;
    private Point point;

    private int color;


    public Block(Rect rectangle, int color, Point point) {
        this.rectangle = rectangle;
        this.color = color;
        this.point = point;

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
