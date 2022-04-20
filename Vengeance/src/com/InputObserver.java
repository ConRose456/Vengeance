package com;

import java.awt.Point;

interface InputObserver {
	void handleKeyPressedInput(int key, GameState gs);
	void handleKeyReleasedInput(int key, GameState gs);
	void handleMouseInput(Point location, GameState gs);
}
