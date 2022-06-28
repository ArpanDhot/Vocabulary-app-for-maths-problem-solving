package com.example.urop_app.levels.levelOne.increase;

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
import com.example.urop_app.gameObjects.Block;
import com.example.urop_app.gameObjects.Characters;
import com.example.urop_app.levels.levelTwo.intersects.IntersectsTwo;

import java.util.ArrayList;

public class IncreaseGameView extends SurfaceView implements SurfaceHolder.Callback {


    private IncreaseGameLoop increaseGameLoop;
    private Bitmap mainBackground;

    private Context mContext;

    private int spriteRectSize = 50;

    //Monster place block
    private Point placeBlockPoint;
    private Block [] placeBlock = new Block[4];

    //BLock place intersect true
    private boolean [] intersectMonsterPlace = new boolean[4];

    //Touch detection rectangle
    private Point touchFollowPoint;
    private Rect touchFollow;

    //Monsters auto
    private Point[] monsterPointTwo = new Point[6];
    private Characters[] monstersTwo = new Characters[6];

    //Monsters to place
    private Point monsterPointOne;
    private ArrayList<Characters> monstersOne = new ArrayList<>();

    //Button to place items
    private Point buttonPlaceItemPoint;
    private Rect buttonPlaceItem;
    private boolean buttonPlaceItemPressed = false;
    private int buttonPlaceItemClickCount = 0;
    private Paint buttonPlaceItemPaint;

    //Text properties
    private Paint textStyle;


    public IncreaseGameView(Context context) {
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        mContext = context;

        //Loading the background
        mainBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg02);

        //Setting up the game loop
        increaseGameLoop = new IncreaseGameLoop(this, surfaceHolder);

        textStyle = new Paint();
        textStyle.setColor(Color.rgb(234, 237, 239));
        textStyle.setStyle(Paint.Style.FILL);
        textStyle.setTextSize(40);

        //The touchFollowPoint(rectangle) will be used to get intersection against the save and the put "button". This approach was better than the conditions to create the perimeter because then I could not delay and the code would have been long.
        touchFollowPoint = new Point(500, 500);
        touchFollow = new Rect(0, 0, 40, 40);
        touchFollow.set(touchFollowPoint.x - touchFollow.width() / 2, touchFollowPoint.y - touchFollow.height() / 2, touchFollowPoint.x + touchFollow.width() / 2, touchFollowPoint.y + touchFollow.height() / 2);

        //The buttonPad (rectangle) will be used as button to put in the items
        buttonPlaceItemPoint = new Point(2400, 1200);
        buttonPlaceItem = new Rect(0, 0, 150, 150);
        buttonPlaceItem.set(buttonPlaceItemPoint.x - buttonPlaceItem.width() / 2, buttonPlaceItemPoint.y - buttonPlaceItem.height() / 2, buttonPlaceItemPoint.x + buttonPlaceItem.width() / 2, buttonPlaceItemPoint.y + buttonPlaceItem.height() / 2);
        buttonPlaceItemPaint = new Paint();

