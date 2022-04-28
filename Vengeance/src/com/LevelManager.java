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
				switch(backgroundLevelToLoad.get(row).charAt(column)) {
				case '0':
					objects.add(factory.create(JSONReader.getObjectSpec("0"), coords));
					break;
				case '1':
					objects.add(factory.create(JSONReader.getObjectSpec("1"), coords));
					break;
				case '2':
					objects.add(factory.create(JSONReader.getObjectSpec("2"), coords));
					break;
				case '3':
					objects.add(factory.create(JSONReader.getObjectSpec("3"), coords));
					break;
				case '4':
					objects.add(factory.create(JSONReader.getObjectSpec("4"), coords));
					break;
				case '5':
					objects.add(factory.create(JSONReader.getObjectSpec("5"), coords));
					break;
				case '6':
					objects.add(factory.create(JSONReader.getObjectSpec("6"), coords));
					break;
				case '7':
					objects.add(factory.create(JSONReader.getObjectSpec("7"), coords));
					break;
				case '8':
					objects.add(factory.create(JSONReader.getObjectSpec("8"), coords));
					break;
				case '9':
					objects.add(factory.create(JSONReader.getObjectSpec("9"), coords));
					break;
				case 'a':
					objects.add(factory.create(JSONReader.getObjectSpec("a"), coords));
					break;
				case 'b':
					objects.add(factory.create(JSONReader.getObjectSpec("b"), coords));
					break;
				case 'c':
					objects.add(factory.create(JSONReader.getObjectSpec("c"), coords));
					break;
				case 'd':
					objects.add(factory.create(JSONReader.getObjectSpec("d"), coords));
					break;
				case 'e':
					objects.add(factory.create(JSONReader.getObjectSpec("e"), coords));
					break;
				case 'f':
					objects.add(factory.create(JSONReader.getObjectSpec("f"), coords));
					break;
				case 'g':
					objects.add(factory.create(JSONReader.getObjectSpec("g"), coords));
					break;
				case 'h':
					objects.add(factory.create(JSONReader.getObjectSpec("h"), coords));
					break;
				case 'i':
					objects.add(factory.create(JSONReader.getObjectSpec("i"), coords));
					break;
				case 'j':
					objects.add(factory.create(JSONReader.getObjectSpec("j"), coords));
					break;
				case 'k':
					objects.add(factory.create(JSONReader.getObjectSpec("k"), coords));
					break;
				case 'l':
					objects.add(factory.create(JSONReader.getObjectSpec("l"), coords));
					break;
				case 'm':
					objects.add(factory.create(JSONReader.getObjectSpec("m"), coords));
					break;
				case 'n':
					objects.add(factory.create(JSONReader.getObjectSpec("n"), coords));
					break;
				case 'o':
					objects.add(factory.create(JSONReader.getObjectSpec("o"), coords));
					break;
				case 'q':
					objects.add(factory.create(JSONReader.getObjectSpec("q"), coords));
					break;
				case 'r':
					objects.add(factory.create(JSONReader.getObjectSpec("r"), coords));
					break;
				case 's':
					objects.add(factory.create(JSONReader.getObjectSpec("s"), coords));
					break;
				case 't':
					objects.add(factory.create(JSONReader.getObjectSpec("t"), coords));
					break;
				case 'u':
					objects.add(factory.create(JSONReader.getObjectSpec("u"), coords));
					break;
				case 'v':
					objects.add(factory.create(JSONReader.getObjectSpec("v"), coords));
					break;
				case 'w':
					objects.add(factory.create(JSONReader.getObjectSpec("w"), coords));
					break;
				case 'x':
					objects.add(factory.create(JSONReader.getObjectSpec("x"), coords));
					break;
				case 'y':
					objects.add(factory.create(JSONReader.getObjectSpec("y"), coords));
					break;
				case 'z':
					objects.add(factory.create(JSONReader.getObjectSpec("z"), coords));
					break;
				case '>':
					objects.add(factory.create(JSONReader.getObjectSpec(">"), coords));
					break;
				case '<':
					objects.add(factory.create(JSONReader.getObjectSpec("<"), coords));
					break;
				case ';':
					objects.add(factory.create(JSONReader.getObjectSpec(";"), coords));
					break;
				case ':':
					objects.add(factory.create(JSONReader.getObjectSpec(":"), coords));
					break;
				case '.':
					break;
				}
			}
		}
		
		for (int row = 0; row < levelToLoad.size(); row++) {
			for (int column = 0; column < levelToLoad.get(row).length(); column++) {
				PointF coords = new PointF(column, row);
				switch(levelToLoad.get(row).charAt(column)) {
				case 'p':
					objects.add(factory.create(JSONReader.getObjectSpec("p"), coords));
					PLAYER_INDEX = objects.size() - 1;
					break;
				case '0':
					objects.add(factory.create(JSONReader.getObjectSpec("0"), coords));
					break;
				case '1':
					objects.add(factory.create(JSONReader.getObjectSpec("1"), coords));
					break;
				case '2':
					objects.add(factory.create(JSONReader.getObjectSpec("2"), coords));
					break;
				case '3':
					objects.add(factory.create(JSONReader.getObjectSpec("3"), coords));
					break;
				case '4':
					objects.add(factory.create(JSONReader.getObjectSpec("4"), coords));
					break;
				case '5':
					objects.add(factory.create(JSONReader.getObjectSpec("5"), coords));
					break;
				case '6':
					objects.add(factory.create(JSONReader.getObjectSpec("6"), coords));
					break;
				case '7':
					objects.add(factory.create(JSONReader.getObjectSpec("7"), coords));
					break;
				case '8':
					objects.add(factory.create(JSONReader.getObjectSpec("8"), coords));
					break;
				case '9':
					objects.add(factory.create(JSONReader.getObjectSpec("9"), coords));
					break;
				case 'a':
					objects.add(factory.create(JSONReader.getObjectSpec("a"), coords));
					break;
				case 'b':
					objects.add(factory.create(JSONReader.getObjectSpec("b"), coords));
					break;
				case 'c':
					objects.add(factory.create(JSONReader.getObjectSpec("c"), coords));
					break;
				case 'd':
					objects.add(factory.create(JSONReader.getObjectSpec("d"), coords));
					break;
				case 'e':
					objects.add(factory.create(JSONReader.getObjectSpec("e"), coords));
					break;
				case 'f':
					objects.add(factory.create(JSONReader.getObjectSpec("f"), coords));
					break;
				case 'g':
					objects.add(factory.create(JSONReader.getObjectSpec("g"), coords));
					break;
				case 'h':
					objects.add(factory.create(JSONReader.getObjectSpec("h"), coords));
					break;
				case 'i':
					objects.add(factory.create(JSONReader.getObjectSpec("i"), coords));
					break;
				case 'j':
					objects.add(factory.create(JSONReader.getObjectSpec("j"), coords));
					break;
				case 'k':
					objects.add(factory.create(JSONReader.getObjectSpec("k"), coords));
					break;
				case 'l':
					objects.add(factory.create(JSONReader.getObjectSpec("l"), coords));
					break;
				case 'm':
					objects.add(factory.create(JSONReader.getObjectSpec("m"), coords));
					break;
				case 'n':
					objects.add(factory.create(JSONReader.getObjectSpec("n"), coords));
					break;
				case 'o':
					objects.add(factory.create(JSONReader.getObjectSpec("o"), coords));
					break;
				case 'q':
					objects.add(factory.create(JSONReader.getObjectSpec("q"), coords));
					break;
				case 'r':
					objects.add(factory.create(JSONReader.getObjectSpec("r"), coords));
					break;
				case 's':
					objects.add(factory.create(JSONReader.getObjectSpec("s"), coords));
					break;
				case 't':
					objects.add(factory.create(JSONReader.getObjectSpec("t"), coords));
					break;
				case 'u':
					objects.add(factory.create(JSONReader.getObjectSpec("u"), coords));
					break;
				case 'v':
					objects.add(factory.create(JSONReader.getObjectSpec("v"), coords));
					break;
				case 'w':
					objects.add(factory.create(JSONReader.getObjectSpec("w"), coords));
					break;
				case 'x':
					objects.add(factory.create(JSONReader.getObjectSpec("x"), coords));
					break;
				case 'y':
					objects.add(factory.create(JSONReader.getObjectSpec("y"), coords));
					break;
				case 'z':
					objects.add(factory.create(JSONReader.getObjectSpec("z"), coords));
					break;
				case '>':
					objects.add(factory.create(JSONReader.getObjectSpec(">"), coords));
					break;
				case '<':
					objects.add(factory.create(JSONReader.getObjectSpec("<"), coords));
					break;
				case ';':
					objects.add(factory.create(JSONReader.getObjectSpec(";"), coords));
					break;
				case ':':
					objects.add(factory.create(JSONReader.getObjectSpec(":"), coords));
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
