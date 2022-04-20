package com;

import java.awt.Point;
import java.awt.event.KeyEvent;

class UIController implements InputObserver {
	
	private Point mScreenSize;
	
	UIController(GameEngineBroadcaster b, Point screenSize) {
		this.addObserver(b);
		
		mScreenSize = screenSize;
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
		
		if (gs.isPaused() && gs.isGameOver()) {
			gs.setCurrentLevel(2);
			gs.startNewGame();
		}
	}
}
