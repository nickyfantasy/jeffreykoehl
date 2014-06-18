package com.nickyfantasy.marblesplash;

import java.util.Random;

import android.util.Log;

public class World {
	
	static final float TICK_INITIAL = 0.5f; //secs to add a new marble
	static int ROW_WIDTH = Dimen.apply(200);
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

}
