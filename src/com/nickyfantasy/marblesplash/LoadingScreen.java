package com.nickyfantasy.marblesplash;

import com.nickyfantasy.marblesplash.framework.Game;
import com.nickyfantasy.marblesplash.framework.Graphics;
import com.nickyfantasy.marblesplash.framework.Graphics.PixmapFormat;
import com.nickyfantasy.marblesplash.framework.Screen;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }

    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.background = g.newPixmap("main_bg.jpg", PixmapFormat.RGB565);
        Assets.redMarble = g.newPixmap("red.png", PixmapFormat.ARGB4444);
        Assets.greenMarble = g.newPixmap("green.png", PixmapFormat.ARGB4444);
        Assets.yellowMarble = g.newPixmap("yellow.png", PixmapFormat.ARGB4444);
        Assets.blueMarble = g.newPixmap("blue.png", PixmapFormat.ARGB4444);
        Assets.play = g.newPixmap("play.png", PixmapFormat.ARGB4444);
        Assets.lv1 = g.newPixmap("lv1.png", PixmapFormat.ARGB4444);
        Assets.lv2 = g.newPixmap("lv2.png", PixmapFormat.ARGB4444);
        Assets.lv3 = g.newPixmap("lv3.png", PixmapFormat.ARGB4444);
        Assets.selectLevel = g.newPixmap("select_level.png", PixmapFormat.ARGB4444);
        Assets.click = game.getAudio().newSound("click.ogg");
        Assets.eat = game.getAudio().newSound("eat.ogg");
        Assets.bitten = game.getAudio().newSound("bitten.ogg");
//        Settings.load(game.getFileIO());
        game.setScreen(new GameScreen(game));
    }
    
    public void present(float deltaTime) {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void dispose() {

    }
}
