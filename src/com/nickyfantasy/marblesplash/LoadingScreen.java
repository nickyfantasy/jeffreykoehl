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
        Assets.background = g.newPixmap("main_bg3.png", PixmapFormat.RGB565, true);
        Assets.redMarble = g.newPixmap("red.png", PixmapFormat.RGB565, false);
        Assets.greenMarble = g.newPixmap("green.png", PixmapFormat.RGB565, false);
        Assets.yellowMarble = g.newPixmap("yellow.png", PixmapFormat.RGB565, false);
        Assets.blueMarble = g.newPixmap("blue.png", PixmapFormat.RGB565, false);
        Assets.play = g.newPixmap("play.png", PixmapFormat.RGB565, false);
        Assets.lv1 = g.newPixmap("lv1.png", PixmapFormat.RGB565, false);
        Assets.lv2 = g.newPixmap("lv2.png", PixmapFormat.RGB565, false);
        Assets.lv3 = g.newPixmap("lv3.png", PixmapFormat.RGB565, false);
        Assets.selectLevel = g.newPixmap("select_level.png", PixmapFormat.RGB565, false);
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
