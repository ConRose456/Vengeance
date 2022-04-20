package com.GOSpecs;

import com.util.PointF;

public class InvisibleDeathSpec extends GameObjectSpec {

	private final static String tag = "death";
	private final static String bitmapName = "death_invisible";
	private final static float speed = 0f;
	private final static PointF size = new PointF(10f, 10f);
	private final static String[] components = {"InanimateUpdateComponent", "InanimateGraphicsComponent"};
	private final static int framesOfAnimation = 1;
	
	public InvisibleDeathSpec() {
		super(tag, bitmapName, speed, size, components, framesOfAnimation);
	}

}
