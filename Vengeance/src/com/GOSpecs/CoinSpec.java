package com.GOSpecs;

import com.util.PointF;

public class CoinSpec extends GameObjectSpec {

	private final static String tag = "Coin";
	private final static String bitmapName = "coin";
	private final static float speed = 0f;
	private final static PointF size = new PointF(1f, 1f);
	private final static String[] components = {"AnimatedGraphicsComponent", "InanimateUpdateComponent"};
	private final static int framesOfAnimation = 7;
	
	public CoinSpec() {
		super(tag, bitmapName, speed, size, components, framesOfAnimation);
	}

}
