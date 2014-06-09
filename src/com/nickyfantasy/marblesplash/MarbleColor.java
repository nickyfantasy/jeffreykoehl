package com.nickyfantasy.marblesplash;

public enum MarbleColor {
	RED(1), BLUE(2), YELLOW(3), GREEN(4);

	private int id;

	MarbleColor(int id) {
		this.id = id;
	}

	int getId() {
		return id;
	}

	static MarbleColor getColor(int id) {
		switch (id) {
		case 1:
			return RED;
		case 2:
			return BLUE;
		case 3:
			return YELLOW;
		case 4:
			return GREEN;
		}
		return null;
	}
}
