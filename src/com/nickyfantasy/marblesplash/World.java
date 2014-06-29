package com.nickyfantasy.marblesplash;

import java.util.Random;

import com.nickyfantasy.marblesplash.Constant.MarbleColor;
import com.nickyfantasy.marblesplash.Constant.MarbleState;
import com.nickyfantasy.marblesplash.framework.Input.TouchEvent;

public class World {
	
	static final float TICK_INITIAL = 1.2f; //secs to add a new marble
    static final float TICK_DECREMENT = 0.02f;
	static final int MIN_SWIPE_DIST= Dimen.apply(30);
	static final int ROW_WIDTH = Dimen.apply(200);
	Row[] mRows = new Row[6];
	Nom mBlueNom;
	Nom mRedNom;
	Nom mYellowNom;
	Nom mGreenNom;
	boolean mGameOver;
	private float tickTime = 0;
    private float tick = TICK_INITIAL;
	private Random mRandom = new Random();
	private int mMarbleSpeed = Dimen.apply(200);
	private int mMarbleIncSpeed = Dimen.apply(5);
	private int mMaxMarbleInRow = 12;
	private Marble mReadyMarble;
	private boolean mInsertLeft;
	
	public World() {
		boolean narrow = (1.0f * Dimen.deviceWidth / Dimen.deviceHeight) <= 1.5;
		if (narrow) {
			
		} else {
			
		}
		int leftRightSpace = (Dimen.deviceWidth - ROW_WIDTH * 6) / 2;
		for (int i = 0; i < 6; i++) {
			//TODO
			mRows[i] = new Row(null, ROW_WIDTH, Dimen.deviceHeight, ROW_WIDTH * i + leftRightSpace, 0, mMaxMarbleInRow, this);
		}
		mBlueNom = new Nom(Constant.NomType.BLUE, Assets.blueNom, 0, 0);
        mRedNom = new Nom(Constant.NomType.RED, Assets.redNom, Dimen.deviceWidth - Assets.redNom.getWidth(), 0);
        mYellowNom = new Nom(Constant.NomType.YELLOW, Assets.yellowNom, 0, Dimen.deviceHeight - Assets.yellowNom.getHeight());
        mGreenNom = new Nom(Constant.NomType.GREEN, Assets.greenNom, Dimen.deviceWidth - Assets.greenNom.getWidth(), Dimen.deviceHeight - Assets.greenNom.getHeight());
	}
	
	public void update(float deltaTime) {
		tickTime += deltaTime;
		if (tickTime > tick) {
            tickTime -= tick;
            tick *= 0.99f;
//            insertRandomMarble(0);
//            insertRandomMarble(1);
//            insertRandomMarble(2);
//            insertRandomMarble(3);
//            insertRandomMarble(4);
//            insertRandomMarble(5);
//            insertMarbleToRandomRow(marble);
//            insertRandomMarbleToRandomRow();
            if (mInsertLeft) {
            	insertRandomMarbleToLeftSide();
            	mInsertLeft = false;
            } else {
            	insertRandomMarbleToRightSide();
            	mInsertLeft = true;
            }
		}

		mReadyMarble = null;
		for (Row row : mRows) {
			row.updateState(deltaTime);
			if (mReadyMarble == null) {
				mReadyMarble = row.mBottomMarble;
			} else if (row.mBottomMarble != null && row.mBottomMarble.mPosY > mReadyMarble.mPosY) {
				mReadyMarble = row.mBottomMarble;
			}
		}
		if (mReadyMarble != null && mReadyMarble.mState != MarbleState.PRESSED) {
			mReadyMarble.setMarbleState(MarbleState.READY);
		}
		
	}
	
	private void insertRandomMarbleToLeftSide() {
		int rowIndex = mRandom.nextInt(3);
		insertRandomMarble(rowIndex);
	}
	
	private void insertRandomMarbleToRightSide() {
		int rowIndex = mRandom.nextInt(3) + 3;
		insertRandomMarble(rowIndex);
	}
	
	private void insertRandomMarbleToRandomRow() {
		int rowIndex = mRandom.nextInt(6);
		insertRandomMarble(rowIndex);
	}
	
	private void insertMarbleToRandomRow(int color) {
		int rowIndex = mRandom.nextInt(6);
		insertMarble(rowIndex, color);
	}
	
	private void insertRandomMarble(int rowIndex) {
		int color = mRandom.nextInt(MarbleColor.MARBLE_TYPE_COUNT) + 1;
		insertMarble(rowIndex, color);
	}
	
	private void insertMarble(int rowIndex, int color) {
		mRows[rowIndex].insertMarble(color, mMarbleSpeed);
		mMarbleSpeed += mMarbleIncSpeed;
	}

	
	public void getAllMarblesInScreen() {
		
	}
	
	public void hurtNom(Nom nom) {
		nom.mLife--;
		if (nom.mLife == 0) mGameOver = true;
	}
	
	public void updateWithTouchEvent(TouchEvent event) {
    	for (Row row : mRows) {
            if (row.isTouchInBounds(event.x, event.y)) {
            	for (Marble marble : row.mMarbleList) {
            		if (marble != null) {
            			if (marble.isTouchInBounds(event.x, event.y)) {
            				if (event.type == TouchEvent.TOUCH_DRAGGED && marble.mState == MarbleState.PRESSED) {
            					if (marble.mTouchStartPos[0] - event.x > MIN_SWIPE_DIST
            							|| marble.mTouchStartPos[0] - event.x < -MIN_SWIPE_DIST
            							|| marble.mTouchStartPos[1] - event.y > MIN_SWIPE_DIST
            							|| marble.mTouchStartPos[1] - event.y < -MIN_SWIPE_DIST) {
            						marble.setMarbleState(MarbleState.FLYING);
            						marble.mEndStartPos[0] = event.x;
            						marble.mEndStartPos[1] = event.y;
            						marble.computeFlyingSpeed();
            					}
            				} else if (event.type == TouchEvent.TOUCH_DOWN && marble.mState == MarbleState.READY) {
            					marble.setMarbleState(MarbleState.PRESSED);
            					marble.mTouchStartPos[0] = event.x;
            					marble.mTouchStartPos[1] = event.y;
            				} else if (event.type == TouchEvent.TOUCH_UP && marble.mState == MarbleState.PRESSED) {
            					marble.setMarbleState(MarbleState.READY);
            				}
            				
//            				break; //no need check other marbles
            			}
            		} else {
            			break;
            		}
            	}
//            	break; //no need check other rows (need to check because added padding)
            }
        }
	}

}
