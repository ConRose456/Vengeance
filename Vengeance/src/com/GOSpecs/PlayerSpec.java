package com.GOSpecs;

import com.util.PointF;

public class PlayerSpec extends GameObjectSpec {

	private final static String tag = "Player";
	private final static String bitmapName = "player";
	private final static float speed = 10f;
	private final static PointF size = new PointF(1.0f, 1.0f);
	private final static String[] components = {"PlayerInputComponent", "AnimatedGraphicsComponent", "PlayerUpdateComponent"};
	private final static int framesOfAnimation = 6	;
	
	public PlayerSpec() {
		super(tag, bitmapName, speed, size, components, framesOfAnimation);
	}

}
