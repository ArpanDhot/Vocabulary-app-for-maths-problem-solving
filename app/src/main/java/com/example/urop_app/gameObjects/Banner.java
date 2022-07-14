package com.example.urop_app.gameObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import com.example.urop_app.R;

public class Banner implements GameObject {

    //Context loading
    private Context context;

    //Image loading
    private Bitmap bitmap;


    public Banner(Context context, int bannerNumber) {
        this.context = context;

        loadBannerToVar(bannerNumber);

    }

    private void loadBannerToVar(int bannerNumber) {

        switch (bannerNumber) {

            case 2:
                bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.volumeinstruction), 2560, 1500, true);
                break;

            case 4:
                bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.volumeinstruction), 2560, 1500, true);
                break;

            case 6:
                bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.volumeinstruction), 2560, 1500, true);
                break;

            case 8:
                bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.volumeinstruction), 2560, 1500, true);
                break;

            case 10:
                bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.volumeinstruction), 2560, 1500, true);
                break;

            case 12:
                bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.volumeinstruction), 2560, 1500, true);
                break;

            case 14:
                bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.volumeinstruction), 2560, 1500, true);
                break;

            case 16:
                bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.volumeinstruction), 2560, 1500, true);
                break;

            case 18:
                bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.volumeinstruction), 2560, 1500, true);
                break;

            case 20:
                bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.volumeinstruction), 2560, 1500, true);
                break;

            case 22:
                bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.volumeinstruction), 2560, 1500, true);
                break;

            case 24:
                bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.volumeinstruction), 2560, 1500, true);
                break;


        }

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    @Override
    public void update() {

    }
}
