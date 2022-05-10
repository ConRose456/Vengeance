package com.LevelBuilder;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class InputObserver {

	private Point screenSize;
	private Point gridSize;
	private LevelBuilderPanel panelReference;

	private int cellSize;
	private int numOfObjects;

	private boolean debug = false;
	
	InputObserver(LevelBuilderPanel panelRef, Point screenSize, Point gridSize, int cellSize, int numOfObjects) {
		this.screenSize = screenSize;
		this.gridSize = gridSize;
		this.panelReference = panelRef;

		this.cellSize = cellSize;
		this.numOfObjects = numOfObjects;
	}

	void keyInput(KeyEvent e) {

	}

	void mouseInput(MouseEvent e, ArrayList<Rectangle> buttons, ArrayList<Rectangle> layerButtons, ArrayList<List<List<Integer>>> levelData) {
		Point location = e.getPoint();

		for (int y = 0; y < gridSize.y; y += cellSize) {
			for (int x = 0; x < gridSize.x; x += cellSize) {
				if (new Rectangle(x, y, cellSize, cellSize).contains(location)) {
					levelData.get(panelReference.getCurrentLayer()).get(y / cellSize).set(x / cellSize, panelReference.getCurrentObject());
				}
			}
		}
		
		panelReference.updateLevelData(levelData);

		int i = 0;
		for (Rectangle button : buttons) {
			if (button.contains(location)) {
				if (i >= numOfObjects) {
					panelReference.setCurrentObject(255);
				} else {
					panelReference.setCurrentObject(i);
				}
			}
			i++;
		}
		
		int j = 0;
		for (Rectangle button: layerButtons) {
			if (button.contains(location)) {
				panelReference.updateLayer(j);
			}
			j++;
		}
		
		if (debug) {
			printLevelData(levelData);
		}
	}
	
	private void printLevelData(ArrayList<List<List<Integer>>> levelData) {
		for (int i = 0; i < levelData.size(); i++) {
			for (int y = 0; y < levelData.get(0).size(); y++) {
				System.out.println("Info / Data: " + levelData.get(i).get(y));
			}
			System.out.println();
		}
	}
}
