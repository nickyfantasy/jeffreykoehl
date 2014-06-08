package com.nickyfantasy.marblesplash;

import com.nickyfantasy.marblesplash.framework.Pixmap;

public interface TouchableItem {
	
	boolean isTouchInBounds(int x, int y);
	int getX();
	int getY();
	int getWidth();
	int getHeight();
	Pixmap getPixmap();
	void updateState(float deltaTime);
	void setPressing(boolean pressing);

}
