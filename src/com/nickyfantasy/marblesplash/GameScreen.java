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
	

	private GameObject mGameOverText;
	private GameObject mRestartText;
	private GameObject mMainMenuText;
	
    public GameScreen(Game game) {
        super(game);
        mGameOverText = new GameObject(Assets.game_over, (Dimen.deviceWidth - Assets.game_over.getWidth()) / 2, 0);
        mRestartText = new GameObject(Assets.restart, (Dimen.deviceWidth - Assets.restart.getWidth()) / 2, Dimen.apply(400));
        mMainMenuText = new GameObject(Assets.main_menu, (Dimen.deviceWidth - Assets.main_menu.getWidth()) / 2, Dimen.apply(600));
    }   

    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
//        game.getInput().getKeyEvents();
        
        if(state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        else if(state == GameState.Paused)
            updatePaused(touchEvents);
        else if(state == GameState.GameOver)
            updateGameOver(touchEvents);   

    }
    
    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            mWorld.updateWithTouchEvent(event);
        }

    	mWorld.update(deltaTime);
        if(mWorld.mGameOver) {
            if(Settings.soundEnabled)
                Assets.bitten.play(1);
            state = GameState.GameOver;
        }
    }
    
    private void updatePaused(List<TouchEvent> touchEvents) {
    	
    }
    
    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(mMainMenuText.isTouchInBounds(event.x, event.y)) {
                    game.setScreen(new MainMenuScreen(game), R.drawable.main_bg3);
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                } else if(mRestartText.isTouchInBounds(event.x, event.y)) {
                        game.setScreen(new GameScreen(game), R.drawable.main_bg);
                        if(Settings.soundEnabled)
                            Assets.click.play(1);
                    return;
                }
            }
        }
    }

    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.clear();
        drawWorld(g, mWorld);
        if(state == GameState.Ready) 
            drawReadyUI(g);
        if(state == GameState.Running)
            drawRunningUI(g);
        if(state == GameState.Paused)
            drawPausedUI(g);
        if(state == GameState.GameOver)
            drawGameOverUI(g);
    }
    
    private void drawWorld(Graphics g, World world) {
        for (Row row : world.mRows) {
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
        g.drawGameObject(world.mBlueNom);
        g.drawGameObject(world.mRedNom);
        g.drawGameObject(world.mYellowNom);
        g.drawGameObject(world.mGreenNom);
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
    
    private void drawReadyUI(Graphics g) {
    	
    }
    
    private void drawRunningUI(Graphics g) {
    	
    }
    
    private void drawPausedUI(Graphics g) {
    	
    }
    
    private void drawGameOverUI(Graphics g) {
    	g.drawARGB(120, 0, 0, 0);
    	g.drawGameObject(mGameOverText);
    	g.drawGameObject(mMainMenuText);
    	g.drawGameObject(mRestartText);
    }

    public void pause() {        
        Settings.save(game.getFileIO());
    }

    public void resume() {

    }

    public void dispose() {

    }
}