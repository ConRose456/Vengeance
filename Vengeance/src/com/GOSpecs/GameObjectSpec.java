package com.GOSpecs;

import com.util.PointF;

public abstract class GameObjectSpec {

	private String mTag;
	private String mBitmapName;
	private float mSpeed;
	private PointF mSize;
	private String[] mComponents;
	private int mFramesOfAnimation;
	
	GameObjectSpec(String tag, String bitmapName, float speed, PointF size, String[] components,
			int framesOfAnimation) {
		this.mTag = tag;
		this.mBitmapName = bitmapName;
		this.mSpeed = speed;
		this.mSize = size;
		this.mComponents = components;
		this.mFramesOfAnimation = framesOfAnimation;
	}
	
	public String getTag() {
		return mTag;
	}
	
	public String getBitmapName() {
		return mBitmapName;
	}
	
	public float getSpeed() {
		return mSpeed;
	}
	
	public PointF getSize() {
		return mSize;
	}
	
	public String[] getComponents() {
		return mComponents;
	}
	
	public int getFramesOfAnimation() {
		return mFramesOfAnimation;
	}
	
}
