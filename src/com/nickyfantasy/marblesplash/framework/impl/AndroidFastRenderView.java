package com.nickyfantasy.marblesplash.framework.impl;

import com.nickyfantasy.marblesplash.R;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AndroidFastRenderView extends SurfaceView implements Runnable {
    AndroidGame game;
    AndroidGraphics graphics;
    Thread renderThread = null;
    SurfaceHolder holder;
    volatile boolean running = false;

    public AndroidFastRenderView(AndroidGame game) {
        super(game);
        this.game = game;
        this.graphics = (AndroidGraphics) game.getGraphics();
        this.holder = getHolder();
        holder.setFormat(PixelFormat.TRANSPARENT);
        setZOrderOnTop(true);
    }

    public void resume() { 
        running = true;
        renderThread = new Thread(this);
        renderThread.start();         
    }      

    public void run() {
        long startTime = System.nanoTime();
        long beginTime = System.nanoTime();
        int frameCount = 0;
        int totalFrameCount = 0;
        long realBeginTime = System.nanoTime();
        while(running) {  
            if(!holder.getSurface().isValid())
                continue;           
            
            float deltaTime = (System.nanoTime()-startTime) / 1000000000.0f;
            startTime = System.nanoTime();
            frameCount++;
            totalFrameCount++;
            
            if ((startTime - beginTime) > 1000000000.0f) {
            	Log.e("fps", "fps = " + frameCount);
            	Log.e("fps", "total fps = " + 1.0f * totalFrameCount / ((System.nanoTime() - realBeginTime) / 1000000000));
            	beginTime += 1000000000.0f;
            	frameCount = 0;
            }

            Canvas canvas = holder.lockCanvas();
            graphics.canvas = canvas;
            game.getCurrentScreen().update(deltaTime);
            game.getCurrentScreen().present(deltaTime);
            
//            canvas.getClipBounds(dstRect);
//            canvas.drawBitmap(framebuffer, null, dstRect, null);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause() {                        
        running = false;                        
        while(true) {
            try {
                renderThread.join();
                return;
            } catch (InterruptedException e) {
                // retry
            }
        }
    }        
}
