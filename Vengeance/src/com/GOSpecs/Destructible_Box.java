package com.GOSpecs;

import com.util.PointF;

public class Destructible_Box extends GameObjectSpec {

	private final static String tag = "destructible";
	private final static String bitmapName = "destructible_box";
	private final static float speed = 0f;
	private final static PointF size = new PointF(1f, 1f);
	private final static String[] components = {"InanimateGraphicsComponent", "InanimateUpdateComponent"};
	private final static int framesOfAnimation = 1;
	
	public Destructible_Box() {
		super(tag, bitmapName, speed, size, components, framesOfAnimation);
	}
}
