package com;

import java.util.ArrayList;

import com.GOSpecs.*;
import com.Levels.Level;
import com.Levels.LevelOne;
import com.Levels.LevelThree;
import com.Levels.LevelTwo;
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
				switch(backgroundLevelToLoad.get(row).charAt(column)) {
				case 'l':
					objects.add(factory.create(new CaveWallSpec(), coords));
					break;
				case '1':
					objects.add(factory.create(new BackgroundSpec(), coords));
					break;
				}
			}
		}
		
		for (int row = 0; row < levelToLoad.size(); row++) {
			for (int column = 0; column < levelToLoad.get(row).length(); column++) {
				PointF coords = new PointF(column, row);
				switch(levelToLoad.get(row).charAt(column)) {
				case 'g':
					objects.add(factory.create(new GrassBlockSpec(), coords));
					break;
				case 'p':
					objects.add(factory.create(new PlayerSpec(), coords));
					PLAYER_INDEX = objects.size() - 1;
					break;
				case 't':
					objects.add(factory.create(new TreeSpec(), coords));
					break;
				case 'm':
					objects.add(factory.create(new MudBlockSpec(), coords));
					break;
				case 'c':
					objects.add(factory.create(new CoinSpec(), coords));
					break;
				case 'd':
					objects.add(factory.create(new InvisibleDeathSpec(), coords));
					break;
				case 'f':
					objects.add(factory.create(new CandleSpec(), coords));
					break;
				case 's':
					objects.add(factory.create(new Stalagmite_Up(), coords));
					break;
				case 'a':
					objects.add(factory.create(new Stalagmite_Down(), coords));
					break;
				case 'b':
					objects.add(factory.create(new Destructible_Box(), coords));
					break;
				case 'y':
					objects.add(factory.create(new TunnelSpec(), coords));
					break;
				case '.':
					break;
				}
			}
		}
	}
	
	ArrayList<GameObject> getGameObjects() {
		return objects;
	}
	
}
