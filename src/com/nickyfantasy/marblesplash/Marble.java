package com.nickyfantasy.marblesplash;

import com.nickyfantasy.marblesplash.framework.Pixmap;

public class Marble extends GameObject{
	
	public Marble(Pixmap pixmap, int x, int y) {
		super(pixmap, x, y);
	}
	
	@Override
	public void updateState(float deltaTime) {
		super.updateState(deltaTime);
		mPosY++;
	}

}
