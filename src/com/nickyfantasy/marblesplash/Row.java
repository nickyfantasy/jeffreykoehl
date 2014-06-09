package com.nickyfantasy.marblesplash;

import com.nickyfantasy.marblesplash.framework.Pixmap;

public class Row extends GameObject {

	// LinkedList<Marble> mMarbleList = new LinkedList<Marble>();
	Marble[] mMarbleList = new Marble[10];
	int mMarbleIndexToInsert = 0;

	public Row(Pixmap pixmap, int width, int height, int x, int y) {
		super(pixmap, width, height, x, y);
	}

	@Override
	public void updateState(float deltaTime) {
		super.updateState(deltaTime);
		for (Marble marble : mMarbleList) {
			if (marble != null) {
				marble.updateState(deltaTime);
			} else {
				break;
			}
		}
	}
	
	public void insertMarble(Marble marble) {
		mMarbleList[mMarbleIndexToInsert++] = marble;
		if (mMarbleIndexToInsert == 10) mMarbleIndexToInsert = 0;
		marble.mPosX = mPosX;
	}

}
