package com.GOSpecs;

import com.util.PointF;

public class CaveWallSpec extends GameObjectSpec {

	private final static String tag = "Inert";
	private final static String bitmapName = "cave_wall";
	private final static float speed = 0f;
	private final static PointF size = new PointF(1f,1f);
	private final static String[] components = {"InanimateGraphicsComponent", "InanimateUpdateComponent"};
	private final static int framesOfAnimation = 1;
	
	public CaveWallSpec() {
		super(tag, bitmapName, speed, size, components, framesOfAnimation);
	}

}
