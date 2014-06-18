package com.nickyfantasy.marblesplash.framework.impl;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Bitmap.Config;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.nickyfantasy.marblesplash.Dimen;
import com.nickyfantasy.marblesplash.R;
import com.nickyfantasy.marblesplash.framework.Audio;
import com.nickyfantasy.marblesplash.framework.FileIO;
import com.nickyfantasy.marblesplash.framework.Game;
import com.nickyfantasy.marblesplash.framework.Graphics;
import com.nickyfantasy.marblesplash.framework.Input;
import com.nickyfantasy.marblesplash.framework.Screen;

public abstract class AndroidGame extends Activity implements Game {
    AndroidFastRenderView renderView;
    Graphics graphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;
    WakeLock wakeLock;

    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        int frameBufferWidth = 0;
        int frameBufferHeight = 0;
        Point size = new Point();
        if (Build.VERSION.SDK_INT >= 19) {
        	View decorView = this.getWindow().getDecorView();
	        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY 
	        		| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
	        		| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
	        decorView.setSystemUiVisibility(uiOptions);
            this.getWindowManager().getDefaultDisplay().getRealSize(size);
            frameBufferWidth = size.x;
            frameBufferHeight = size.y;
        } else if (Build.VERSION.SDK_INT >= 14) {
	        View decorView = this.getWindow().getDecorView();
	        int uiOptions = View.SYSTEM_UI_FLAG_LOW_PROFILE;
	        decorView.setSystemUiVisibility(uiOptions);
	        try {
	            this.getWindowManager().getDefaultDisplay().getSize(size);
	            frameBufferWidth = size.x;
	            frameBufferHeight = size.y;
	        } catch (NoSuchMethodError e) {
	            Log.i("error", "it can't work");
	        }
        } else {
        	DisplayMetrics metrics = new DisplayMetrics();
            this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            frameBufferWidth = metrics.widthPixels;
            frameBufferHeight = metrics.heightPixels;
        } 
        Dimen.deviceWidth = frameBufferWidth;
        Dimen.deviceHeight = frameBufferHeight;
        Dimen.scaleRatio = frameBufferHeight / 1080f;
        Log.d("ZZZ", "display width = " + frameBufferWidth);
        Log.d("ZZZ", "display height = " + frameBufferHeight);
//        int frameBufferWidth = 1200;
//        int frameBufferHeight = 800;
//        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
//                frameBufferHeight, Config.RGB_565);
        
        float scaleX = (float) frameBufferWidth
                / getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float) frameBufferHeight
                / getWindowManager().getDefaultDisplay().getHeight();

        graphics = new AndroidGraphics(getAssets());
        renderView = new AndroidFastRenderView(this);
        fileIO = new AndroidFileIO(this);
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, renderView, scaleX, scaleY);
        screen = getStartScreen();
//        FrameLayout frameLayout=  new FrameLayout(this);
//        frameLayout.addView(renderView);
//        frameLayout.setBackgroundResource(R.drawable.main_bg3);
        setContentView(renderView);
        
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
    }

    @Override
    public void onResume() {
        super.onResume();
        wakeLock.acquire();
        screen.resume();
        renderView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        wakeLock.release();
        renderView.pause();
        screen.pause();

        if (isFinishing())
            screen.dispose();
    }

    public Input getInput() {
        return input;
    }

    public FileIO getFileIO() {
        return fileIO;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setScreen(Screen screen, final int backgroundResId) {
        if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");
        runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				renderView.setBackgroundResource(backgroundResId);
			}
		});

        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;

    }

    public Screen getCurrentScreen() {
        return screen;
    }
}
