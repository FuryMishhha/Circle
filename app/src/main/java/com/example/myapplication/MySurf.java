package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurf extends SurfaceView implements SurfaceHolder.Callback {
    MyThread mt=new MyThread(getHolder());

    public MySurf(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mt.setRunning(true);
        mt.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry=true;
        mt.setRunning(false);
        while(retry){
            try{
                mt.join();
                retry=false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
