package com.nickyfantasy.marblesplash;

import java.util.Random;

import com.nickyfantasy.marblesplash.framework.Input.TouchEvent;

import android.util.Log;

public class World {
	
	static final float TICK_INITIAL = 0.7f; //secs to add a new marble
	static final int MIN_SWIPE_DIST= Dimen.apply(20);
	static final int ROW_WIDTH = Dimen.apply(200);
	public Row[] mRows = new Row[6];
	private float tickTime = 0;
	private Random mRandom = new Random();
	private int mMarbleSpeed = Dimen.apply(300);
	private int mMaxMarbleInRow = 12;
	
	public World() {
		boolean narrow = (1.0f * Dimen.deviceWidth / Dimen.deviceHeight) <= 1.5;
		if (narrow) {
			
		} else {
			
		}
		int leftRightSpace = (Dimen.deviceWidth - ROW_WIDTH * 6) / 2;
		for (int i = 0; i < 6; i++) {
			//TODO
			mRows[i] = new Row(null, ROW_WIDTH, Dimen.deviceHeight, ROW_WIDTH * i + leftRightSpace, 0, mMaxMarbleInRow);
		}
	}
	
	public void update(float deltaTime) {
		for (Row row : mRows) {
			row.updateState(deltaTime);
		}
		tickTime += deltaTime;
		while (tickTime > TICK_INITIAL) {
            tickTime -= TICK_INITIAL;
            insertRandomMarble(0);
            insertRandomMarble(1);
            insertRandomMarble(2);
            insertRandomMarble(3);
            insertRandomMarble(4);
            insertRandomMarble(5);
//            insertMarbleToRandomRow(marble);
//            insertRandomMarbleToRandomRow();
		}
		
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
	}

	
	public void getAllMarblesInScreen() {
		
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
            						marble.mState = MarbleState.FLYING;
            						marble.mEndStartPos[0] = event.x;
            						marble.mEndStartPos[1] = event.y;
            					}
            				} else if (event.type == TouchEvent.TOUCH_DOWN && marble.mState == MarbleState.FALLING) {
            					marble.mState = MarbleState.PRESSED;
            					marble.mTouchStartPos[0] = event.x;
            					marble.mTouchStartPos[1] = event.y;
            				} else if (event.type == TouchEvent.TOUCH_UP && marble.mState == MarbleState.PRESSED) {
            					marble.mState = MarbleState.FALLING;
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
