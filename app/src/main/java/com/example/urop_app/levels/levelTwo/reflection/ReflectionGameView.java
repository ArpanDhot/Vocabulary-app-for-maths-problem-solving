package com.example.urop_app.levels.levelTwo.reflection;

import android.content.Context;
import android.content.Intent;
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
import com.example.urop_app.gameObjects.Banner;
import com.example.urop_app.gameObjects.Block;
import com.example.urop_app.gameObjects.Characters;
import com.example.urop_app.gameObjects.Sound;
import com.example.urop_app.levels.levelOne.axis.AxisGameLoop;
import com.example.urop_app.levels.levelTwo.intersects.IntersectsOne;
import com.example.urop_app.levels.levelTwo.intersects.IntersectsTwo;

public class ReflectionGameView extends SurfaceView implements SurfaceHolder.Callback {

    //Setting up required classes by the this class
    private ReflectionGameLoop reflectionGameLoop;
    private Context mContext;

    //Banner and voiceover
    private Sound sound;
    private Banner banner;
    boolean soundBoolean = true;

    //Setting up the background
    private Bitmap mainBackground;
    private Bitmap mirror[] = new Bitmap[2];

    //Monsters to place
    private Point monsterPointOne;
    private Characters monstersOne[] = new Characters[4];
    private int spriteRectSize = 50;

    //Block draw trigger
    private Point placeBlockPointOne;
    private Block blockTrigger;

    //Touch detection rectangle
    private Point touchFollowPoint;
    private Rect touchFollow;

    //Button to place items
    private Point buttonPlaceItemPoint;
    private Rect buttonPlaceItem;
    private boolean buttonPlaceItemPressed = false;
    private int buttonPlaceItemClickCount = 0;
    private Paint buttonPlaceItemPaint;
    private Paint textStyle;


