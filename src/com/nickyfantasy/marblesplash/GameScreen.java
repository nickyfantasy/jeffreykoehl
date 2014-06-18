package com.nickyfantasy.marblesplash;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;

import com.nickyfantasy.marblesplash.framework.Game;
import com.nickyfantasy.marblesplash.framework.Graphics;
import com.nickyfantasy.marblesplash.framework.Input.TouchEvent;
import com.nickyfantasy.marblesplash.framework.Screen;
import com.nickyfantasy.marblesplash.framework.impl.AndroidGraphics;
import com.nickyfantasy.marblesplash.framework.impl.AndroidPixmap;

public class GameScreen extends Screen {
    enum GameState {
        Ready,
        Running,
        Paused,
        GameOver
    }
    private World mWorld = new World();
	private GameState state = GameState.Running;
	
    public GameScreen(Game game) {
        super(game);
    }   

    public void update(float deltaTime) {
    	mWorld.update(deltaTime);
    	
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();       
        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
//            if(event.type == TouchEvent.TOUCH_UP) {
//                if(mLv1Button.isTouchInBounds(event.x, event.y)) {
//                    game.setScreen(new GameScreen(game));
//                    if(Settings.soundEnabled)
//                        Assets.click.play(1);
//                    return;
//                }
//            }
        }
    }

    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.clear();
        for (Row row : mWorld.mRows) {
        	for (Marble marble : row.mMarbleList) {
        		if (marble != null) {
        			if (!marble.mDestroyed) {
        				g.drawGameObject(marble);
        			}
        		} else {
        			break;
        		}
        	}
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