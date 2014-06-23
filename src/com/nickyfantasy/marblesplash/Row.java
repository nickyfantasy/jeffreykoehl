package com.nickyfantasy.marblesplash;

import android.util.Log;

import com.nickyfantasy.marblesplash.framework.Pixmap;

public class Row extends GameObject {

	// LinkedList<Marble> mMarbleList = new LinkedList<Marble>();
	public static final int TOUCH_LEFT_PADDING = Dimen.apply(60); //this need to be calculated again with marble size
	Marble[] mMarbleList;
	int mMarbleIndexToInsert = 0;
	int mDestroyedMarbleIndex = 0;
	final int mMarbleXPos;
	Marble mBottomMarble;

	public Row(Pixmap pixmap, int width, int height, int x, int y, int maxSize) {
		super(pixmap, width, height, x, y);
		mMarbleList = new Marble[maxSize];
		mMarbleXPos = x + ((width - Assets.redMarble.getWidth()) / 2);
	}

	@Override
	public void updateState(float deltaTime) {
		super.updateState(deltaTime);
		mBottomMarble = null;
		for (Marble marble : mMarbleList) {
			if (marble != null) {
				if (marble.mState != MarbleState.DESTROYED) {
					marble.updateState(deltaTime);
				}
				if (marble.mState == MarbleState.FALLING) {
					if (mBottomMarble == null) mBottomMarble = marble;
					else if (marble.mPosY > mBottomMarble.mPosY) mBottomMarble = marble;
				}
			} else {
				break;
			}
		}
	}

	public void insertMarble(int color, int speed) {
		Marble marble = reuseMarble(color, speed);
		if (marble == null) {
			marble = createMarble(color, speed);
			mMarbleList[mMarbleIndexToInsert++] = marble;
			if (mMarbleIndexToInsert == mMarbleList.length)
				mMarbleIndexToInsert = 0;
		}
	}

	private Marble createMarble(int color, int speed) {
		Log.e("ZZZ", "createMarble");
		return new Marble(color, speed, mMarbleXPos);
	}

	private Marble reuseMarble(int color, int speed) {
		for (int i = 0; i < mMarbleList.length; i++) {
			Marble marble = mMarbleList[i];
			if (marble == null)
				return null;
			if (marble.mState == MarbleState.DESTROYED) {
				marble.setMarbleProperties(color, speed, mMarbleXPos);
				return marble;
			}
		}
		return null;
	}
	
	public boolean isTouchInBounds(int x, int y) {
	    boolean isTouch = false;  
	    if (x >= mPosX - TOUCH_LEFT_PADDING && x <= mPosX + mWidth + TOUCH_LEFT_PADDING) {  
	        isTouch = true;
	    }  
	    return isTouch;  
	}

}
