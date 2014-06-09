package com.nickyfantasy.marblesplash;

import java.util.Random;

import android.util.Log;

public class World {
	
	static final float TICK_INITIAL = 0.5f; //secs to add a new marble
	
	public Row[] mRows = new Row[6];
	private float tickTime = 0;
	private Random mRandom = new Random();
	private int mMarbleSpeed = Dimen.apply(300);
	
	public World() {
		for (int i = 0; i < 6; i++) {
			//TODO
			mRows[i] = new Row(null, Assets.redMarble.getWidth(), Dimen.deviceHeight, Assets.redMarble.getWidth() * (i + 1), 0);
		}
	}
	
	public void update(float deltaTime) {
		for (Row row : mRows) {
			row.updateState(deltaTime);
		}
		tickTime += deltaTime;
		while (tickTime > TICK_INITIAL) {
            tickTime -= TICK_INITIAL;
            Marble marble = MarbleFactory.createRandomMarble();
            marble.mSpeed = mMarbleSpeed;
            mRows[0].insertMarble(marble);
            marble = MarbleFactory.createRandomMarble();
            marble.mSpeed = mMarbleSpeed;
            mRows[1].insertMarble(marble);
            marble = MarbleFactory.createRandomMarble();
            marble.mSpeed = mMarbleSpeed;
            mRows[2].insertMarble(marble);
            marble = MarbleFactory.createRandomMarble();
            marble.mSpeed = mMarbleSpeed;
            mRows[3].insertMarble(marble);
            marble = MarbleFactory.createRandomMarble();
            marble.mSpeed = mMarbleSpeed;
            mRows[4].insertMarble(marble);
            marble = MarbleFactory.createRandomMarble();
            marble.mSpeed = mMarbleSpeed;
            mRows[5].insertMarble(marble);
//            insertMarbleToRandomRow(marble);
		}
		
	}
	
	public void insertMarbleToRandomRow(Marble marble) {
		int rowIndex = mRandom.nextInt(6);
		mRows[rowIndex].insertMarble(marble);
	}
	
	public void getAllMarblesInScreen() {
		
	}

}
