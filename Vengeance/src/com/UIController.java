package com;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.util.RectangleF;

class UIController implements InputObserver {
	
	private Point mScreenSize;
	
	private HUD mHud;
	
	UIController(GameEngineBroadcaster b, HUD hud, Point screenSize) {
		this.addObserver(b);
		
		mScreenSize = screenSize;
		mHud = hud;
	}

	void addObserver(GameEngineBroadcaster b) {
		b.addObserver(this);
	}
	
	@Override
	public void handleKeyPressedInput(int key, GameState gs) {
		
		if (gs.isGameOver() && !gs.isPaused()) {
			if (key == KeyEvent.VK_SPACE) {
				gs.flipPaused();
			}
		}
		else if (gs.isGameOver() && gs.isPaused()) {
			if (key == KeyEvent.VK_SPACE) {
				gs.setCurrentLevel(2);
				gs.startNewGame();
			}
			if (key == KeyEvent.VK_L) {
				gs.flipLevelMenu();
			}
		} else if (!gs.isGameOver() && gs.isPaused()) {
			if (key == KeyEvent.VK_S) {
				gs.death();
			}
		} else if (!gs.isGameOver()) {
			if (key == KeyEvent.VK_ESCAPE) {
				gs.flipPaused();
			}
		}
	}
	
	@Override
	public void handleKeyReleasedInput(int key, GameState gs) {}
	@Override
	public void handleMouseInput(Point location, GameState gs) {
		
		if (gs.isPaused() && gs.isGameOver() && !gs.getLevelMenu()) {
			if (mHud.getMainMenu_LevelButton().contains(location)) {
				gs.flipLevelMenu();
			} else {
				gs.setCurrentLevel(2);
				gs.startNewGame();
			}
		}
		
		if (gs.getLevelMenu()) {
			for (int i = 0; i < mHud.getLevelButtons().size(); i++) {
				if (mHud.getLevelButtons().get(i).contains(location)) {
					gs.flipLevelMenu();
					gs.setCurrentLevel(i + 1);
					gs.startNewGame();
				}
			}
			
			if (mHud.getLevelMenuBackButton().contains(location)) {
				gs.flipLevelMenu();
			}
		}
	}
}
