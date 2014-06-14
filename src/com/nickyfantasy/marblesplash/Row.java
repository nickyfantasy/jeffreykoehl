package com.nickyfantasy.marblesplash;

import android.util.Log;

import com.nickyfantasy.marblesplash.framework.Pixmap;

public class Row extends GameObject {

	// LinkedList<Marble> mMarbleList = new LinkedList<Marble>();
	Marble[] mMarbleList;
	int mMarbleIndexToInsert = 0;
	int mDestroyedMarbleIndex = 0;

	public Row(Pixmap pixmap, int width, int height, int x, int y, int maxSize) {
		super(pixmap, width, height, x, y);
		mMarbleList = new Marble[maxSize];
	}

	@Override
	public void updateState(float deltaTime) {
		super.updateState(deltaTime);
		for (Marble marble : mMarbleList) {
			if (marble != null) {
				if (!marble.mDestroyed) {
					marble.updateState(deltaTime);
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
		return new Marble(color, speed, mPosX);
	}

	private Marble reuseMarble(int color, int speed) {
		for (int i = 0; i < mMarbleList.length; i++) {
			Marble marble = mMarbleList[i];
			if (marble == null)
				return null;
			if (marble.mDestroyed) {
				marble.setMarbleProperties(color, speed);
				return marble;
			}
		}
		return null;
	}

}
