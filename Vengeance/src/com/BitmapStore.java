package com;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.util.PointF;

public class BitmapStore {

	private static Map<String, Image> mBitmapMap;
	private static Map<String, Image> mBitmapReversedMap;
	private static BitmapStore mOurInstance;
	
	public static BitmapStore getInstance() {
		if (mOurInstance == null) {
			mOurInstance = new BitmapStore();
		}
		return mOurInstance;
	}
	
	private BitmapStore() {
		mBitmapMap = new HashMap<>();
		mBitmapReversedMap = new HashMap<>();
		
		addBitmap("death_visible", new PointF(1f, 1f), 128, true);
	}
	
	public static Image getBitmap(String bitmapName) {
		if (mBitmapMap.containsKey(bitmapName)) {
			return mBitmapMap.get(bitmapName);
		}
		return mBitmapMap.get("death_visible");
	}
	
	public static Image getBitmapReversed(String bitmapName) {
		if (mBitmapReversedMap.containsKey(bitmapName)) {
			return mBitmapReversedMap.get(bitmapName);
		} 
		return mBitmapMap.get("deathVisible");
	}
	
	public static void addBitmap(String bitmapName, PointF objectSize, int pixelsPerMetre, boolean needReversed) {
		
		if (mBitmapMap.containsKey(bitmapName)) {
			return;
		}
		
		BufferedImage bitmap;
		BufferedImage bitmapReversed;
		
		String path = System.getProperty("user.dir") + "/res/images/";
		
		try {
			bitmap = ImageIO.read(new File(path + bitmapName + ".png"));
			mBitmapMap.put(bitmapName, bitmap);
			
			if (needReversed) {
				AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
				tx.translate(-bitmap.getWidth(null), 0);
				AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
				bitmapReversed = op.filter(bitmap, null);
				
				mBitmapReversedMap.put(bitmapName, bitmapReversed);
			}
		} catch (IOException io) {
			System.out.println("Failed to Load Image!");
			try {
				mBitmapMap.put(bitmapName, ImageIO.read(new File(path + "death_visible.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void clearStore() {
		mBitmapMap.clear();
		mBitmapReversedMap.clear();
	}
	
}
