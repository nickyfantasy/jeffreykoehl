package com.nickyfantasy.marblesplash;

public class Marble {
	
	public enum MarbleColor {
		RED(1), BLUE(2), YELLOW(3), GREEN(4);
		
		private int id;
		
		MarbleColor(int id) {
			this.id = id;
		}
		
		int getId() {
			return id;
		}
	}

}
