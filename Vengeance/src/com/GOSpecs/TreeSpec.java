package com.GOSpecs;

import com.util.PointF;

public class TreeSpec extends GameObjectSpec {

	private final static String tag = "untouchable";
	private final static String bitmapName = "tree";
	private final static float speed = 0f;
	private final static PointF size = new PointF(8f, 8f);
	private final static String[] components = {"InanimateGraphicsComponent", "InanimateUpdateComponent"};
	private final static int framesOfAnimation = 1;
	
	public TreeSpec() {
		super(tag, bitmapName, speed, size, components, framesOfAnimation);
	}

}
