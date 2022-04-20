package com.GOSpecs;

import com.util.PointF;

public class Stalagmite_Down extends GameObjectSpec {

	private final static String tag = "stalagmite";
	private final static String bitmapName = "stalagmite_down";
	private final static float speed = 0f;
	private final static PointF size = new PointF(1f, 1f);
	private final static String[] components = {"InanimateGraphicsComponent", "InanimateUpdateComponent"};
	private final static int framesOfAnimation = 1;
	
	public Stalagmite_Down() {
		super(tag, bitmapName, speed, size, components, framesOfAnimation);
	}
	
}
