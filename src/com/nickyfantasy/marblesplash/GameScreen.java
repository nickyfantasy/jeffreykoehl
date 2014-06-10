package com.nickyfantasy.marblesplash;

import java.util.List;

import android.graphics.Color;
import android.util.Log;

import com.nickyfantasy.marblesplash.framework.Game;
import com.nickyfantasy.marblesplash.framework.Graphics;
import com.nickyfantasy.marblesplash.framework.Input.TouchEvent;
import com.nickyfantasy.marblesplash.framework.Pixmap;
import com.nickyfantasy.marblesplash.framework.Screen;

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
//    	Log.e("ZZZ", "present");
        Graphics g = game.getGraphics();
        g.clear(Color.WHITE);
//        g.drawPixmap(Assets.background, 0, 0);
//        Log.e("ZZZ", "present1");
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

        Log.e("ZZZ", "present2");
    }

    public void pause() {        
        Settings.save(game.getFileIO());
    }

    public void resume() {

    }

    public void dispose() {

    }
}