package com.int403.jabong.myapplication2;

import android.content.Context;
import android.os.AsyncTask;

import java.io.InputStream;

/**
 * Created by jabong on 19/7/16.
 */
public class GifLoader  {
    private int resID;
    private InputStream is;
    private Context mContext;
    private GifDecoder decoder;

    public GifLoader(int resID) {
        this.resID = resID;
    }
     public class StartDecodingOnThread extends AsyncTask {

            public StartDecodingOnThread(){
                execute();
            }

         @Override
         protected Object doInBackground(Object[] params) {
             is = mContext.getResources().openRawResource(R.raw.sample);
             GifDecoder decoder = new GifDecoder();
             decoder.read(is);
             return decoder;
         }


     }


}
