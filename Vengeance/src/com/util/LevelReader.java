package com.util;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class LevelReader {
	
	private static String path;
	private static BufferedImage image = null;
	
	private static ArrayList<List<List<Integer>>> levelData;
	
	private static LevelReader mOurInstance;
	
	public static LevelReader getInstance() {
		if (mOurInstance == null) {
			mOurInstance = new LevelReader();
		}
		return mOurInstance;
	}
	
	private LevelReader() {
		path = System.getProperty("user.dir") + "/src/com/Levels/";
	}
	
	public static ArrayList<List<List<Integer>>> readImg(int level) {
		levelData = new ArrayList<>();
		try {
			image = ImageIO.read(new File(path + level + ".png"));
			
			List<Integer> foregroundLayer;
			List<Integer> middlegroundLayer;
			List<Integer> backgroundLayer;
			
			List<List<Integer>> foregroundData = new ArrayList<>();
			List<List<Integer>> middlegroundData = new ArrayList<>();
			List<List<Integer>> backgroundData = new ArrayList<>();
			
			for (int y = 0; y < image.getHeight(); y++) {
				foregroundLayer = new ArrayList<>();
				middlegroundLayer = new ArrayList<>();
				backgroundLayer = new ArrayList<>();
				for (int x = 0; x < image.getWidth(); x++) {
					
					int pixel = image.getRGB(x, y);
					Color color = new Color(pixel);
					
					int red = color.getRed();
					int green = color.getGreen();
					int blue = color.getBlue();
					int alpha = color.getAlpha();
					
					foregroundLayer.add(red);
					middlegroundLayer.add(green);
					backgroundLayer.add(blue);
				}
				foregroundData.add(foregroundLayer);
				middlegroundData.add(middlegroundLayer);
				backgroundData.add(backgroundLayer);
			}
			
			levelData.add(foregroundData);
			levelData.add(middlegroundData);
			levelData.add(backgroundData);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return levelData;
	}
}
