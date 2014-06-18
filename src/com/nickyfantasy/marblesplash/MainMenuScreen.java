package com.nickyfantasy.marblesplash;

import java.util.List;

import android.util.Log;

import com.nickyfantasy.marblesplash.framework.Game;
import com.nickyfantasy.marblesplash.framework.Graphics;
import com.nickyfantasy.marblesplash.framework.Input.TouchEvent;
import com.nickyfantasy.marblesplash.framework.Pixmap;
import com.nickyfantasy.marblesplash.framework.Screen;

public class MainMenuScreen extends Screen {
	private GameObject mPlayButton;
	
    public MainMenuScreen(Game game) {
        super(game);  
        mPlayButton = new GameObject(Assets.play, (game.getGraphics().getWidth() - Assets.play.getWidth()) / 2, 
        		(game.getGraphics().getHeight() - Assets.play.getHeight()) / 2);
    }   

    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();       
        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(mPlayButton.isTouchInBounds(event.x, event.y)) {
                    game.setScreen(new SelectLevelScreen(game), R.drawable.main_bg3);
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
        g.drawGameObject(mPlayButton);

//        for (int i = 0; i < 6; i++) {
//        	int marbleColor = i % 4 + 1; 
//        	Pixmap marbleAsset = null;
//        	if (marbleColor == MarbleColor.RED.getId()) marbleAsset = Assets.redMarble;
//        	if (marbleColor == MarbleColor.YELLOW.getId()) marbleAsset = Assets.yellowMarble;
//        	if (marbleColor == MarbleColor.GREEN.getId()) marbleAsset = Assets.greenMarble;
//        	if (marbleColor == MarbleColor.BLUE.getId()) marbleAsset = Assets.blueMarble;
//        			
//        	g.drawPixmap(marbleAsset, Assets.play.getWidth() * i, Assets.play.getHeight() * i);
//        }
    }

    public void pause() {        
        Settings.save(game.getFileIO());
    }

    public void resume() {

    }

    public void dispose() {

    }
}