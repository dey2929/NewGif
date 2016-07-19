package com.int403.jabong.myapplication2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;

/**
 * Created by jabong on 19/7/16.
 */
public class CustomDrawable extends Drawable {
    private Rect destRect;
    private Paint paint;
    private GifDecoder decoder;

    public void draw(final Canvas canvas) {


        class GifRunner implements Runnable {
            int index;

            public GifRunner(int index) {
                this.index = index;
            }

            @Override
            public void run() {
                {

                    Bitmap bitmap = decoder.getFrame(index);
                    canvas.drawBitmap(bitmap,null,getDestRect(),getPaint());
                    new Handler(Looper.getMainLooper()).postDelayed(new GifRunner((index + 1) % decoder.frameCount), decoder.getDelay(index));
                }
            }
        }


    }




    private Rect getDestRect() {
        if (destRect == null) {
            destRect = new Rect();
        }
        return destRect;
    }

    private Paint getPaint() {
        if (paint == null) {
            paint = new Paint(Paint.FILTER_BITMAP_FLAG);
        }
        return paint;
    }


    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