        //Creating the first block object to avoid to have any index issues
        monsterPointOne = new Point(600, 600);
        monstersOne.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 1));

        //Monster place blocks
        placeBlockPoint = new Point(1800,1350);
        for(int i=0;i<4;i++){
            placeBlock[i] = new Block(new Rect(0, 0, 100, 100), Color.argb(70,255, 255, 255), placeBlockPoint);
            placeBlockPoint.set(placeBlockPoint.x, placeBlockPoint.y - 110);
        }


        //Setting up the auto monster
        monsterPointTwo[0] = new Point(-50, 1350); //TODO remove end pos  0 to 2 x=1300
        monsterPointTwo[1] = new Point(-50, 1240);
        monsterPointTwo[2] = new Point(-50, 1130);
        monsterPointTwo[3] = new Point(-250, 1350);  //TODO remove end pos  3 to 4 x=800
        monsterPointTwo[4] = new Point(-250, 1240);
        monsterPointTwo[5] = new Point(-450, 1350); //TODO remove end pos  5 x=400

        monstersTwo[0] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo[0], getContext(), 2, 1);
        monstersTwo[1] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo[1], getContext(), 2, 1);
        monstersTwo[2] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo[2], getContext(), 2, 1);
        monstersTwo[3] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo[3], getContext(), 2, 1);
        monstersTwo[4] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo[4], getContext(), 2, 1);
        monstersTwo[5] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo[5], getContext(), 2, 1);



        setFocusable(true);
    }

    //CUSTOM METHODS

    //Auto monster movement method
    private void monsterAutoMove() {


        if(monstersTwo[0].getxPos()<=1300){
            monstersTwo[0].update(monstersTwo[0].getSpeed(),0);
            monstersTwo[1].update(monstersTwo[1].getSpeed(),0);
            monstersTwo[2].update(monstersTwo[2].getSpeed(),0);
        }

        if(monstersTwo[3].getxPos()<=800){
            monstersTwo[3].update(monstersTwo[3].getSpeed(),0);
            monstersTwo[4].update(monstersTwo[4].getSpeed(),0);
        }

        if(monstersTwo[5].getxPos()<=400){
            monstersTwo[5].update(monstersTwo[5].getSpeed(),0);
        }

    }

    //Method to check if the monsters are placed in the rectangles
    private void monsterPlaceCheck(){

        for (Characters characters : monstersOne){

            if(Rect.intersects(characters.getRectangle(),placeBlock[0].getRectangle())){
                intersectMonsterPlace[0] =true;
            }
            if(Rect.intersects(characters.getRectangle(),placeBlock[1].getRectangle())){
                intersectMonsterPlace[1] =true;
            }
            if(Rect.intersects(characters.getRectangle(),placeBlock[2].getRectangle())){
                intersectMonsterPlace[2] =true;
            }
            if(Rect.intersects(characters.getRectangle(),placeBlock[3].getRectangle())){
                intersectMonsterPlace[3] =true;
            }

        }


        if((intersectMonsterPlace[0]==true)&&(intersectMonsterPlace[1]==true)&&(intersectMonsterPlace[2]==true)&&(intersectMonsterPlace[3]==true)){
            Intent intent = new Intent(mContext, IntersectsTwo.class);
            mContext.startActivity(intent);
        }
    }


    //ORIGINAL METHODS
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        //This method allows to scale the image size
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(mainBackground, 2600, 1500, true);
        //drawing the Bitmap on to the canvas
        canvas.drawBitmap(resizedBitmap, 0, 0, null);

        for (Block block : placeBlock) {
            block.draw(canvas);
        }

        for (Characters monster : monstersTwo) {
            monster.draw(canvas);
        }

        //Printing a of the block objects
        for (Characters monster : monstersOne) {
            monster.draw(canvas);
        }






        //Printing the place button rectangle on the screen
        buttonPlaceItemPaint.setColor(Color.rgb(175, 201, 220));
        canvas.drawRect(buttonPlaceItem, buttonPlaceItemPaint);
        canvas.drawText("Place", 2350, 1215, textStyle);


    }

    public void update() {

        //2) I am checking if the click count is equal to 2 then trigger and reset the count
        //To place the block
        if (buttonPlaceItemClickCount == 2) {

            //Creating the locks on when the user clicks
            monsterPointOne.set(50, 50);
            monstersOne.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 1));
            //Reset the count of click so the cycle continues
            buttonPlaceItemClickCount = 0;
        }



        //
        monsterAutoMove();

        //
        monsterPlaceCheck();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

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

                //Only calling the block movement method of the most recent element
                //The condition is there to stop the block to move when the user is pressing the button
                if (!(Rect.intersects(touchFollow, buttonPlaceItem))) {
                    monstersOne.get(monstersOne.size() - 1).movement(event);
                }
            case MotionEvent.ACTION_UP:
                //Closing down the button when the the finger is lifted from the screen.
                buttonPlaceItemPressed = false;

                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("Game.java", "surfaceCreated()");
        if (increaseGameLoop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            increaseGameLoop = new IncreaseGameLoop(this, surfaceHolder);
        }
        increaseGameLoop.startLoop();
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
        increaseGameLoop.stopLoop();

    }
}
