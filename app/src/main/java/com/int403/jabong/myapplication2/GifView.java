package com.int403.jabong.myapplication2;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.io.InputStream;



public class GifView extends ImageView {


    private InputStream gifInputStream;
    private InputStream gifInputStream1;

    private GifDecoder decoder;
    private boolean isPaused;
    private boolean isRunning;
    private int resId;

    public GifView(Context context) {
        super(context);
    }


    public GifView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GifView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private class GifRunner implements Runnable {
        int index;

        public GifRunner(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            {

                Bitmap bitmap = decoder.getFrame(index);
                GifView.this.setImageBitmap(bitmap);
                new Handler(Looper.getMainLooper()).postDelayed(new GifRunner((index + 1) % decoder.frameCount), decoder.getDelay(index));
            }
        }
    }

    public void startGif() {

        //Glide.with(getContext()).load("").asGif().into();
        new Handler(Looper.getMainLooper()).postDelayed(new GifRunner(0), 0);
    }



    public void stopGif(){
        isRunning = false;
    }

    @Override
    protected void onDetachedFromWindow() {
        stopGif();
        super.onDetachedFromWindow();
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int id) {
        this.resId = id;
        gifInputStream = getContext().getResources().openRawResource(id);
        decoder = new GifDecoder();
        decoder.read(gifInputStream);
    }
}
