package com.nickyfantasy.marblesplash;

public interface ClickableItem {
	
	boolean isTouchInBounds(int x, int y);
	int getX();
	int getY();
	int getWidth();
	int getHeight();

}
