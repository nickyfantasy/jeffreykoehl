package com.nickyfantasy.marblesplash;

import com.nickyfantasy.marblesplash.framework.Screen;
import com.nickyfantasy.marblesplash.framework.impl.AndroidGame;

public class MarbleSplashGame extends AndroidGame {
    public Screen getStartScreen() {
        return new LoadingScreen(this); 
    }
} 
