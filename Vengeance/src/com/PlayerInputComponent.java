package com;

import java.awt.Point;
import java.awt.event.KeyEvent;

class PlayerInputComponent implements InputObserver {

	private Transform mPlayersTransform;
	private PlayerTransform mPlayersPlayerTransform;
	
	PlayerInputComponent(GameEngine ger) {
		ger.addObserver(this);
	}
	
	void setTransform(Transform transform) {
		mPlayersTransform = transform;
		mPlayersPlayerTransform = (PlayerTransform) mPlayersTransform;
	}
	
	@Override
	public void handleKeyPressedInput(int key, GameState gs) {
		if (!gs.isPaused()) {
			switch (key) {
			case KeyEvent.VK_D:
				mPlayersTransform.headRight();
				break;
			case KeyEvent.VK_A:
				mPlayersTransform.headLeft();
				break;
			case KeyEvent.VK_W:
				mPlayersPlayerTransform.triggerJump();
				break;
			case KeyEvent.VK_RIGHT:
				mPlayersTransform.headRight();
				break;
			case KeyEvent.VK_LEFT:
				mPlayersTransform.headLeft();
				break;
			case KeyEvent.VK_UP:
				mPlayersPlayerTransform.triggerJump();
				break;
			}
		}
	}
	
	@Override
	public void handleKeyReleasedInput(int key, GameState gs) {
		if (!gs.isPaused()) {
			switch (key) {
			case KeyEvent.VK_D:
				mPlayersTransform.stopMovingRight();;
				break;
			case KeyEvent.VK_A:
				mPlayersTransform.stopMovingLeft();
				break;
			case KeyEvent.VK_RIGHT:
				mPlayersTransform.stopMovingRight();
				break;
			case KeyEvent.VK_LEFT:
				mPlayersTransform.stopMovingLeft();
				break;
			}
		}
	}

	@Override
	public void handleMouseInput(Point location, GameState gs) {}
}
