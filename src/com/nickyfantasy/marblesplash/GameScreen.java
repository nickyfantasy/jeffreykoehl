package com.nickyfantasy.marblesplash;

import java.util.List;

import com.nickyfantasy.marblesplash.Constant.MarbleState;
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
	private static int LIFE_Y_OFFSET = Dimen.apply(230);
	private static int LIFE_SIZE = Dimen.apply(60);
	
    public GameScreen(Game game) {
        super(game);
        
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
        g.drawGameObject(mWorld.mBlueNom);
        g.drawGameObject(mWorld.mRedNom);
        g.drawGameObject(mWorld.mYellowNom);
        g.drawGameObject(mWorld.mGreenNom);
        drawLife(g);
        
    }
    
	public void drawLife(Graphics g) {
		

		if (mWorld.mBlueNom.mLife <= 0) {
			state = GameState.GameOver;
		} else {
			if (mWorld.mBlueNom.mLife >= 1) {
				g.drawPixmap(Assets.life, LIFE_SIZE * 0,
						LIFE_Y_OFFSET);
			}
			if (mWorld.mBlueNom.mLife >= 2) {
				g.drawPixmap(Assets.life, LIFE_SIZE * 1, LIFE_Y_OFFSET);
			}
			if (mWorld.mBlueNom.mLife >= 3) {
				g.drawPixmap(Assets.life, LIFE_SIZE * 2, LIFE_Y_OFFSET);
			}
		}
		
		if (mWorld.mRedNom.mLife <= 0) {
			state = GameState.GameOver;
		} else {
			if (mWorld.mRedNom.mLife >= 1) {
				g.drawPixmap(Assets.life, Dimen.deviceWidth - LIFE_SIZE * 1,
						LIFE_Y_OFFSET);
			}
			if (mWorld.mRedNom.mLife >= 2) {
				g.drawPixmap(Assets.life, Dimen.deviceWidth - LIFE_SIZE * 2, LIFE_Y_OFFSET);
			}
			if (mWorld.mRedNom.mLife >= 3) {
				g.drawPixmap(Assets.life, Dimen.deviceWidth - LIFE_SIZE * 3, LIFE_Y_OFFSET);
			}
		}
		
		if (mWorld.mYellowNom.mLife <= 0) {
			state = GameState.GameOver;
		} else {
			if (mWorld.mYellowNom.mLife >= 1) {
				g.drawPixmap(Assets.life, LIFE_SIZE * 0 ,
						Dimen.deviceHeight - LIFE_Y_OFFSET - LIFE_SIZE);
			}
			if (mWorld.mYellowNom.mLife >= 2) {
				g.drawPixmap(Assets.life, LIFE_SIZE * 1, Dimen.deviceHeight - LIFE_Y_OFFSET - LIFE_SIZE);
			}
			if (mWorld.mYellowNom.mLife >= 3) {
				g.drawPixmap(Assets.life, LIFE_SIZE * 2, Dimen.deviceHeight - LIFE_Y_OFFSET - LIFE_SIZE);
			}
		}
		
		if (mWorld.mGreenNom.mLife <= 0) {
			state = GameState.GameOver;
		} else {
			if (mWorld.mGreenNom.mLife >= 1) {
				g.drawPixmap(Assets.life, Dimen.deviceWidth - LIFE_SIZE * 1,
						Dimen.deviceHeight - LIFE_Y_OFFSET - LIFE_SIZE);
			}
			if (mWorld.mGreenNom.mLife >= 2) {
				g.drawPixmap(Assets.life, Dimen.deviceWidth - LIFE_SIZE * 2, Dimen.deviceHeight - LIFE_Y_OFFSET - LIFE_SIZE);
			}
			if (mWorld.mGreenNom.mLife >= 3) {
				g.drawPixmap(Assets.life, Dimen.deviceWidth - LIFE_SIZE * 3, Dimen.deviceHeight - LIFE_Y_OFFSET - LIFE_SIZE);
			}
		}
	}
    
    public void drawBlueLife() {
    	
    }
    
    public void drawRedLife() {
    	
    }
    
    public void drawYellowLife() {
    	
    }
    
    public void drawGreenLife() {
    	
    }

    public void pause() {        
        Settings.save(game.getFileIO());
    }

    public void resume() {

    }

    public void dispose() {

    }
}