package com.nickyfantasy.marblesplash;

import java.util.List;

import com.nickyfantasy.marblesplash.framework.Game;
import com.nickyfantasy.marblesplash.framework.Graphics;
import com.nickyfantasy.marblesplash.framework.Input.TouchEvent;
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
	private GameObject mBlueNom;
	private GameObject mRedNom;
	private GameObject mYellowNom;
	private GameObject mGreenNom;
	
    public GameScreen(Game game) {
        super(game);
        mBlueNom = new GameObject(Assets.blueNom, 0, 0);
        mRedNom = new GameObject(Assets.redNom, Dimen.deviceWidth - Assets.redNom.getWidth(), 0);
        mYellowNom = new GameObject(Assets.yellowNom, 0, Dimen.deviceHeight - Assets.yellowNom.getHeight());
        mGreenNom = new GameObject(Assets.greenNom, Dimen.deviceWidth - Assets.greenNom.getWidth(), Dimen.deviceHeight - Assets.greenNom.getHeight());
    }   

    public void update(float deltaTime) {
    	
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();       
        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            mWorld.updateWithTouchEvent(event);
        }

    	mWorld.update(deltaTime);
    }

    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.clear();
        for (Row row : mWorld.mRows) {
        	for (Marble marble : row.mMarbleList) {
        		if (marble != null) {
        			if (marble.mState != MarbleState.DESTROYED) {
        				g.drawGameObject(marble);
        			}
        		} else {
        			break;
        		}
        	}
        }
        g.drawGameObject(mBlueNom);
        g.drawGameObject(mRedNom);
        g.drawGameObject(mYellowNom);
        g.drawGameObject(mGreenNom);
    }

    public void pause() {        
        Settings.save(game.getFileIO());
    }

    public void resume() {

    }

    public void dispose() {

    }
}