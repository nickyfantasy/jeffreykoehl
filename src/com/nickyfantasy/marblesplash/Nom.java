package com.nickyfantasy.marblesplash;

import com.nickyfantasy.marblesplash.framework.Pixmap;

public class Nom extends GameObject {
	
	int mType;
	int mLife = 3;

	public Nom(int type, Pixmap pixmap, int x, int y) {
		super(pixmap, x, y);
		mType = type;
	}

}
