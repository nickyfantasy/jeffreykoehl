package com.nickyfantasy.marblesplash.framework;

public interface Game {
    public Input getInput();

    public FileIO getFileIO();

    public Graphics getGraphics();

    public Audio getAudio();

    public void setScreen(Screen screen, int backgroundResId);

    public Screen getCurrentScreen();

    public Screen getStartScreen();
}