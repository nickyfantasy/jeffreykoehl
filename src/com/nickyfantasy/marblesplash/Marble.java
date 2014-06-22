package com.nickyfantasy.marblesplash;

import android.util.Log;

import com.nickyfantasy.marblesplash.framework.Pixmap;

public class Marble extends GameObject {

	public static final int FLYING_SPEED = Dimen.apply(1000);
	public static final int TOUCH_LEFT_PADDING = Dimen.apply(100);
	public static final int TOUCH_TOP_PADDING = Dimen.apply(130);
	public static final int TOUCH_BOTTOM_PADDING = Dimen.apply(70);
	public int mSpeed; //px per sec
	public int mState = MarbleState.FALLING;
	public int[] mTouchStartPos = new int[2];
	public int[] mEndStartPos = new int[2];
	
	public Marble(int color, int speed, int x) {
		super(null, 0, 0);
		setMarbleProperties(color, speed, x);
	}

	@Override
	public void updateState(float deltaTime) {
		super.updateState(deltaTime);
		if (mState == MarbleState.FLYING) {
			int deltaDist = Math.round(deltaTime * FLYING_SPEED);
			int swipeDeltaX = mEndStartPos[0] - mTouchStartPos[0];
			int swipeDeltaY = mEndStartPos[1] - mTouchStartPos[1];
			if (Math.abs(swipeDeltaX) > Math.abs(swipeDeltaY)) {
				mPosX += deltaDist;
				mPosY += deltaDist * 1.0f / swipeDeltaX * swipeDeltaY;
			} else {
				mPosY += deltaDist;
				mPosX += deltaDist * 1.0f / swipeDeltaY * swipeDeltaX;
			}
//			mPosY -= ();
//			mPosX -= (deltaTime * FLYING_SPEED);
			if (mPosY > Dimen.deviceHeight || mPosX > Dimen.deviceWidth 
					|| mPosY < -mPixmap.getHeight() || mPosX < -mPixmap.getWidth()) {
				Log.e("ZZZ", "desstroyed");
				mState = MarbleState.DESTROYED;
			}
		} else {
			mPosY += Math.round(deltaTime * mSpeed);
			if (mPosY > Dimen.deviceHeight) {
				mState = MarbleState.DESTROYED;
			}
		}
	}

	public void setMarbleProperties(int color, int speed, int x) {
		mState = MarbleState.FALLING;
		mPosY = -Assets.redMarble.getHeight();
		mPosX = x;
		mSpeed = speed;
		switch (color) {
		case MarbleColor.RED:
			mPixmap = Assets.redMarble;
			break;
		case MarbleColor.BLUE:
			mPixmap = Assets.blueMarble;
			break;
		case MarbleColor.YELLOW:
			mPixmap = Assets.yellowMarble;
			break;
		case MarbleColor.GREEN:
			mPixmap = Assets.greenMarble;
			break;
		}
		mWidth = mPixmap.getWidth();
		mHeight = mPixmap.getHeight();
	}
	
	public boolean isTouchInBounds(int x, int y) {
	    boolean isTouch = false;  
	    if (x >= mPosX - TOUCH_LEFT_PADDING && x <= mPosX + mWidth + TOUCH_LEFT_PADDING && y >= mPosY - TOUCH_TOP_PADDING  
	        && y <= mPosY + mHeight + TOUCH_BOTTOM_PADDING) {  
	        isTouch = true;
	    }  
	    return isTouch;  
	}

}
