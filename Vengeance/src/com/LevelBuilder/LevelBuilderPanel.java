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
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JPanel;

import com.BitmapStore;
import com.util.JSONReader;
import com.util.PointF;


class LevelBuilderPanel extends JPanel implements Runnable, MouseListener, KeyListener {
	
	private static final long serialVersionUID = -2080207661414913899L;
	
	private final int WIDTH = 800;
	private final int HEIGHT = 500;
	
	private Thread thread = null;
	
	private int cellSize = 15;
	
	private char objectType = '0';
	
	private ArrayList<String> map = new ArrayList<>();
	private ArrayList<String> background = new ArrayList<>();
	
	private boolean isBackground = false;
	
	LevelBuilderPanel() {
		BitmapStore bs = BitmapStore.getInstance();
		JSONReader js = JSONReader.getInstance();
		
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setFocusable(true);
		this.addKeyListener(this);
		this.addMouseListener(this);
		
		HashMap<String, PointF> data = JSONReader.loadGameObjectsLevelBuilder();
		String path = System.getProperty("user.dir") + "/res/images/";
		
		File[] images = new File(path).listFiles();
		for (File img : images) {
			String fileName = img.getName();
			if (fileName.length() > 4) {
				String name = img.getName().substring(0, img.getName().length() - 4);
				BitmapStore.addBitmap(name, data.get(name), cellSize, false);
			}
		}
		
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
				case 'p':
					g.drawImage(BitmapStore.getBitmap("p"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case '0':
					g.drawImage(BitmapStore.getBitmap("grass"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case '1':
					g.drawImage(BitmapStore.getBitmap("GrassCornerLeft"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case '2':
					g.drawImage(BitmapStore.getBitmap("GrassCornerRight"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case '3':
					g.drawImage(BitmapStore.getBitmap("GrassCornerRocky"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case '4':
					g.drawImage(BitmapStore.getBitmap("CaveWallOne"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case '5':
					g.drawImage(BitmapStore.getBitmap("CaveWall"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case '6':
					g.drawImage(BitmapStore.getBitmap("CaveWallTwo"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case '7':
					g.drawImage(BitmapStore.getBitmap("CaveBackgroundHole"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case '8':
					g.drawImage(BitmapStore.getBitmap("Mushrooms"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case '9':
					g.drawImage(BitmapStore.getBitmap("GrassForeground"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'a':
					g.drawImage(BitmapStore.getBitmap("BushForeground"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'b':
					g.drawImage(BitmapStore.getBitmap("BushBackground"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'c':
					g.drawImage(BitmapStore.getBitmap("Platform"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'd':
					g.drawImage(BitmapStore.getBitmap("WoodPillar"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'e':
					g.drawImage(BitmapStore.getBitmap("MineralBlue"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'f':
					g.drawImage(BitmapStore.getBitmap("Mud"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'g':
					g.drawImage(BitmapStore.getBitmap("HouseBackground"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'h':
					g.drawImage(BitmapStore.getBitmap("HouseForeground"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'i':
					g.drawImage(BitmapStore.getBitmap("MudOne"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'j':
					g.drawImage(BitmapStore.getBitmap("MudTwo"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'k':
					g.drawImage(BitmapStore.getBitmap("CaveWallRight"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'l':
					g.drawImage(BitmapStore.getBitmap("CaveWallLeft"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case '.':
					break;
				}
			}
		}
		
		for (int i = 0; i < map.size(); i++) {
			for (int j = 0; j < map.get(i).length(); j++) {
				switch(map.get(i).charAt(j)) {
				case 'p':
					g.drawImage(BitmapStore.getBitmap("p"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case '0':
					g.drawImage(BitmapStore.getBitmap("grass"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case '1':
					g.drawImage(BitmapStore.getBitmap("GrassCornerLeft"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case '2':
					g.drawImage(BitmapStore.getBitmap("GrassCornerRight"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case '3':
					g.drawImage(BitmapStore.getBitmap("GrassCornerRocky"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case '4':
					g.drawImage(BitmapStore.getBitmap("CaveWallOne"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case '5':
					g.drawImage(BitmapStore.getBitmap("CaveWall"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case '6':
					g.drawImage(BitmapStore.getBitmap("CaveWallTwo"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case '7':
					g.drawImage(BitmapStore.getBitmap("CaveBackgroundHole"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case '8':
					g.drawImage(BitmapStore.getBitmap("Mushrooms"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case '9':
					g.drawImage(BitmapStore.getBitmap("GrassForeground"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'a':
					g.drawImage(BitmapStore.getBitmap("BushForeground"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'b':
					g.drawImage(BitmapStore.getBitmap("BushBackground"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'c':
					g.drawImage(BitmapStore.getBitmap("Platform"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'd':
					g.drawImage(BitmapStore.getBitmap("WoodPillar"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'e':
					g.drawImage(BitmapStore.getBitmap("MineralBlue"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'f':
					g.drawImage(BitmapStore.getBitmap("Mud"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'g':
					g.drawImage(BitmapStore.getBitmap("HouseBackground"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'h':
					g.drawImage(BitmapStore.getBitmap("HouseForeground"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'i':
					g.drawImage(BitmapStore.getBitmap("MudOne"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'j':
					g.drawImage(BitmapStore.getBitmap("MudTwo"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'k':
					g.drawImage(BitmapStore.getBitmap("CaveWallRight"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case 'l':
					g.drawImage(BitmapStore.getBitmap("CaveWallLeft"), j * cellSize, i * cellSize, cellSize, cellSize, null);
					break;
				case '.':
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
		case KeyEvent.VK_P:
			objectType = 'p';
			break;
		case KeyEvent.VK_0:
			objectType = '0';
			break;
		case KeyEvent.VK_1:
			objectType = '1';
			break;
		case KeyEvent.VK_2:
			objectType = '2';
			break;
		case KeyEvent.VK_3:
			objectType = '3';
			break;
		case KeyEvent.VK_4:
			objectType = '4';
			break;
		case KeyEvent.VK_5:
			objectType = '5';
			break;
		case KeyEvent.VK_6:
			objectType = '6';
			break;
		case KeyEvent.VK_7:
			objectType = '7';
			break;
		case KeyEvent.VK_8:
			objectType = '8';
			break;
		case KeyEvent.VK_9:
			objectType = '9';
			break;
		case KeyEvent.VK_A:
			objectType = 'a';
			break;
		case KeyEvent.VK_B:
			objectType = 'b';
			break;
		case KeyEvent.VK_C:
			objectType = 'c';
			break;
		case KeyEvent.VK_D:
			objectType = 'd';
			break;
		case KeyEvent.VK_E:
			objectType = 'e';
			break;
		case KeyEvent.VK_F:
			objectType = 'f';
			break;
		case KeyEvent.VK_G:
			objectType = 'g';
			break;
		case KeyEvent.VK_H:
			objectType = 'h';
			break;
		case KeyEvent.VK_I:
			objectType = 'i';
			break;
		case KeyEvent.VK_J:
			objectType = 'j';
			break;
		case KeyEvent.VK_K:
			objectType = 'k';
			break;
		case KeyEvent.VK_L:
			objectType = 'l';
			break;
		case KeyEvent.VK_PERIOD:
			objectType = '.';
			break;
		}
		
		if (key == KeyEvent.VK_ESCAPE) {
			exportLevel();
		}
		if (key == KeyEvent.VK_TAB) {
			importLevelFile();
		}
		if (key == KeyEvent.VK_SHIFT) {
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
		//background.clear();
		
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
