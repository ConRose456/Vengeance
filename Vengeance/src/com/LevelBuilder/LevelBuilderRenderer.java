package com.LevelBuilder;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class LevelBuilderRenderer {
	
	private Point screenSize;
	private Point gridSize;
	
	private int cellSize;
	
	private ArrayList<List<List<Integer>>> levelData;
	private HashMap<Integer, Image> images;
	
	public LevelBuilderRenderer(Point screenSize, Point gridSize, int cellSize,
			ArrayList<List<List<Integer>>> levelData, HashMap<Integer, Image> images) {
		this.screenSize = screenSize;
		this.gridSize = gridSize;
		this.cellSize = cellSize;
		this.levelData = levelData;
		this.images = images;
	}

	void draw(Graphics g, ArrayList<Rectangle> buttons, ArrayList<Rectangle> layerButtons, int currentLayer) {
		g.setColor(new Color(100, 100, 100, 255));
		g.fillRect(0, 0, screenSize.x, screenSize.y);
		
		g.setColor(new Color(255, 255, 255, 255));
		for (int y = 0; y < screenSize.y; y+=cellSize) {
			for (int x = 0; x < gridSize.x; x+=cellSize) {
				g.drawLine(0, y, gridSize.x, y);
				g.drawLine(x, 0, x, screenSize.x);
			}
		}
		
		g.drawString("Game Objects", (int) (gridSize.x + (screenSize.x * 0.06f)), screenSize.y / 50);
		
		g.setColor(new Color(0, 0, 0, 100));
		int i = 0;
		for (Rectangle button : buttons) {
			if (i < images.size()) {
				g.drawImage(images.get(i), button.x, button.y, button.width, button.height, null);
			} else {
				g.fillRect(button.x, button.y, button.width, button.height);
			}
			i++;
		}
		
		int j = 0;
		for (Rectangle button : layerButtons) {
			g.setColor(new Color(0, 0, 0, 100));
			g.fillRect(button.x, button.y, button.width, button.height);
			g.setColor(new Color(255, 255, 255, 255));
			g.drawString("" + j, button.x, button.y + button.height);
			j++;
		}
		
		
		g.setColor(new Color(0, 0, 0, 255));
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(5));
		g2d.drawLine(gridSize.x, 0, gridSize.x, screenSize.y);
		
		for (int y = 0; y < levelData.get(0).size(); y++) {
			for (int x = 0; x < levelData.get(0).get(y).size(); x++) {
				if (levelData.get(1).get(y).get(x) > 39 && levelData.get(1).get(y).get(x) < 255) {
					g.setColor(Color.WHITE);
					g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
				}
				g.drawImage(images.get(levelData.get(2).get(y).get(x)), 
						x * cellSize, y * cellSize, cellSize, cellSize, null);
				g.drawImage(images.get(levelData.get(1).get(y).get(x)), 
						x * cellSize, y * cellSize, cellSize, cellSize, null);
				g.drawImage(images.get(levelData.get(0).get(y).get(x)), 
						x * cellSize, y * cellSize, cellSize, cellSize, null);
			}
		}
		
		g.setColor(new Color(255, 255, 255, 255));
		g.drawString("Layer: " + currentLayer, gridSize.x, gridSize.y);
	}
	
	ArrayList<List<List<Integer>>> getLevelData() {
		return levelData;
	}
	
	void setLevelData(ArrayList<List<List<Integer>>> levelData) {
		this.levelData = levelData;
	}
}
