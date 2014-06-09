package com.nickyfantasy.marblesplash.framework;


public interface IGameObject {
	
	boolean isTouchInBounds(int x, int y);
	int getX();
	int getY();
	int getWidth();
	int getHeight();
	Pixmap getPixmap();
	void updateState(float deltaTime);
	void setPressing(boolean pressing);

}
