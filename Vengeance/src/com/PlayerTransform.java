package com;

import java.util.ArrayList;

import com.util.PointF;
import com.util.RectangleF;

class PlayerTransform extends Transform {

	private ArrayList<RectangleF> mColliders;
	
	private final float TENTH = .1f;
	private final float HALF = .5f;
	private final float THIRD = .3f;
	private final float FIFTH = .2f;
	private final float FEET_PROTRUSION = 1.2f;
	
	private RectangleF mHeadRectF = new RectangleF();
	private RectangleF mRightRectF = new RectangleF();
	private RectangleF mFeetRectF = new RectangleF();
	private RectangleF mLeftRectF = new RectangleF();
	
	private boolean mJumpTriggered = false;
	private boolean mBumpedHeadTriggered = false;
	
	private boolean mGrounded;
	
	PlayerTransform(float speed, float objectWidth, float objectHeight, PointF startingLocation) {
		super(speed, objectWidth, objectHeight, startingLocation);
		
		mColliders = new ArrayList<>();
		mColliders.add(mFeetRectF);
		mColliders.add(mHeadRectF);
		mColliders.add(mRightRectF);
		mColliders.add(mLeftRectF);
	}
	
	public ArrayList<RectangleF> getColliders() {
		updateColliders();
		return mColliders;
	}
	
	public void updateColliders() {
		PointF location = getLocation();
		float objectWidth = getSize().x;
		float objectHeight = getSize().y;
	
		//Feet
		mColliders.get(0).x = location.x + (objectWidth * 0.45f);
		mColliders.get(0).y = location.y + objectHeight - (objectHeight * 0.15f);
		mColliders.get(0).width = objectWidth - ((objectWidth * 0.45f) * 2);
		mColliders.get(0).height = (objectHeight * FIFTH);
		// Head
		mColliders.get(1).x = location.x + ((objectWidth * 0.45f)) ;
		mColliders.get(1).y = location.y;
		mColliders.get(1).width = objectWidth - ((objectWidth * 0.45f) * 2) ;
		mColliders.get(1).height = (objectHeight * FIFTH);
		// Right
		mColliders.get(2).x = location.x + objectWidth - (objectWidth * FIFTH);
		mColliders.get(2).y = location.y + (objectHeight * 0.45f);
		mColliders.get(2).width = (objectWidth) * (FIFTH);
		mColliders.get(2).height = (objectHeight - ((objectHeight * 0.45f) * 2));
		// Left
		mColliders.get(3).x = location.x;
		mColliders.get(3).y = location.y + (objectHeight * 0.45f);
		mColliders.get(3).width = (objectWidth) * FIFTH;
		mColliders.get(3).height = (objectHeight - ((objectHeight * 0.45f) * 2));
	}
	
	void triggerJump() {
		mJumpTriggered = true;
	}
	
	void handlingJump() {
		mJumpTriggered = false;
	}
	
	boolean jumpTriggered() {
		return mJumpTriggered;
	}
	
	void setNotGrounded() {
		mGrounded = false;
	}
	
	void triggerBumpedHead() {
		mBumpedHeadTriggered = true;
	}
	
	void handlingBumpedHead() {
		mBumpedHeadTriggered = false;
	}
	
	boolean bumpedHead() {
		return mBumpedHeadTriggered;
	}
	
	void notGrounded() {
		mGrounded = false;
	}
	
	void grounded() {
		mGrounded = true;
	}
	
	boolean isGrounded() {
		return mGrounded;
	}
}
