package com.nickyfantasy.marblesplash;

import com.nickyfantasy.marblesplash.Constant.FlyingDirection;
import com.nickyfantasy.marblesplash.Constant.MarbleColor;
import com.nickyfantasy.marblesplash.Constant.MarbleState;

public class Marble extends GameObject {

	static final int FLYING_SPEED = Dimen.apply(1600);
	static final int FLYING_IN_BORDER_SPEED = Dimen.apply(1000);
	static final int BOMB_FLYING_SPEED = Dimen.apply(1500);
	static final int TOUCH_LEFT_PADDING = Dimen.apply(100);
	static final int TOUCH_TOP_PADDING = Dimen.apply(130);
	static final int TOUCH_BOTTOM_PADDING = Dimen.apply(70);
	int mFallingSpeed; //px per sec
	int mState = MarbleState.FALLING;
	int[] mTouchStartPos = new int[2];
	int[] mEndStartPos = new int[2];
	int[] mFlyingSpeed = new int[2];
	int mFlyingDirection;
	int mColor;
	float mBombFlyingAngleRatio;
	World mWorld;
	
	public Marble(int color, int speed, int x, World world) {
		super(null, 0, 0);
		setMarbleProperties(color, speed, x);
		mWorld = world;
	}

	@Override
	public void updateState(float deltaTime) {
		super.updateState(deltaTime);
		if (mState == MarbleState.FLYING) {
			
			boolean touchXBorder = false;
			boolean touchYBorder = false;
			
			switch (mFlyingDirection) {
				case FlyingDirection.TOP_LEFT:
					touchXBorder = mPosX <= 0;
					touchYBorder = mPosY <= 0;
					
					if (touchXBorder) {
						mPosY -= deltaTime * FLYING_IN_BORDER_SPEED;
						touchYBorder = mPosY <= 0;
					} else if (touchYBorder) {
						mPosX -= deltaTime * FLYING_IN_BORDER_SPEED;
						touchXBorder = mPosX <= 0;
					} else {
						mPosX += (deltaTime * mFlyingSpeed[0]);
						mPosY += (deltaTime * mFlyingSpeed[1]);
						touchXBorder = mPosX <= 0;
						touchYBorder = mPosY <= 0;
					}
					
					if (touchXBorder) mPosX = 0;
					if (touchYBorder) mPosY = 0;
					break;
				case FlyingDirection.TOP_RIGHT:
					touchXBorder = mPosX >= Dimen.deviceWidth - mWidth;
					touchYBorder = mPosY <= 0;

					if (touchXBorder) {
						mPosY -= deltaTime * FLYING_IN_BORDER_SPEED;
						touchYBorder = mPosY <= 0;
					} else if (touchYBorder) {
						mPosX += deltaTime * FLYING_IN_BORDER_SPEED;
						touchXBorder = mPosX >= Dimen.deviceWidth - mWidth;
					} else {
						mPosX += (deltaTime * mFlyingSpeed[0]);
						mPosY += (deltaTime * mFlyingSpeed[1]);
						touchXBorder = mPosX >= Dimen.deviceWidth - mWidth;
						touchYBorder = mPosY <= 0;
					}
					
					if (touchXBorder) mPosX = Dimen.deviceWidth - mWidth;
					if (touchYBorder) mPosY = 0;
					break;
				case FlyingDirection.BOTTOM_LEFT:
					touchXBorder = mPosX <= 0;
					touchYBorder = mPosY >= Dimen.deviceHeight - mHeight;
					
					if (touchXBorder) {
						mPosY += deltaTime * FLYING_IN_BORDER_SPEED;
						touchYBorder = mPosY >= Dimen.deviceHeight - mHeight;
					} else if (touchYBorder) {
						mPosX -= deltaTime * FLYING_IN_BORDER_SPEED;
						touchXBorder = mPosX <= 0;
					} else {
						mPosX += (deltaTime * mFlyingSpeed[0]);
						mPosY += (deltaTime * mFlyingSpeed[1]);
						touchXBorder = mPosX <= 0;
						touchYBorder = mPosY >= Dimen.deviceHeight - mHeight;
					}
					
					if (touchXBorder) mPosX = 0;
					if (touchYBorder) mPosY = Dimen.deviceHeight - mHeight;
					break;
				case FlyingDirection.BOTTOM_RIGHT:
					touchXBorder = mPosX >= Dimen.deviceWidth - mWidth;
					touchYBorder = mPosY >= Dimen.deviceHeight - mHeight;
					
					if (touchXBorder) {
						mPosY += deltaTime * FLYING_IN_BORDER_SPEED;
						touchYBorder = mPosY >= Dimen.deviceHeight - mHeight;
					} else if (touchYBorder) {
						mPosX += deltaTime * FLYING_IN_BORDER_SPEED;
						touchXBorder = mPosX >= Dimen.deviceWidth - mWidth;
					} else {
						mPosX += (deltaTime * mFlyingSpeed[0]);
						mPosY += (deltaTime * mFlyingSpeed[1]);
						touchXBorder = mPosX >= Dimen.deviceWidth - mWidth;
						touchYBorder = mPosY >= Dimen.deviceHeight - mHeight;
					}
					
					if (touchXBorder) mPosX = Dimen.deviceWidth - mWidth;
					if (touchYBorder) mPosY = Dimen.deviceHeight - mHeight;
					break;
			}

			if (touchXBorder && touchYBorder) {
				mState = MarbleState.DESTROYED;
				if (mFlyingDirection != mColor) {
					switch (mFlyingDirection) {
						case FlyingDirection.TOP_LEFT:
							mWorld.mBlueNom.mLife--;
							break;
						case FlyingDirection.TOP_RIGHT:
							mWorld.mRedNom.mLife--;
							break;
						case FlyingDirection.BOTTOM_LEFT:
							mWorld.mYellowNom.mLife--;
							break;
						case FlyingDirection.BOTTOM_RIGHT:
							mWorld.mGreenNom.mLife--;
							break;
					}
				}
			}
		} else if (mState == MarbleState.BOMBING) {
			float deltaY = deltaTime * BOMB_FLYING_SPEED;
			switch (mColor) {
				case FlyingDirection.TOP_LEFT:
					mPosY -= deltaY;
					mPosX -= deltaY * mBombFlyingAngleRatio;
					if (mPosY <= 0 && mPosX <= 0) {
						mState = MarbleState.DESTROYED;
						mWorld.mBlueNom.mLife--;
					}
					break;
				case FlyingDirection.TOP_RIGHT:
					mPosY -= deltaY;
					mPosX += deltaY * mBombFlyingAngleRatio;
					if (mPosY <= 0 && mPosX >= Dimen.deviceWidth - mWidth) {
						mState = MarbleState.DESTROYED;
						mWorld.mRedNom.mLife--;
					}
					break;
				case FlyingDirection.BOTTOM_LEFT:
					mPosX -= deltaY;
					if (mPosX <= 0) {
						mState = MarbleState.DESTROYED;
						mWorld.mYellowNom.mLife--;
					}
					break;
				case FlyingDirection.BOTTOM_RIGHT:
					mPosX += deltaY;
					if (mPosX >= Dimen.deviceWidth - mWidth) {
						mState = MarbleState.DESTROYED;
						mWorld.mGreenNom.mLife--;
					}
					break;
			}
		} else {
			mPosY += Math.round(deltaTime * mFallingSpeed);
			if (mPosY > Dimen.deviceHeight - mHeight) {
				mPosY = Dimen.deviceHeight - mHeight;
				setMarbleState(MarbleState.BOMBING);
				if (mColor == FlyingDirection.TOP_LEFT) {
					mBombFlyingAngleRatio = 1.0f * mPosX / (Dimen.deviceHeight - mHeight);
				} else if (mColor == FlyingDirection.TOP_RIGHT) {
					mBombFlyingAngleRatio = 1.0f * (Dimen.deviceWidth - mPosX - mWidth) / (Dimen.deviceHeight - mHeight);
				}
			}
		}
	}
	
