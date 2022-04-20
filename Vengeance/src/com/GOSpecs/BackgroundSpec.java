package com.GOSpecs;

import com.util.PointF;

public class BackgroundSpec extends GameObjectSpec {

	private final static String tag = "Background";
	private final static String bitmapName = "background";
	private final static float speed = 0f;
	private final static PointF size = new PointF(800, 500); 
	private final static String[] components = {"BackgroundGraphicsComponent", "BackgroundUpdateComponent"};
	private final static int framesOfAnimation = 1;
	
	public BackgroundSpec() {
		super(tag, bitmapName, speed, size, components, framesOfAnimation);
	}

}
