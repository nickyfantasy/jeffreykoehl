package com.nickyfantasy.marblesplash;

import android.util.Log;

import com.nickyfantasy.marblesplash.framework.Pixmap;

public class Marble extends GameObject{
	
	public int mSpeed; //move 300px per sec
	
	public Marble(Pixmap pixmap, int x, int y) {
		super(pixmap, x, y);
	}
	
	@Override
	public void updateState(float deltaTime) {
		super.updateState(deltaTime);
		mPosY += (deltaTime * mSpeed);
		
//		mPosY += 1;
	}

}
