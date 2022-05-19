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
		
		this.mScreenSize = screenSize;
		this.mHud = hud;
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
		else if (gs.isGameOver() && gs.isPaused() && !gs.getLevelMenu() && !gs.getOptionsMenu() && !gs.getCustomMenu()) {
			if (key == KeyEvent.VK_ENTER) {
				gs.setCurrentLevel(gs.getHighestReachedLevel());
				gs.startNewGame();
			}
		} else if (!gs.isGameOver() && gs.isPaused() && gs.getLevelCompleted()) {
			if (key == KeyEvent.VK_H) {
				gs.goHome();
			}
			else if (key == KeyEvent.VK_R) {
				gs.setCurrentLevel(gs.getCurrentLevel());
				gs.startNewGame();
			}
			else if (key == KeyEvent.VK_N) {
				gs.setCurrentLevel(gs.getHighestReachedLevel());
				gs.startNewGame();
			}
		} else if (!gs.isGameOver() && gs.isPaused()) {
			if (key == KeyEvent.VK_S) {
				gs.goHome();
			}
			if (key == KeyEvent.VK_ESCAPE) {
				gs.flipPaused();
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
		
		if (gs.isPaused() && gs.isGameOver() && !gs.getLevelMenu() && !gs.getOptionsMenu() && !gs.getCustomMenu()) {
			
			if (mHud.getStartMenuButtons().get("Start").contains(location)) {
				gs.setCurrentLevel(gs.getHighestReachedLevel());
				gs.startNewGame();
			} else if (mHud.getStartMenuButtons().get("Options").contains(location)) {
				gs.flipOptionsMenu();
			} else if (mHud.getStartMenuButtons().get("Exit").contains(location)) {
				System.exit(0);
			} 
			else if (mHud.getMainMenu_LevelButton().contains(location)) {
				gs.flipLevelMenu();
			} else if (mHud.getMainMenu_CustomizationButton().contains(location)) {
				gs.flipCustomMenu();
			} 
		} else if (!gs.isGameOver() && gs.isPaused() && gs.getLevelCompleted()) {
			if (mHud.getCompletedLevelButtons().get("Restart").contains(location)) {
				gs.setCurrentLevel(gs.getCurrentLevel());
				gs.startNewGame();
			}
			else if (mHud.getCompletedLevelButtons().get("Home").contains(location)) {
				gs.goHome();
			}
			else if (mHud.getCompletedLevelButtons().get("NextLevel").contains(location)) {
				gs.setCurrentLevel(gs.getHighestReachedLevel());
				gs.startNewGame();
			}
		} else if (!gs.isGameOver() && gs.isPaused()) {
			if (mHud.getPauseMenuButtons().get("Resume").contains(location)) {
				gs.flipPaused();
			} 
			else if (mHud.getPauseMenuButtons().get("Options").contains(location)) {
				gs.flipOptionsMenu();
			}
			else if (mHud.getPauseMenuButtons().get("Exit").contains(location)) {
				gs.goHome();
			}
		} else if (!gs.isGameOver() && !gs.isPaused()) {
			if (mHud.getInGamePauseButton().contains(location)) {
				gs.flipPaused();
			}
		}
		
		if (gs.getLevelMenu()) {
			for (int i = 0; i < mHud.getLevelButtons().size(); i++) {
				if (mHud.getLevelButtons().get(i).contains(location)) {
					if (gs.getHighestReachedLevel() >= i+1) {
						gs.flipLevelMenu();
						gs.setCurrentLevel(i + 1);
						gs.startNewGame();
					}
				}
			}
			
			if (mHud.getLevelMenuBackButton().contains(location)) {
				gs.flipLevelMenu();
			}
		}
		
		if (gs.getOptionsMenu()) {
			if (mHud.getOptionsMenuBackButton().contains(location)) {
				gs.flipOptionsMenu();
			}
		}
		
		if (gs.getCustomMenu()) {
			if (mHud.getCustomMenuBackButton().contains(location)) {
				gs.flipCustomMenu();
			}
		}
	}
}
