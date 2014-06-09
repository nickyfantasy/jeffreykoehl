package com.nickyfantasy.marblesplash;

import java.util.Random;

public class World {
	
	static final float TICK_INITIAL = 3f; //3 secs to add a new marble
	
	public Row[] mRows = new Row[6];
	private float tickTime = 0;
	private Random mRandom = new Random();
	
	public World() {
		for (int i = 0; i < 6; i++) {
			//TODO
			mRows[i] = new Row(null, Assets.redMarble.getWidth(), Utils.mDeviceHeight, Assets.redMarble.getWidth() * (i + 1), 0);
		}
	}
	
	public void update(float deltaTime) {
		for (Row row : mRows) {
			row.updateState(deltaTime);
		}
		tickTime =+ deltaTime;
		while (tickTime > TICK_INITIAL) {
            tickTime -= TICK_INITIAL;
            Marble marble = MarbleFactory.createRandomMarble();
            insertMarbleToRandomRow(marble);
		}
		
	}
	
	public void insertMarbleToRandomRow(Marble marble) {
		int rowIndex = mRandom.nextInt(6);
		mRows[rowIndex].insertMarble(marble);
	}
	
	public void getAllMarblesInScreen() {
		
	}

}
