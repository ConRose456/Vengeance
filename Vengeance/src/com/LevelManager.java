package com;

import java.util.ArrayList;

import com.GOSpecs.*;
import com.Levels.Level;
import com.Levels.LevelOne;
import com.Levels.LevelThree;
import com.Levels.LevelTwo;
import com.util.JSONReader;
import com.util.PointF;

final class LevelManager {

	static int PLAYER_INDEX;
	private ArrayList<GameObject> objects;
	private Level currentLevel;
	private GameObjectFactory factory;
	
	LevelManager(GameEngine ge, int pixelsPerMetre) {
		objects = new ArrayList<>();
		factory = new GameObjectFactory(ge, pixelsPerMetre);
	}
	
	void setCurrentLevel(int level) {
		switch (level) {
		case 1:
			currentLevel = new LevelOne();
			break;
		case 2:
			currentLevel = new LevelTwo();
			break;
		case 3:
			currentLevel = new LevelThree();
			break;
		}
	}
	
	void buildGameObjects(GameState gs) {
		gs.resetCoins();
		objects.clear();
		ArrayList<String> levelToLoad = currentLevel.getTiles();
		ArrayList<String> backgroundLevelToLoad = currentLevel.getBackgroundTiles();
		
		for (int row = 0; row < backgroundLevelToLoad.size(); row++) {
			for (int column = 0; column < backgroundLevelToLoad.get(row).length(); column++) {
				PointF coords = new PointF(column, row);
				
				char objectId = backgroundLevelToLoad.get(row).charAt(column);
				String id = "" + objectId;
				
				if ((int)objectId != 46) {
					objects.add(factory.create(JSONReader.getObjectSpec(id), coords));
				}
			}
		}
		
		for (int row = 0; row < levelToLoad.size(); row++) {
			for (int column = 0; column < levelToLoad.get(row).length(); column++) {
				PointF coords = new PointF(column, row);
				char objectId = levelToLoad.get(row).charAt(column);
				String id = "" + objectId;
				
				if ((int)objectId != 46) {
					objects.add(factory.create(JSONReader.getObjectSpec(id), coords));
					if ((int)objectId == 112) {
						PLAYER_INDEX = objects.size() - 1;
					}
				}
			}
		}
	}
	
	ArrayList<GameObject> getGameObjects() {
		return objects;
	}
}
