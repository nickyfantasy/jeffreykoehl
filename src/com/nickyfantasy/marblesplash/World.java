package com.nickyfantasy.marblesplash;

import java.util.Random;

import android.util.Log;

public class World {
	
	static final float TICK_INITIAL = 0.5f; //secs to add a new marble
	
	public Row[] mRows = new Row[6];
	private float tickTime = 0;
	private Random mRandom = new Random();
	private int mMarbleSpeed = Dimen.apply(300);
	private int mMaxMarbleInRow = 12;
	
	public World() {
		for (int i = 0; i < 6; i++) {
			//TODO
			mRows[i] = new Row(null, Assets.redMarble.getWidth(), Dimen.deviceHeight, Assets.redMarble.getWidth() * (i + 1), 0, mMaxMarbleInRow);
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
	
	private void insertMarbleToRandomRow(MarbleColor color) {
		int rowIndex = mRandom.nextInt(6);
		insertMarble(rowIndex, color);
	}
	
	private void insertRandomMarble(int rowIndex) {
		int id = mRandom.nextInt(4) + 1;
		insertMarble(rowIndex, MarbleColor.getColor(id));
	}
	
	private void insertMarble(int rowIndex, MarbleColor color) {
		mRows[rowIndex].insertMarble(color, mMarbleSpeed);
	}

	
	public void getAllMarblesInScreen() {
		
	}

}
