package com.nickyfantasy.marblesplash;

public class Dimen {
	
	public static int deviceWidth;
	public static int deviceHeight;
	public static float scaleRatio; //baseline as 1080
	
	public static int apply(int dimen) {
		return (int) (dimen * scaleRatio);
	}

}
