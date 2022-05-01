package com.LevelBuilder;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.BitmapStore;
import com.util.JSONReader;
import com.util.PointF;


class LevelBuilderPanel extends JPanel implements Runnable, MouseListener, KeyListener {
	
	private static final long serialVersionUID = -2080207661414913899L;
	
	private final int HEIGHT = 500;
	private final int GRID_WIDTH = 800;
	private final int SCREEN_WIDTH = 1000;
	
	private Thread thread = null;
	
	private int cellSize = 15;
	
	private int currentObject = 0;
	
	private ArrayList<List<List<Integer>>> levelData = new ArrayList<>();
	private ArrayList<String> bitmapNames = new ArrayList<>();
	
	private HashMap<Integer, Image> images = new HashMap<>();
	private HashMap<String, PointF> gameObjects = new HashMap<>();
	
	private boolean isBackground = false;
	
	private ArrayList<Rectangle> buttons;
	
	private LevelBuilderRenderer renderer;
	private InputObserver inputObserver;
	
	LevelBuilderPanel() {
		
		JSONReader jsonReader = JSONReader.getInstance();
		
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, HEIGHT));
		this.setFocusable(true);
		this.addKeyListener(this);
		this.addMouseListener(this);

		List<List<Integer>> layerR = new ArrayList<>();
		List<List<Integer>> layerG = new ArrayList<>();
		List<List<Integer>> layerB = new ArrayList<>();
		
		levelData.add(layerR);
		levelData.add(layerG);
		levelData.add(layerB);
		
		for (int y = 0; y < HEIGHT / cellSize; y++) {
			levelData.get(0).add(new ArrayList<>());
			levelData.get(1).add(new ArrayList<>());
			levelData.get(2).add(new ArrayList<>());
			for (int x = 0; x < GRID_WIDTH; x += cellSize) {
				levelData.get(0).get(y).add(255);
				levelData.get(1).get(y).add(255);
				levelData.get(2).get(y).add(255);
			}
		}
		
		gameObjects = JSONReader.getData();
		bitmapNames = JSONReader.getBitmapNames();
		
		String path = System.getProperty("user.dir") + "/res/images/";
		for (int i = 0; i < gameObjects.size(); i++) {
			try { 
				images.put(i, ImageIO.read(new File(path + bitmapNames.get(i) + ".png")));
				System.out.println(i + "   :   Name: " + bitmapNames.get(i));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		this.buttons = new ArrayList<>();
		
		int verticalBuffer = 50;
		int buffer = 10;
		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 5; x++) {
				buttons.add(new Rectangle(GRID_WIDTH + 10 + (40 * x), 50 + (40 * y), 20, 20));
			}
		}
		
		this.inputObserver = new InputObserver(this,
				new Point(SCREEN_WIDTH, HEIGHT), new Point(GRID_WIDTH, HEIGHT), cellSize);
		this.renderer = new LevelBuilderRenderer(new Point(SCREEN_WIDTH, HEIGHT), new Point(GRID_WIDTH, HEIGHT),
				cellSize, levelData, images);
		
		startThread();
	}
	
	private void startThread() {
		thread = new Thread(this, "Main Loop");
		thread.start();
	}
	
	@Override
	public void run() {
		while (true) {
			repaint();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		renderer.draw(g, buttons);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_ESCAPE) {
			exportLevel();
		}
		inputObserver.keyInput(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		inputObserver.mouseInput(e, buttons, levelData);
	}
	
	void setCurrentObject(int currentObject) {
		this.currentObject = currentObject;
	}
	
	int getCurrentObject() {
		return this.currentObject;
	}
	
	void updateLevelData(ArrayList<List<List<Integer>>> newLevelData) {
		this.levelData = newLevelData;
	}
	
	private void exportLevel() {
		BufferedImage image = new BufferedImage(2, 1, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		
		for (int y = 0; y < levelData.get(0).size(); y++) {
			for (int x = 0; x < levelData.get(0).get(y).size(); x++) {
				
				System.out.println(levelData.get(0).get(y).get(x));
				
				g.setColor(new Color(
					levelData.get(0).get(y).get(x), 
					levelData.get(1).get(y).get(x), 
					levelData.get(2).get(y).get(x)));
				g.fillRect(x, y, 1, 1);
			}
		}
				
		try {
			ImageIO.write(image, "PNG", new File("levelImage.png"));
			System.out.println("Completed");
		} catch (IOException e) {
			System.out.println("Didnt work");
			e.printStackTrace();
		}
	}
	
	private void importLevelFile() {
		
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}
	@Override
	public void mouseClicked(MouseEvent arg0) {}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
