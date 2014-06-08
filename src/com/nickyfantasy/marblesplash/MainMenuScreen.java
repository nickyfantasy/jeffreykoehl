package com.nickyfantasy.marblesplash;

import java.util.List;

import android.util.Log;

import com.nickyfantasy.marblesplash.Marble.MarbleColor;
import com.nickyfantasy.marblesplash.framework.Game;
import com.nickyfantasy.marblesplash.framework.Graphics;
import com.nickyfantasy.marblesplash.framework.Input.TouchEvent;
import com.nickyfantasy.marblesplash.framework.Pixmap;
import com.nickyfantasy.marblesplash.framework.Screen;


public class MainMenuScreen extends Screen {
    public MainMenuScreen(Game game) {
        super(game);               
    }   

    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();       
        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
//                if(inBounds(event, 64, 220, 192, 42) ) {
//                    game.setScreen(new GameScreen(game));
//                    if(Settings.soundEnabled)
//                        Assets.click.play(1);
//                    return;
//                }
            }
        }
    }
    
    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if(event.x > x && event.x < x + width - 1 && 
           event.y > y && event.y < y + height - 1) 
            return true;
        else
            return false;
    }

    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.play, (g.getWidth() - Assets.play.getWidth()) / 2, 
        		(g.getHeight() - Assets.play.getHeight()) / 2);

        for (int i = 0; i < 6; i++) {
        	int marbleColor = i % 4 + 1; 
        	Pixmap marbleAsset = null;
        	if (marbleColor == MarbleColor.RED.getId()) marbleAsset = Assets.redMarble;
        	if (marbleColor == MarbleColor.YELLOW.getId()) marbleAsset = Assets.yellowMarble;
        	if (marbleColor == MarbleColor.GREEN.getId()) marbleAsset = Assets.greenMarble;
        	if (marbleColor == MarbleColor.BLUE.getId()) marbleAsset = Assets.blueMarble;
        			
        	g.drawPixmap(marbleAsset, Assets.play.getWidth() * i, Assets.play.getHeight() * i);
        }
    }

    public void pause() {        
        Settings.save(game.getFileIO());
    }

    public void resume() {

    }

    public void dispose() {

    }
}