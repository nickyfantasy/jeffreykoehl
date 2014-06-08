package com.nickyfantasy.marblesplash;

import com.nickyfantasy.marblesplash.framework.Pixmap;

public class MenuActionItem implements TouchableItem {
	
	private Pixmap mPixmap;
	private int mPosX;
	private int mPosY;
	private int mWidth;
	private int mHeight;
	private boolean mPressing;
	
	public MenuActionItem(Pixmap pixmap, int x, int y) {
		mPixmap = pixmap;
		mPosX = x;
		mPosY = y;
		mWidth = pixmap.getWidth();
		mHeight = pixmap.getHeight();
	}

	@Override
	public boolean isTouchInBounds(int x, int y) {
	    boolean isTouch = false;  
	    if (x >= mPosX && x <= mPosX + mWidth && y >= mPosY  
	        && y <= mPosY + mHeight) {  
	        isTouch = true;
	    }  
	    return isTouch;  
	}

	@Override
	public int getX() {
		return mPosX;
	}

	@Override
	public int getY() {
		return mPosY;
	}

	@Override
	public int getWidth() {
		return mWidth;
	}

	@Override
	public int getHeight() {
		return mHeight;
	}
	
	@Override
	public Pixmap getPixmap() {
		return mPixmap;
	}

	@Override
	public void setPressing(boolean pressing) {
		mPressing = pressing;
	}

	@Override
	public void updateState(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

}
