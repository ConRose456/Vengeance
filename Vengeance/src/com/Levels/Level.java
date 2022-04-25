package com.Levels;

import java.util.ArrayList;

public abstract class Level {
	ArrayList<String> tiles;
	ArrayList<String> backgroundTiles;
	boolean locked;
	
	public ArrayList<String> getTiles() {
		return tiles;
	}
	public ArrayList<String> getBackgroundTiles() {
		return backgroundTiles;
	}
	public boolean locked() {
		return locked;
	}
	public void unlock() {
		locked = false;
	}
}
