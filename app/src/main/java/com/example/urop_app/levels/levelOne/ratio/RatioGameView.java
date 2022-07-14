package com.example.urop_app.levels.levelOne.ratio;

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
import com.example.urop_app.levels.levelOne.axis.AxisOne;
import com.example.urop_app.levels.levelTwo.intersects.IntersectsTwo;

import java.util.ArrayList;

public class RatioGameView extends SurfaceView implements SurfaceHolder.Callback {


    //Setting up required classes by the this class
    private RatioGameLoop ratioGameLoop;
    private Context mContext;

    //Banner and voiceover
    private Sound sound;
    private Banner banner;
    boolean soundBoolean = true;

    //Setting up the background
    private Bitmap mainBackground;
    private Bitmap barrel;


    //Cage objects
    private Block cageBlock[] = new Block[4];
    private Point cagePoint[] = new Point[2];
    private Bitmap cageSpriteLoad[] = new Bitmap[2];

    //Cage labels objects
    private Block cageLabelsBlock[] = new Block[4];
    private Point cageLabelsPoint[] = new Point[2];

    //Text and score
    private Paint paintText;
    private int scoreFirst = 0, scoreSecond = 0;

    //Monsters to place
    private Point monsterPointOne;
    private Characters monstersOne[] = new Characters[6];
    private boolean intersectCheck[] = new boolean[6];
    private int spriteRectSize = 50;
    private int sumIntersect = 0;

    //Monster static
    private Point monsterPointTwo;
    private ArrayList<Characters> monstersTwo = new ArrayList<>();


