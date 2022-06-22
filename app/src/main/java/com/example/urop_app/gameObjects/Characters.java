package com.example.urop_app.gameObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.urop_app.R;

//TODO align the rectangles with the sprites

public class Characters extends Position implements GameObject {


    private Rect rectangle;
    private Context context;
    private Bitmap spriteLoadLeft[] = new Bitmap[12];
    private Bitmap spriteLoadRight[] = new Bitmap[12];
    private int spriteWidth = 120 ,spriteHeight = 100;


    private int characterNumber = 1;
    private int spriteCount = 0; //It will vary character to character. Therefore, its value will be assigned in the switch.
    private int spriteIndex = 0;
    private double oldXPos = 0;
    private double oldYPos = 0;
    private int speed = 1;

    public Characters(Rect rectangle, Point point, Context context, int speed, int characterNumber) {
        this.rectangle = rectangle;
        this.setxPos(point.x);
        this.setyPos(point.y);
        this.speed = speed;
        this.characterNumber = characterNumber;
        this.context = context;

        //Setting up the points of the rectangle shape. This will draw the four points of the rectangle
        //left, top, right, bottom
        rectangle.set(point.x - rectangle.width() / 2, point.y - rectangle.height() / 2, point.x + rectangle.width() / 2, point.y + rectangle.height() / 2);


        if (characterNumber == 1) {

            spriteLoadLeft[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.angryl1), spriteWidth, spriteHeight, true);
            spriteLoadLeft[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.angryl2), spriteWidth, spriteHeight, true);
            spriteLoadLeft[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.angryl3), spriteWidth, spriteHeight, true);
            spriteLoadLeft[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.angryl4), spriteWidth, spriteHeight, true);
            spriteLoadLeft[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.angryl5), spriteWidth, spriteHeight, true);
            spriteLoadLeft[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.angryl6), spriteWidth, spriteHeight, true);
            spriteLoadLeft[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.angryl7), spriteWidth, spriteHeight, true);
            spriteLoadLeft[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.angryl8), spriteWidth, spriteHeight, true);


            spriteLoadRight[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.angryr1), spriteWidth, spriteHeight, true);
            spriteLoadRight[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.angryr2), spriteWidth, spriteHeight, true);
            spriteLoadRight[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.angryr3), spriteWidth, spriteHeight, true);
            spriteLoadRight[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.angryr4), spriteWidth, spriteHeight, true);
            spriteLoadRight[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.angryr5), spriteWidth, spriteHeight, true);
            spriteLoadRight[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.angryr6), spriteWidth, spriteHeight, true);
            spriteLoadRight[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.angryr7), spriteWidth, spriteHeight, true);
            spriteLoadRight[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.angryr8), spriteWidth, spriteHeight, true);


            spriteCount = 8 - 1; // count < sprite count
        } else if (characterNumber == 2) {
            spriteLoadLeft[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.batl1), spriteWidth, spriteHeight, true);
            spriteLoadLeft[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.batl2), spriteWidth, spriteHeight, true);
            spriteLoadLeft[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.batl3), spriteWidth, spriteHeight, true);
            spriteLoadLeft[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.batl4), spriteWidth, spriteHeight, true);
            spriteLoadLeft[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.batl5), spriteWidth, spriteHeight, true);
            spriteLoadLeft[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.batl6), spriteWidth, spriteHeight, true);
            spriteLoadLeft[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.batl7), spriteWidth, spriteHeight, true);
            spriteLoadLeft[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.batl8), spriteWidth, spriteHeight, true);


            spriteLoadRight[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.batr1), spriteWidth, spriteHeight, true);
            spriteLoadRight[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.batr2), spriteWidth, spriteHeight, true);
            spriteLoadRight[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.batr3), spriteWidth, spriteHeight, true);
            spriteLoadRight[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.batr4), spriteWidth, spriteHeight, true);
            spriteLoadRight[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.batr5), spriteWidth, spriteHeight, true);
            spriteLoadRight[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.batr6), spriteWidth, spriteHeight, true);
            spriteLoadRight[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.batr7), spriteWidth, spriteHeight, true);
            spriteLoadRight[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.batr8), spriteWidth, spriteHeight, true);

            spriteCount = 8 - 1;
        } else if (characterNumber == 3) {
            spriteLoadLeft[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.bitingl1), spriteWidth, spriteHeight, true);
            spriteLoadLeft[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.bitingl2), spriteWidth, spriteHeight, true);
            spriteLoadLeft[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.bitingl3), spriteWidth, spriteHeight, true);
            spriteLoadLeft[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.bitingl4), spriteWidth, spriteHeight, true);
            spriteLoadLeft[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.bitingl5), spriteWidth, spriteHeight, true);
            spriteLoadLeft[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.bitingl6), spriteWidth, spriteHeight, true);
            spriteLoadLeft[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.bitingl7), spriteWidth, spriteHeight, true);
            spriteLoadLeft[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.bitingl8), spriteWidth, spriteHeight, true);


            spriteLoadRight[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.bitingr1), spriteWidth, spriteHeight, true);
            spriteLoadRight[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.bitingr2), spriteWidth, spriteHeight, true);
            spriteLoadRight[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.bitingr3), spriteWidth, spriteHeight, true);
            spriteLoadRight[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.bitingr4), spriteWidth, spriteHeight, true);
            spriteLoadRight[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.bitingr5), spriteWidth, spriteHeight, true);
            spriteLoadRight[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.bitingr6), spriteWidth, spriteHeight, true);
            spriteLoadRight[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.bitingr7), spriteWidth, spriteHeight, true);
            spriteLoadRight[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.bitingr8), spriteWidth, spriteHeight, true);

            spriteCount = 8 - 1;
        } else if (characterNumber == 4) {
            spriteLoadLeft[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.bluel1), spriteWidth, spriteHeight, true);
            spriteLoadLeft[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.bluel2), spriteWidth, spriteHeight, true);
            spriteLoadLeft[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.bluel3), spriteWidth, spriteHeight, true);
            spriteLoadLeft[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.bluel4), spriteWidth, spriteHeight, true);
            spriteLoadLeft[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.bluel5), spriteWidth, spriteHeight, true);
            spriteLoadLeft[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.bluel6), spriteWidth, spriteHeight, true);


            spriteLoadRight[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.bluer1), spriteWidth, spriteHeight, true);
            spriteLoadRight[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.bluer2), spriteWidth, spriteHeight, true);
            spriteLoadRight[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.bluer3), spriteWidth, spriteHeight, true);
            spriteLoadRight[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.bluer4), spriteWidth, spriteHeight, true);
            spriteLoadRight[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.bluer5), spriteWidth, spriteHeight, true);
            spriteLoadRight[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.bluer6), spriteWidth, spriteHeight, true);

            spriteCount = 6 - 1;
        } else if (characterNumber == 5) {
            spriteLoadLeft[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.earphonel1), spriteWidth, spriteHeight, true);
            spriteLoadLeft[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.earphonel2), spriteWidth, spriteHeight, true);
            spriteLoadLeft[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.earphonel3), spriteWidth, spriteHeight, true);
            spriteLoadLeft[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.earphonel4), spriteWidth, spriteHeight, true);
            spriteLoadLeft[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.earphonel5), spriteWidth, spriteHeight, true);
            spriteLoadLeft[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.earphonel6), spriteWidth, spriteHeight, true);
            spriteLoadLeft[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.earphonel7), spriteWidth, spriteHeight, true);
            spriteLoadLeft[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.earphonel8), spriteWidth, spriteHeight, true);

            spriteLoadRight[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.earphoner1), spriteWidth, spriteHeight, true);
            spriteLoadRight[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.earphoner2), spriteWidth, spriteHeight, true);
            spriteLoadRight[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.earphoner3), spriteWidth, spriteHeight, true);
            spriteLoadRight[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.earphoner4), spriteWidth, spriteHeight, true);
            spriteLoadRight[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.earphoner5), spriteWidth, spriteHeight, true);
            spriteLoadRight[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.earphoner6), spriteWidth, spriteHeight, true);
            spriteLoadRight[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.earphoner7), spriteWidth, spriteHeight, true);
            spriteLoadRight[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.earphoner8), spriteWidth, spriteHeight, true);

            spriteCount = 8 - 1;
        } else if (characterNumber == 6) {
            spriteLoadLeft[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.flyingl1), spriteWidth, spriteHeight, true);
            spriteLoadLeft[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.flyingl2), spriteWidth, spriteHeight, true);
            spriteLoadLeft[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.flyingl3), spriteWidth, spriteHeight, true);
            spriteLoadLeft[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.flyingl4), spriteWidth, spriteHeight, true);
            spriteLoadLeft[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.flyingl5), spriteWidth, spriteHeight, true);
            spriteLoadLeft[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.flyingl6), spriteWidth, spriteHeight, true);
            spriteLoadLeft[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.flyingl7), spriteWidth, spriteHeight, true);
            spriteLoadLeft[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.flyingl8), spriteWidth, spriteHeight, true);

            spriteLoadRight[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.flyingr1), spriteWidth, spriteHeight, true);
            spriteLoadRight[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.flyingr2), spriteWidth, spriteHeight, true);
            spriteLoadRight[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.flyingr3), spriteWidth, spriteHeight, true);
            spriteLoadRight[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.flyingr4), spriteWidth, spriteHeight, true);
            spriteLoadRight[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.flyingr5), spriteWidth, spriteHeight, true);
            spriteLoadRight[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.flyingr6), spriteWidth, spriteHeight, true);
            spriteLoadRight[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.flyingr7), spriteWidth, spriteHeight, true);
            spriteLoadRight[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.flyingr8), spriteWidth, spriteHeight, true);


            spriteCount = 8 - 1;
        } else if (characterNumber == 7) {
            spriteLoadLeft[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.greenl1), spriteWidth, spriteHeight, true);
            spriteLoadLeft[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.greenl2), spriteWidth, spriteHeight, true);
            spriteLoadLeft[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.greenl3), spriteWidth, spriteHeight, true);
            spriteLoadLeft[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.greenl4), spriteWidth, spriteHeight, true);
            spriteLoadLeft[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.greenl5), spriteWidth, spriteHeight, true);
            spriteLoadLeft[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.greenl6), spriteWidth, spriteHeight, true);


            spriteLoadRight[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.greenr1), spriteWidth, spriteHeight, true);
            spriteLoadRight[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.greenr2), spriteWidth, spriteHeight, true);
            spriteLoadRight[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.greenr3), spriteWidth, spriteHeight, true);
            spriteLoadRight[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.greenr4), spriteWidth, spriteHeight, true);
            spriteLoadRight[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.greenr5), spriteWidth, spriteHeight, true);
            spriteLoadRight[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.greenr6), spriteWidth, spriteHeight, true);

            spriteCount = 6 - 1;
        } else if (characterNumber == 8) {
            spriteLoadLeft[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.hairyl1), spriteWidth, spriteHeight, true);
            spriteLoadLeft[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.hairyl2), spriteWidth, spriteHeight, true);
            spriteLoadLeft[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.hairyl3), spriteWidth, spriteHeight, true);
            spriteLoadLeft[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.hairyl4), spriteWidth, spriteHeight, true);
            spriteLoadLeft[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.hairyl5), spriteWidth, spriteHeight, true);
            spriteLoadLeft[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.hairyl6), spriteWidth, spriteHeight, true);


            spriteLoadRight[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.hairyr1), spriteWidth, spriteHeight, true);
            spriteLoadRight[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.hairyr2), spriteWidth, spriteHeight, true);
            spriteLoadRight[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.hairyr3), spriteWidth, spriteHeight, true);
            spriteLoadRight[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.hairyr4), spriteWidth, spriteHeight, true);
            spriteLoadRight[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.hairyr5), spriteWidth, spriteHeight, true);
            spriteLoadRight[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.hairyr6), spriteWidth, spriteHeight, true);

            spriteCount = 6 - 1;
        } else if (characterNumber == 9) {
            spriteLoadLeft[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.leafl1), spriteWidth, spriteHeight, true);
            spriteLoadLeft[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.leafl2), spriteWidth, spriteHeight, true);
            spriteLoadLeft[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.leafl3), spriteWidth, spriteHeight, true);
            spriteLoadLeft[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.leafl4), spriteWidth, spriteHeight, true);
            spriteLoadLeft[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.leafl5), spriteWidth, spriteHeight, true);
            spriteLoadLeft[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.leafl6), spriteWidth, spriteHeight, true);


            spriteLoadRight[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.leafr1), spriteWidth, spriteHeight, true);
            spriteLoadRight[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.leafr2), spriteWidth, spriteHeight, true);
            spriteLoadRight[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.leafr3), spriteWidth, spriteHeight, true);
            spriteLoadRight[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.leafr4), spriteWidth, spriteHeight, true);
            spriteLoadRight[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.leafr5), spriteWidth, spriteHeight, true);
            spriteLoadRight[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.leafr6), spriteWidth, spriteHeight, true);

            spriteCount = 6 - 1;
        } else if (characterNumber == 10) {
            spriteLoadLeft[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.pinkl1), spriteWidth, spriteHeight, true);
            spriteLoadLeft[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.pinkl2), spriteWidth, spriteHeight, true);
            spriteLoadLeft[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.pinkl3), spriteWidth, spriteHeight, true);
            spriteLoadLeft[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.pinkl4), spriteWidth, spriteHeight, true);
            spriteLoadLeft[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.pinkl5), spriteWidth, spriteHeight, true);
            spriteLoadLeft[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.pinkl6), spriteWidth, spriteHeight, true);


            spriteLoadRight[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.pinkr1), spriteWidth, spriteHeight, true);
            spriteLoadRight[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.pinkr2), spriteWidth, spriteHeight, true);
            spriteLoadRight[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.pinkr3), spriteWidth, spriteHeight, true);
            spriteLoadRight[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.pinkr4), spriteWidth, spriteHeight, true);
            spriteLoadRight[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.pinkr5), spriteWidth, spriteHeight, true);
            spriteLoadRight[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.pinkr6), spriteWidth, spriteHeight, true);

            spriteCount = 6 - 1;
        } else if (characterNumber == 11) {
            spriteLoadLeft[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.prettyl1), spriteWidth, spriteHeight, true);
            spriteLoadLeft[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.prettyl2), spriteWidth, spriteHeight, true);
            spriteLoadLeft[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.prettyl3), spriteWidth, spriteHeight, true);
            spriteLoadLeft[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.prettyl4), spriteWidth, spriteHeight, true);
            spriteLoadLeft[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.prettyl5), spriteWidth, spriteHeight, true);
            spriteLoadLeft[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.prettyl6), spriteWidth, spriteHeight, true);
            spriteLoadLeft[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.prettyl7), spriteWidth, spriteHeight, true);
            spriteLoadLeft[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.prettyl8), spriteWidth, spriteHeight, true);


            spriteLoadRight[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.prettyr1), spriteWidth, spriteHeight, true);
            spriteLoadRight[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.prettyr2), spriteWidth, spriteHeight, true);
            spriteLoadRight[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.prettyr3), spriteWidth, spriteHeight, true);
            spriteLoadRight[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.prettyr4), spriteWidth, spriteHeight, true);
            spriteLoadRight[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.prettyr5), spriteWidth, spriteHeight, true);
            spriteLoadRight[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.prettyr6), spriteWidth, spriteHeight, true);
            spriteLoadRight[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.prettyr7), spriteWidth, spriteHeight, true);
            spriteLoadRight[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.prettyr8), spriteWidth, spriteHeight, true);

            spriteCount = 8 - 1;
        } else if (characterNumber == 12) {
            spriteLoadLeft[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.littlel1), spriteWidth, spriteHeight, true);
            spriteLoadLeft[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.littlel2), spriteWidth, spriteHeight, true);
            spriteLoadLeft[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.littlel3), spriteWidth, spriteHeight, true);
            spriteLoadLeft[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.littlel4), spriteWidth, spriteHeight, true);
            spriteLoadLeft[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.littlel5), spriteWidth, spriteHeight, true);
            spriteLoadLeft[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.littlel6), spriteWidth, spriteHeight, true);
            spriteLoadLeft[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.littlel7), spriteWidth, spriteHeight, true);
            spriteLoadLeft[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.littlel8), spriteWidth, spriteHeight, true);


            spriteLoadRight[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.littler1), spriteWidth, spriteHeight, true);
            spriteLoadRight[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.littler2), spriteWidth, spriteHeight, true);
            spriteLoadRight[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.littler3), spriteWidth, spriteHeight, true);
            spriteLoadRight[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.littler4), spriteWidth, spriteHeight, true);
            spriteLoadRight[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.littler5), spriteWidth, spriteHeight, true);
            spriteLoadRight[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.littler6), spriteWidth, spriteHeight, true);
            spriteLoadRight[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.littler7), spriteWidth, spriteHeight, true);
            spriteLoadRight[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.littler8), spriteWidth, spriteHeight, true);

            spriteCount = 8 - 1;
        }


        this.context = context;
    }

    @Override
    public void draw(Canvas canvas) {

        Bitmap resizedBitmap0 = null;
        //Logic to chose the sprite for right direction move
        //This method allows to scale the image size
        double xPos = this.getxPos();
        if (oldXPos < xPos) {
            resizedBitmap0 = spriteLoadLeft[spriteIndex];
        } else {
            resizedBitmap0 = spriteLoadRight[spriteIndex];
        }

        spriteIndex=spriteIndex+1;
        canvas.drawBitmap(resizedBitmap0, this.getxPos() - 15, this.getyPos() - 15, null);

        //Bird sprite count reset
        if (spriteIndex >= spriteCount) {
            spriteIndex = 0;
        }

        oldXPos = this.getxPos();


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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
