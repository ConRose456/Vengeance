package com.GOSpecs;

import com.util.PointF;

public class CandleSpec extends GameObjectSpec {

	private final static String tag = "candle";
	private final static String bitmapName = "candle";
	private final static float speed = 0f;
	private final static PointF size = new PointF(1f, 1f);
	private final static String[] components = {"InanimateGraphicsComponent", "InanimateUpdateComponent"};
	private final static int framesOfAnimation = 1;
	
	public CandleSpec() {
		super(tag, bitmapName, speed, size, components, framesOfAnimation);
	}
}
