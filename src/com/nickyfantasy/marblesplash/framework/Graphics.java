package com.nickyfantasy.marblesplash.framework;

import com.nickyfantasy.marblesplash.GameObject;

public interface Graphics {
    public static enum PixmapFormat {
        ARGB8888, ARGB4444, RGB565
    }

    public Pixmap newPixmap(String fileName, PixmapFormat format, boolean isBg);

    public void clear();
    
    public void drawARGB(int a, int r, int g, int b);

    public void drawPixel(int x, int y, int color);

    public void drawLine(int x, int y, int x2, int y2, int color);

    public void drawRect(int x, int y, int width, int height, int color);

    public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY,
            int srcWidth, int srcHeight);

    public void drawPixmap(Pixmap pixmap, int x, int y);
    
    public void drawPixmapBg(Pixmap pixmap);
    
    public void drawGameObject(GameObject gameObject);
}
