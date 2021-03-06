package com.nickyfantasy.marblesplash;

import java.util.List;

import android.util.Log;

import com.nickyfantasy.marblesplash.framework.Game;
import com.nickyfantasy.marblesplash.framework.Graphics;
import com.nickyfantasy.marblesplash.framework.Input.TouchEvent;
import com.nickyfantasy.marblesplash.framework.Pixmap;
import com.nickyfantasy.marblesplash.framework.Screen;

public class SelectLevelScreen extends Screen {
	private GameObject mSelectLevelText;
	private GameObject mLv1Button;
	private GameObject mLv2Button;
	private GameObject mLv3Button;
	
    public SelectLevelScreen(Game game) {
        super(game);  
        mSelectLevelText = new GameObject(Assets.selectLevel, (Dimen.deviceWidth - Assets.selectLevel.getWidth()) / 2, 0);
        mLv1Button = new GameObject(Assets.lv1, 0, Assets.selectLevel.getHeight());
        mLv2Button = new GameObject(Assets.lv2, Assets.lv1.getWidth(), Assets.selectLevel.getHeight());
        mLv3Button = new GameObject(Assets.lv3, Assets.lv1.getWidth() * 2, Assets.selectLevel.getHeight());
    }   

    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();       
        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(mLv1Button.isTouchInBounds(event.x, event.y)) {
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
        g.drawGameObject(mSelectLevelText);
        g.drawGameObject(mLv1Button);
        g.drawGameObject(mLv2Button);
        g.drawGameObject(mLv3Button);
    }

    public void pause() {        
        Settings.save(game.getFileIO());
    }

    public void resume() {

    }

    public void dispose() {

    }
}