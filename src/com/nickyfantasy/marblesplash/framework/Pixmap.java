package com.nickyfantasy.marblesplash.framework;

import com.nickyfantasy.marblesplash.framework.Graphics.PixmapFormat;

public interface Pixmap {
    public int getWidth();

    public int getHeight();

    public PixmapFormat getFormat();

    public void dispose();
}
