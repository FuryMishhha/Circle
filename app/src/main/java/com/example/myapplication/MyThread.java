package com.example.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.Random;

public class MyThread extends Thread {
    private Paint paint;
    private SurfaceHolder holder;
    private boolean flag;
    public void setRunning(boolean f){
        this.flag=f;
    }
    MyThread(SurfaceHolder h){
        this.flag=false;
        paint= new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        this.holder=h;
    }

    @Override
    public void run() {
        //super.run();
        int i=0;
        while (flag){
            Canvas canvas=null;
            canvas=canvas=holder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            Random r = new Random();
            int color= Color.rgb(r.nextInt(255),r.nextInt(255),r.nextInt(255));
            int height=canvas.getHeight();
            int weight=canvas.getWidth();
            int maxradius=Math.min(height,weight)/2;
            paint.setColor(color);

            canvas.drawCircle(weight/2, height/2, maxradius-i, paint);

            holder.unlockCanvasAndPost(canvas);
            try{
                Thread.sleep(10);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            i+=2;
        }
    }
}
