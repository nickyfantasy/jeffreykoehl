package com.nickyfantasy.marblesplash;

import com.nickyfantasy.marblesplash.framework.Pixmap;

public class GameObject{
	
	public Pixmap mPixmap;
	public int mPosX;
	public int mPosY;
	public int mWidth;
	public int mHeight;
	public boolean mPressing;
	
	public GameObject(Pixmap pixmap, int x, int y) {
		mPixmap = pixmap;
		mPosX = x;
		mPosY = y;
		if (pixmap != null) {
			mWidth = pixmap.getWidth();
			mHeight = pixmap.getHeight();
		}
	}
	
	public GameObject(Pixmap pixmap, int width, int height, int x, int y) {
		mPixmap = pixmap;
		mPosX = x;
		mPosY = y;
		mWidth = width;
		mHeight = height;
	}

	public boolean isTouchInBounds(int x, int y) {
	    boolean isTouch = false;  
	    if (x >= mPosX && x <= mPosX + mWidth && y >= mPosY  
	        && y <= mPosY + mHeight) {  
	        isTouch = true;
	    }  
	    return isTouch;  
	}


	public void setPressing(boolean pressing) {
		mPressing = pressing;
	}

	public void updateState(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

}