	public void computeFlyingSpeed() {
		int swipeDeltaX = mEndStartPos[0] - mTouchStartPos[0];
		int swipeDeltaY = mEndStartPos[1] - mTouchStartPos[1];
		double scaleRatio = Math.sqrt(FLYING_SPEED * FLYING_SPEED
				/ (swipeDeltaX * swipeDeltaX + swipeDeltaY * swipeDeltaY));
		mFlyingSpeed[0] = (int)(swipeDeltaX * scaleRatio);
		mFlyingSpeed[1] = (int)(swipeDeltaY * scaleRatio);
		if (swipeDeltaX < 0) {
			if (swipeDeltaY < 0) {
				mFlyingDirection = FlyingDirection.TOP_LEFT;
			} else {
				mFlyingDirection = FlyingDirection.BOTTOM_LEFT;
			}
		} else {
			if (swipeDeltaY < 0) {
				mFlyingDirection = FlyingDirection.TOP_RIGHT;
			} else {
				mFlyingDirection = FlyingDirection.BOTTOM_RIGHT;
			}
		}
	}

	public void setMarbleProperties(int color, int speed, int x) {
		mState = MarbleState.FALLING;
		mPosY = -Assets.redMarble.getHeight();
		mPosX = x;
		mFallingSpeed = speed;
		mColor = color;
		setMarbleBitmap();
		mWidth = mPixmap.getWidth();
		mHeight = mPixmap.getHeight();;
	}
	
	public void setMarbleState(int state) {
		if (mState != state) {
			mState = state;
			setMarbleBitmap();
		}
	}
	
	private void setMarbleBitmap() {
		if (mState == MarbleState.FALLING) {
			switch (mColor) {
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
		} else if (mState == MarbleState.BOMBING) {
			switch (mColor) {
			case MarbleColor.RED:
				mPixmap = Assets.redMarbleB;
				break;
			case MarbleColor.BLUE:
				mPixmap = Assets.blueMarbleB;
				break;
			case MarbleColor.YELLOW:
				mPixmap = Assets.yellowMarbleB;
				break;
			case MarbleColor.GREEN:
				mPixmap = Assets.greenMarbleB;
				break;
			}
		} else {
			switch (mColor) {
			case MarbleColor.RED:
				mPixmap = Assets.redMarbleP;
				break;
			case MarbleColor.BLUE:
				mPixmap = Assets.blueMarbleP;
				break;
			case MarbleColor.YELLOW:
				mPixmap = Assets.yellowMarbleP;
				break;
			case MarbleColor.GREEN:
				mPixmap = Assets.greenMarbleP;
				break;
			}
		}
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
