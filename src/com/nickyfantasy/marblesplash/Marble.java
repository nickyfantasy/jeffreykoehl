package com.nickyfantasy.marblesplash;

import android.util.Log;

import com.nickyfantasy.marblesplash.framework.Pixmap;

public class Marble extends GameObject {

	public int mSpeed; //px per sec
	public boolean mDestroyed;
	
	public Marble(MarbleColor color, int speed, int x) {
		super(null, x, 0);
		setMarbleProperties(color, speed);
	}

	@Override
	public void updateState(float deltaTime) {
		super.updateState(deltaTime);
		mPosY += (deltaTime * mSpeed);
		if (mPosY > Dimen.deviceHeight) {
			mDestroyed = true;
		}
	}

	public void setMarbleProperties(MarbleColor color, int speed) {
		mDestroyed = false;
		mPosY = -Assets.redMarble.getHeight();
		mSpeed = speed;
		switch (color) {
		case RED:
			mPixmap = Assets.redMarble;
			break;
		case BLUE:
			mPixmap = Assets.blueMarble;
			break;
		case YELLOW:
			mPixmap = Assets.yellowMarble;
			break;
		case GREEN:
			mPixmap = Assets.greenMarble;
			break;
		}
		mWidth = mPixmap.getWidth();
		mHeight = mPixmap.getHeight();
	}

}