    public RatioGameView(Context context) {
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        mContext = context;

        mainBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg04);
        barrel = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.barrel), 200, 230, true);
        cageSpriteLoad[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.cg01), 400, 400, true);
        cageSpriteLoad[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.cgr01), 400, 400, true);


        //Setting up the game loop
        ratioGameLoop = new RatioGameLoop(this, surfaceHolder);


        //Setting up the objects for the cage
        cagePoint[0] = new Point(300, 1200);
        cageBlock[0] = new Block(new Rect(0, 0, 400, 400), Color.argb(0, 220, 220, 220), cagePoint[0]);
        cagePoint[0].set(750, 1200);
        cageBlock[1] = new Block(new Rect(0, 0, 400, 400), Color.argb(0, 220, 220, 220), cagePoint[0]);

        cagePoint[1] = new Point(1800, 1200);
        cageBlock[2] = new Block(new Rect(0, 0, 200, 220), Color.argb(200, 220, 220, 220), cagePoint[1]);
        cagePoint[1].set(2250, 1200);
        cageBlock[3] = new Block(new Rect(0, 0, 200, 220), Color.argb(200, 220, 220, 220), cagePoint[1]);


        //Setting up the objects for the cage labels
        cageLabelsPoint[0] = new Point(300, 1200);
        cageLabelsBlock[0] = new Block(new Rect(0, 0, 100, 100), Color.argb(200, 32, 178, 170), cageLabelsPoint[0]);
        cageLabelsPoint[0].set(750, 1200);
        cageLabelsBlock[1] = new Block(new Rect(0, 0, 100, 100), Color.argb(200, 220, 220, 220), cageLabelsPoint[0]);

        cageLabelsPoint[1] = new Point(1800, 1200);
        cageLabelsBlock[2] = new Block(new Rect(0, 0, 100, 100), Color.argb(200, 32, 178, 170), cageLabelsPoint[1]);
        cageLabelsPoint[1].set(2250, 1200);
        cageLabelsBlock[3] = new Block(new Rect(0, 0, 100, 100), Color.argb(200, 220, 220, 220), cageLabelsPoint[1]);

        paintText = new Paint();
        paintText.setColor(Color.BLACK);
        paintText.setStyle(Paint.Style.FILL);
        paintText.setTextSize(60);


        //Creating the first block object to avoid to have any index issues
        monsterPointOne = new Point(1270, 1270);
        for (int i = 0; i < 6; i++) {
            //This condition allows me to load two different sprites at different index start
            if (i <= 1) {
                monstersOne[i] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 7);
            } else if (i >= 2 && i <= 5) {
                monstersOne[i] = new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointOne, getContext(), 8, 12);
            }

            intersectCheck[i] = false;
        }

        //Monster static
        monsterPointTwo = new Point(350, 1150);
        monstersTwo.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo, getContext(), 8, 7));
        monsterPointTwo.set(180, 1090);
        monstersTwo.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo, getContext(), 8, 7));
        monsterPointTwo.set(220, 1280);
        monstersTwo.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo, getContext(), 8, 7));
        monsterPointTwo.set(620, 1250);
        monstersTwo.add(new Characters(new Rect(0, 0, spriteRectSize, spriteRectSize), monsterPointTwo, getContext(), 8, 12));


        setFocusable(true);
    }

    //CUSTOM METHODS
    //This method contains the logic of monster intersecting with the block and spawning new monster
    //This method also record how many boxes do have a monster in it
    private void blockMonsterIntersect() {

        for (int i = 0; i < 6; i++) {

            if (Rect.intersects(cageBlock[2].getRectangle(), monstersOne[i].getRectangle())) {
                //Checking if the intersectCheck boolean is false. It means that the monster has not intersected and new monster has not been added to the linked list
                if (intersectCheck[i] == false && i <= 1) {
                    //Creating adding new monster
                    // Making the intersectCheck boolean true. Meaning that new object has been added
                    intersectCheck[i] = true;
                    //It is used index the monster to control in the onTouchEvent method
                    //sumIntersect++;
                    //It updates the score(ratio values)
                    if (i <= 1 && scoreFirst < 2) {
                        scoreFirst++;
                        //Stopping the index to out of bound
                        if(sumIntersect < 5){
                            sumIntersect++;
                        }
                    }

                }
            } else if (Rect.intersects(cageBlock[3].getRectangle(), monstersOne[i].getRectangle())) {
                //Checking if the intersectCheck boolean is false. It means that the monster has not intersected and new monster has not been added to the linked list
                if (intersectCheck[i] == false && i >= 2) {
                    //Creating adding new monster
                    // Making the intersectCheck boolean true. Meaning that new object has been added
                    intersectCheck[i] = true;
                    //It is used index the monster to control in the onTouchEvent method
                    //sumIntersect++;
                    //It updates the score(ratio values)
                    if (i >= 2 && scoreSecond < 6) {
                        scoreSecond++;
                        //Stopping the index to out of bound
                        if(sumIntersect < 5){
                            sumIntersect++;
                        }
                    }

                }
            }

        }

        System.out.println(sumIntersect);
        if (scoreFirst == 2 && scoreSecond == 4) {
            Intent intent = new Intent(mContext, AxisOne.class);
            mContext.startActivity(intent);
        }

    }

    //ORIGINAL METHODS
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        //This method allows to scale the image size
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(mainBackground, 2600, 1500, true);
        //Drawing the Bitmap on to the canvas
        canvas.drawBitmap(resizedBitmap, 0, 0, null);

        //Printing monster objects static
        for (Characters characters : monstersTwo) {
            characters.draw(canvas);
        }

        //Cage first two
        canvas.drawBitmap(cageSpriteLoad[0], 100, 1000, null);
        canvas.drawBitmap(cageSpriteLoad[1], 550, 1000, null);


        //Printing monster objects user move
        for (Characters characters : monstersOne) {
            characters.draw(canvas);
        }

        //Cage last two
        canvas.drawBitmap(cageSpriteLoad[0], 1600, 1000, null);
        canvas.drawBitmap(cageSpriteLoad[1], 2050, 1000, null);

        //Cage labels
        for (Block block : cageLabelsBlock) {
            block.draw(canvas);
        }

        //Barrel
        canvas.drawBitmap(barrel, 1200, 1200, null);

        //Example text ratio
        canvas.drawText("3", 280, 1220, paintText);
        canvas.drawText("1", 730, 1220, paintText);

        //Update text ratio
        canvas.drawText("" + scoreFirst, 1780, 1220, paintText);
        canvas.drawText("" + scoreSecond, 2230, 1220, paintText);

        //loading up the sound and the banner
        if (soundBoolean) {
            soundBoolean = false;

            sound = new Sound(getContext(), 6);
            banner = new Banner(getContext(), 6);

        }

        //drawing the banner until the voiceover is on
        if (sound.getSoundLoad().isPlaying()) {
            banner.draw(canvas);
        }
    }

    public void update() {

        blockMonsterIntersect();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //Only letting the user play once the voice over is done
        if (!sound.getSoundLoad().isPlaying()) {
            // Handle user input touch event actions
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    monstersOne[sumIntersect].movement(event);

                    return true;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("Game.java", "surfaceCreated()");
        if (ratioGameLoop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            ratioGameLoop = new RatioGameLoop(this, surfaceHolder);
        }
        ratioGameLoop.startLoop();
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
        ratioGameLoop.stopLoop();

    }
}
