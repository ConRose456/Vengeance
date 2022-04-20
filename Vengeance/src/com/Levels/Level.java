package com.Levels;

import java.util.ArrayList;

public abstract class Level {
	ArrayList<String> tiles;
	ArrayList<String> backgroundTiles;
	public ArrayList<String> getTiles() {
		return tiles;
	}
	public ArrayList<String> getBackgroundTiles() {
		return backgroundTiles;
	}
}
