package com.LevelBuilder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;

import com.BitmapStore;
import com.util.PointF;


class LevelBuilderPanel extends JPanel implements Runnable, MouseListener, KeyListener {
	
	private static final long serialVersionUID = -2080207661414913899L;
	
	private final int WIDTH = 800;
	private final int HEIGHT = 500;
	
	private Thread thread = null;
	
	private int cellSize = 15;
	
	private char objectType = 'g';
	
	private ArrayList<String> map = new ArrayList<>();
	private ArrayList<String> background = new ArrayList<>();
	
	private boolean isBackground = false;
	
	LevelBuilderPanel() {
		BitmapStore bs = BitmapStore.getInstance();
		
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setFocusable(true);
		this.addKeyListener(this);
		this.addMouseListener(this);
		
		BitmapStore.addBitmap("grass_block", new PointF(1,1), cellSize, false);
		BitmapStore.addBitmap("mud_block", new PointF(1,1), cellSize, false);
		BitmapStore.addBitmap("coin_icon", new PointF(1,1), cellSize, false);
		BitmapStore.addBitmap("player", new PointF(1,1), cellSize, false);
		BitmapStore.addBitmap("death_visible", new PointF(10,10), cellSize, false);
		BitmapStore.addBitmap("tree", new PointF(8,8), cellSize, false);
		BitmapStore.addBitmap("stalagmite_down", new PointF(1,1), cellSize, false);
		BitmapStore.addBitmap("stalagmite_up", new PointF(1,1), cellSize, false);
		BitmapStore.addBitmap("candle", new PointF(1,1), cellSize, false);
		BitmapStore.addBitmap("destructible_box", new PointF(1,1), cellSize, false);
		BitmapStore.addBitmap("cave_wall", new PointF(1,1), cellSize, false);
		
		for (int i = 0; i < HEIGHT / cellSize; i++) {
			String temp = "";
			for (int j = 0; j < WIDTH / cellSize; j++) {
				temp += ".";
			}
			map.add(temp);
			background.add(temp);
		}
		
		startThread();
	}
	
	private void startThread() {
		thread = new Thread(this, "Main Loop");
		thread.start();
	}
	
	@Override
	public void run() {
		while (true) {
			update();
			repaint();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(new Color(100, 100, 100, 255));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(new Color(255, 255, 255, 255));
		for (int i = cellSize; i < WIDTH; i+=cellSize) {
			g.drawLine(i, 0, i, HEIGHT);
		}
		for (int i = cellSize; i < HEIGHT; i+=cellSize) {
			g.drawLine(0, i, WIDTH, i);
		}
		
		for (int i = 0; i < background.size(); i++) {
			for (int j = 0; j < background.get(i).length(); j++) {
				switch(background.get(i).charAt(j)) {
				case 'l':
					g.drawImage(BitmapStore.getBitmap("cave_wall"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				}
			}
		}
		
		for (int i = 0; i < map.size(); i++) {
			for (int j = 0; j < map.get(i).length(); j++) {
				switch(map.get(i).charAt(j)) {
				case 'g':
					g.drawImage(BitmapStore.getBitmap("grass_block"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'm':
					g.drawImage(BitmapStore.getBitmap("mud_block"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'c':
					g.drawImage(BitmapStore.getBitmap("coin_icon"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'd':
					g.drawImage(BitmapStore.getBitmap("death_visible"), j * cellSize, i * cellSize, cellSize * 10, cellSize * 10, null);
					break;
				case 'p':
					g.drawImage(BitmapStore.getBitmap("player"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'b':
					g.drawImage(BitmapStore.getBitmap("destructible_box"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 't':
					g.drawImage(BitmapStore.getBitmap("tree"), j * cellSize, i * cellSize, cellSize * 8, cellSize * 8, null);
					break;
				case 's':
					g.drawImage(BitmapStore.getBitmap("stalagmite_up"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'a':
					g.drawImage(BitmapStore.getBitmap("stalagmite_down"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'f':
					g.drawImage(BitmapStore.getBitmap("candle"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				}
			}
		}
	}
	
	private void update() {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
	
		switch (key) {
		case KeyEvent.VK_G:
			objectType = 'g';
			break;
		case KeyEvent.VK_M:
			objectType = 'm';
			break;
		case KeyEvent.VK_C:
			objectType = 'c';
			break;
		case KeyEvent.VK_T:
			objectType = 't';
			break;
		case KeyEvent.VK_D:
			objectType = 'd';
			break;
		case KeyEvent.VK_P:
			objectType = 'p';
			break;
		case KeyEvent.VK_PERIOD:
			objectType = '.';
			break;
		case KeyEvent.VK_S:
			objectType = 's';
			break;
		case KeyEvent.VK_A:
			objectType = 'a';
			break;
		case KeyEvent.VK_F:
			objectType = 'f';
			break;
		case KeyEvent.VK_B:
			objectType = 'b';
			break;
		case KeyEvent.VK_L:
			objectType = 'l';
			break;
		}
		
		if (key == KeyEvent.VK_ESCAPE) {
			exportLevel();
		}
		if (key == KeyEvent.VK_1) {
			importLevelFile();
		}
		if (key == KeyEvent.VK_2) {
			isBackground = !isBackground;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point location = e.getPoint();
		if (!isBackground) {
			String temp = map.get((int)(location.y / cellSize));
			String result = temp.substring(0, location.x / cellSize) + objectType + temp.substring(location.x / cellSize + 1);
			map.set((int)(location.getY() / cellSize), result);
		} else {
			String temp_b = background.get((int)(location.y / cellSize));
			String result_b = temp_b.substring(0, location.x / cellSize) + objectType + temp_b.substring(location.x / cellSize + 1);
			background.set((int)(location.getY() / cellSize), result_b);
		}
	}
	
	private void exportLevel() {
		try {
			FileWriter exportToFile = new FileWriter("LevelBuilder_New_Map.txt");
			for (int i = 0; i < map.size(); i++) {
				exportToFile.write("tiles.add(\"" + map.get(i) + "\");\n");
			}
			
			exportToFile.write("...........background...\n");
			
			for (int i = 0; i < background.size(); i++) {
				exportToFile.write("backgroundTiles.add(\"" + background.get(i) + "\");\n");
			}
			
			exportToFile.close();
			System.out.println("LevelBuilder Completed Level Saved!");
		} catch (IOException e) {
			System.out.println("LevelBuilder Failed!");
			e.printStackTrace();
		}
		
	}
	
	private void importLevelFile() {
		map.clear();
		background.clear();
		
		boolean inBackground = false;
		try {
			File file = new File("LevelBuilder_New_Map.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				System.out.println(data);
				if (data == "...........background...") {
					inBackground = true;
					System.out.println("true");
				} else {
					if (!inBackground) {
						map.add(data.substring(11, data.length() - 3));
					} else {
						background.add(data.substring(11, data.length() - 3));
					}
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
