package com.nickyfantasy.marblesplash;

public interface Constant {

	public interface MarbleColor {
		static final int MARBLE_TYPE_COUNT = 4;
		static final int BLUE = FlyingDirection.TOP_LEFT;
		static final int RED = FlyingDirection.TOP_RIGHT;
		static final int YELLOW = FlyingDirection.BOTTOM_LEFT;
		static final int GREEN = FlyingDirection.BOTTOM_RIGHT;
	}

	public interface MarbleState {
		static final int FALLING = 0;
		static final int DESTROYED = 1;
		static final int READY = 2;
		static final int PRESSED = 3;
		static final int FLYING = 4;
		static final int BOMBING = 5;
	}

	public interface FlyingDirection {
		static final int TOP_LEFT = 1;
		static final int TOP_RIGHT = 2;
		static final int BOTTOM_LEFT = 3;
		static final int BOTTOM_RIGHT = 4;
	}

	public interface NomType {
		static final int BLUE = 1;
		static final int RED = 2;
		static final int YELLOW = 3;
		static final int GREEN = 4;
	}

}