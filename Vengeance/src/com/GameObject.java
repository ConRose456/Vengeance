package com;

import java.awt.Graphics;

import com.GOSpecs.GameObjectSpec;
import com.util.PointF;

class GameObject {

	private Transform mTransform;
	
	private boolean mActive = true;
	private String mTag;
	
	private GraphicsComponent mGraphicsComponent;
	private UpdateComponent mUpdateComponent;
	
	void setGraphicsComponent(GraphicsComponent graphics, 
			GameObjectSpec spec, PointF objectSize, int pixelsPerMetre) {
		mGraphicsComponent = graphics;
		graphics.init(spec, objectSize, pixelsPerMetre);
	}
	
	void setMovement(UpdateComponent physics) {
		mUpdateComponent = physics;
	}
	
	void setPlayerInputTransform(PlayerInputComponent s) {
		s.setTransform(mTransform);
	}
	
	void setTransform(Transform t) {
		mTransform = t;
	}
	
	void draw(Graphics g, Camera cam) {
		mGraphicsComponent.draw(g, mTransform, cam);
	}
	
	void update(long fps, Transform playerTransform) {
		mUpdateComponent.update(fps, mTransform, playerTransform);
	}
	
	boolean checkActive() {
		return mActive;
	}
	
	String getTag() {
		return mTag;
	}
	
	void setInactive() {
		mActive = false;
	}
	
	Transform getTransform() {
		return mTransform;
	}
	
	void setTag(String tag) {
		mTag = tag;
	}
	
}