    public ReflectionGameView(Context context) {
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        mContext = context;

        mainBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg06);
        mirror[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.mirror), 350, 400, true);
        mirror[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.mirror), 350, 400, true);

        //Setting up the game loop
        reflectionGameLoop = new ReflectionGameLoop(this, surfaceHolder);

        //Creating the first block object to avoid to have any index issues
        monsterPointOne = new Point(1270, 1270);
        monstersOne[0] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 2); //User control monster
        monsterPointOne.set(1100, 1270);
        monstersOne[1] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 2);
        monsterPointOne.set(1000, 1185);
        monstersOne[2] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 7);
        monsterPointOne.set(840, 1185);
        monstersOne[3] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 7);

        //Block trigger
        placeBlockPointOne = new Point(1950, 1200);
        blockTrigger = new Block(new Rect(0, 0, 60, 160), Color.argb(70, 255, 255, 255), placeBlockPointOne);

        //The touchFollowPoint(rectangle) will be used to get intersection against the save and the put "button". This approach was better than the conditions to create the perimeter because then I could not delay and the code would have been long.
        touchFollowPoint = new Point(500, 500);
        touchFollow = new Rect(0, 0, 40, 40);
        touchFollow.set(touchFollowPoint.x - touchFollow.width() / 2, touchFollowPoint.y - touchFollow.height() / 2, touchFollowPoint.x + touchFollow.width() / 2, touchFollowPoint.y + touchFollow.height() / 2);

        //The buttonPad (rectangle) will be used as button to put in the items
        buttonPlaceItemPoint = new Point(2400, 1200);
        buttonPlaceItem = new Rect(0, 0, 150, 150);
        buttonPlaceItem.set(buttonPlaceItemPoint.x - buttonPlaceItem.width() / 2, buttonPlaceItemPoint.y - buttonPlaceItem.height() / 2, buttonPlaceItemPoint.x + buttonPlaceItem.width() / 2, buttonPlaceItemPoint.y + buttonPlaceItem.height() / 2);
        buttonPlaceItemPaint = new Paint();

        textStyle = new Paint();
        textStyle.setColor(Color.rgb(234, 237, 239));
        textStyle.setStyle(Paint.Style.FILL);
        textStyle.setTextSize(40);

        setFocusable(true);
    }

    //CUSTOM METHODS
    private void buttonPressCheck() {
        //2) I am checking if the click count is equal to 2 then trigger and reset the count
        //To place the block
        if (buttonPlaceItemClickCount == 2) {
            Intent intent = new Intent(mContext, IntersectsOne.class);
            mContext.startActivity(intent);
            //Reset the count of click so the cycle continues
            buttonPlaceItemClickCount = 0;
        }
    }

    //ORIGINAL METHODS
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        //This method allows to scale the image size
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(mainBackground, 2560, 1500, true);
        //Drawing the Bitmap on to the canvas
        canvas.drawBitmap(resizedBitmap, 0, 0, null);

        //Trigger block
        blockTrigger.draw(canvas);

        //Mirror
        canvas.drawBitmap(mirror[0], 700, 1010, null);
        canvas.drawBitmap(mirror[1], 1600, 1010, null);

        //Monster
        monstersOne[0].draw(canvas);
        monstersOne[2].draw(canvas);
        monstersOne[3].draw(canvas);

        //Reflection monster logic
        if (Rect.intersects(monstersOne[0].getRectangle(), blockTrigger.getRectangle())) { //This is there so the main monster is in the right place in order to tiger
            if ((monstersOne[1].getxPos() >= 1678 && monstersOne[1].getxPos() <= 1790) && (monstersOne[1].getyPos() >= 1102 && monstersOne[1].getyPos() <= 1286)) { //The reflection monster doesn't appear when is not in the are of the mirror
                monstersOne[1].draw(canvas);
            }
        }

        //Printing the place button rectangle on the screen
        buttonPlaceItemPaint.setColor(Color.rgb(175, 201, 220));
        canvas.drawRect(buttonPlaceItem, buttonPlaceItemPaint);
        canvas.drawText("Place", 2350, 1215, textStyle);

        //loading up the sound and the banner
        if (soundBoolean) {
            soundBoolean = false;

            sound = new Sound(getContext(), 2);
            banner = new Banner(getContext(), 2);

        }

        //drawing the banner until the voiceover is on
        if (sound.getSoundLoad().isPlaying()) {
            banner.draw(canvas);
        }

    }

    public void update() {

        buttonPressCheck();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //Only letting the user play once the voice over is done
        if (!sound.getSoundLoad().isPlaying()) {
            // Handle user input touch event actions
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    //1) I am checking if the touchFollow is intersection with the buttonPad.Then assigning the trigger to the boolean...LINE 59
                    //Button place trigger
                    if (Rect.intersects(touchFollow, buttonPlaceItem)) {
                        buttonPlaceItemPressed = true;
                        if (buttonPlaceItemPressed == true) {
                            buttonPlaceItemClickCount++;
                        }
                    }
                case MotionEvent.ACTION_MOVE:
                    //Updating the position of the touchFollow rectangle. It is going to have the exact pos where is pressed.
                    touchFollow.set((int) event.getX() - touchFollow.width() / 2, (int) event.getY() - touchFollow.height() / 2, (int) event.getX() + touchFollow.width() / 2, (int) event.getY() + touchFollow.height() / 2);

                    //Logic for the monster main and the shadow monster
                    monstersOne[0].movement(event);
                    event.setLocation(event.getX() - 180, event.getY()); //EVENT VALUE CHANGE
                    monstersOne[1].movement(event);
                    //ATTENTION DO NOT PUT ANYTHING BELLOW THE EVENT IS GETTING MANIPULATED AND IT IS NOT ITS TRUE VALUE BELOW

                case MotionEvent.ACTION_UP:
                    //Closing down the button when the the finger is lifted from the screen.
                    buttonPlaceItemPressed = false;
                    return true;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("Game.java", "surfaceCreated()");
        if (reflectionGameLoop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            reflectionGameLoop = new ReflectionGameLoop(this, surfaceHolder);
        }
        reflectionGameLoop.startLoop();
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
        reflectionGameLoop.stopLoop();

    }
}