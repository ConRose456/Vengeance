package com;

import java.util.ArrayList;
import java.util.List;

import com.GOSpecs.*;
import com.Levels.Level;
import com.Levels.LevelOne;
import com.Levels.LevelThree;
import com.Levels.LevelTwo;
import com.util.JSONReader;
import com.util.LevelReader;
import com.util.PointF;

final class LevelManager {

	static int PLAYER_INDEX;
	private ArrayList<GameObject> objects;
	private GameObjectFactory factory;
	
	private ArrayList<List<List<Integer>>> levelData = new ArrayList<>();
	
	LevelManager(GameEngine ge, int pixelsPerMetre) {
		objects = new ArrayList<>();
		factory = new GameObjectFactory(ge, pixelsPerMetre);
	}
	
	void setCurrentLevel(int level) {
		levelData = LevelReader.readImg(level);
	}
	
	void buildGameObjects(GameState gs) {
		gs.resetCoins();
		objects.clear();
		
		for (int i = 2; i > -1; i--) {
			List<List<Integer>> layer = levelData.get(i);
			for (int y = 0; y < layer.size(); y++) {
				for (int x = 0; x < layer.get(y).size(); x++) {
					PointF coords = new PointF(x, y);
					int objectId = layer.get(y).get(x);
					if (objectId != 255) {
						System.out.println(objectId);
						objects.add(factory.create(JSONReader.getObjectSpec(objectId), coords));
						if (objectId == 0) {
							PLAYER_INDEX = objects.size() - 1;
						}
					}
				}
			}
		}
	}
	
	ArrayList<GameObject> getGameObjects() {
		return objects;
	}
}
