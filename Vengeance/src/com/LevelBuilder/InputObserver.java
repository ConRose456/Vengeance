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
	
	InputObserver(LevelBuilderPanel panelRef, Point screenSize, Point gridSize, int cellSize) {
		this.screenSize = screenSize;
		this.gridSize = gridSize;
		this.panelReference = panelRef;
		
		this.cellSize = cellSize;
	}

	void keyInput(KeyEvent e) {
		
	}
	
	void mouseInput(MouseEvent e, ArrayList<Rectangle> buttons, ArrayList<List<List<Integer>>> levelData) {
		Point location = e.getPoint();
		
		for (int y = 0; y < gridSize.y; y += cellSize) {
			for (int x = 0; x < gridSize.x; x+= cellSize) {
				if (new Rectangle(x, y, cellSize, cellSize).contains(location)) {
					levelData.get(layer()).get(y / cellSize).set(x / cellSize, panelReference.getCurrentObject());
				}
			}
		}
		for (int y = 0; y < levelData.get(0).size(); y++) {
			for (int x = 0; x < levelData.get(0).size(); x ++) {
				System.out.println("x: " + x + "  y: " + y);
			}
		}
		panelReference.updateLevelData(levelData);
		
		int i = 0;
		for (Rectangle button : buttons) {
			if (button.contains(location)) {
				panelReference.setCurrentObject(i);
				System.out.println();
			}
			i++;
		}
	}
	
	private int layer() {
		return 0;
	}
	
}
