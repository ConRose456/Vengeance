package com.GOSpecs;

import com.util.PointF;

public class GrassBlockSpec extends GameObjectSpec {

	private final static String tag = "Inert tile";
	private final static String bitmapName = "grass_block";
	private final static float speed = 0f;
	private final static PointF size = new PointF(1f, 1f);
	private final static String[] components = {"InanimateGraphicsComponent", "InanimateUpdateComponent"};
	private final static int framesOfAnimation = 0;
	
	public GrassBlockSpec() {
		super(tag, bitmapName, speed, size, components, framesOfAnimation);
	}
}
