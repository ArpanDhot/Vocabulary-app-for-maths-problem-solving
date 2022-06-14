package com.example.urop_app.gameObjects;

import android.graphics.Canvas;
/**
 * This is the interface that can be implemented by using the key word "implements" after the class name.
 * What it will so it will force to implement these methods.
 * These methods are important to use in the objects that way they can be drawn as well as update according
 */

public interface GameObject {

    //update the shapes on the SurfaceView
    public void draw(Canvas canvas);

    //to automate the movement
    public void update();


}
