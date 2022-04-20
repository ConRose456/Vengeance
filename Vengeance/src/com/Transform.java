package com;

import com.util.PointF;
import com.util.RectangleF;

class Transform {
	
	RectangleF mCollider;
	private PointF mLocation;
	private float mSpeed;
	private float mObjectWidth;
	private float mObjectHeight;
	private PointF mStartingPosition;
	private boolean mHeadingUp = false;
	private boolean mHeadingDown = false;
	
	private boolean mFacingRight = true;
	private boolean mHeadingLeft = false;
	private boolean mHeadingRight = false;
	
	Transform(float speed, float objectWidth, float objectHeight, PointF startingLocation) {
		mCollider = new RectangleF();
		mSpeed = speed;
		mObjectWidth = objectWidth;
		mObjectHeight = objectHeight;
		mLocation = startingLocation;
		
		mStartingPosition = new PointF(mLocation.x, mLocation.y);
	}
	
	void updateCollider() {
		mCollider.x = mLocation.x;
		mCollider.y = mLocation.y;
		mCollider.width = mObjectWidth;
		mCollider.height = mObjectHeight;
	}
	
	RectangleF getCollider() {
		return mCollider;
	}
	
	void headUp() {
		mHeadingUp = true;
		mHeadingDown = false;
	}
	
	void headDown() {
		mHeadingUp = false;
		mHeadingDown = true;
	}
	
	boolean headingUp() {
		return mHeadingUp;
	}
	
	boolean headingDown() {
		return mHeadingDown;
	}
	
	float getSpeed() {
		return mSpeed;
	}
	
	PointF getLocation() {
		return mLocation;
	}
	
	PointF getSize() {
		return new PointF((int) mObjectWidth, (int) mObjectHeight);
	}
	
	void headRight() {
		mHeadingRight = true;
		mHeadingLeft = false;
		mFacingRight = true;
	}
	
	void headLeft() {
		mHeadingRight = false;
		mHeadingLeft = true;
		mFacingRight = false;
	}
	
	boolean headingRight() {
		return mHeadingRight;
	}
	
	boolean headingLeft() {
		return mHeadingLeft;
	}
	
	void stopHorizontal() {
		mHeadingLeft = false;
		mHeadingRight = false;
	}
	
	void stopMovingLeft() {
		mHeadingLeft = false;
	}
	
	void stopMovingRight() {
		mHeadingRight = false;
	}
	
	boolean getFacingRight() {
		return mFacingRight;
	}
	
	PointF getStartingPosition() {
		return mStartingPosition;
	}
	

}
