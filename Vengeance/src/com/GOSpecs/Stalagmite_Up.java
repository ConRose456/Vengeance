package com.GOSpecs;

import com.util.PointF;

public class Stalagmite_Up extends GameObjectSpec {

	private final static String tag = "stalagmite";
	private final static String bitmapName = "stalagmite_up";
	private final static float speed = 0f;
	private final static PointF size = new PointF(1f, 1f);
	private final static String[] components = {"InanimateGraphicsComponent", "InanimateUpdateComponent"};
	private final static int framesOfAnimation = 1;
	
	public Stalagmite_Up() {
		super(tag, bitmapName, speed, size, components, framesOfAnimation);
	}

}
