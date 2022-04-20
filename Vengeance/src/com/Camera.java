package com;

import java.awt.Rectangle;

import com.util.PointF;

class Camera {
	
	private PointF mCurrentCameraWorldCentre;
	private Rectangle mConvertedRect;
	private int mPixelsPerMetre;
	private int mScreenCentreX;
	private int mScreenCentreY;
	
	Camera(int screenXResolution, int screenYResolution) {
		this.mScreenCentreX = screenXResolution / 2;
		this.mScreenCentreY = screenYResolution / 2;
		
		final int pixelsPerMetreToResolutionRatio = 18;
		mPixelsPerMetre = screenXResolution / pixelsPerMetreToResolutionRatio;
		
		mConvertedRect = new Rectangle();
		mCurrentCameraWorldCentre = new PointF(0, 0);
	}
	
	int getPixelsPerMetre() {
		return mPixelsPerMetre;
	}
	
	int getYCentre() {
		return mScreenCentreY;
	}
	
	float getCameraWorldCentre() {
		return mCurrentCameraWorldCentre.y;
	}
	
	void setWorldXCentre(PointF worldCentre) {
		mCurrentCameraWorldCentre.x = worldCentre.x;
	}
	
	void setWorldYCentre(PointF worldCentre) {
		mCurrentCameraWorldCentre.y = worldCentre.y;
	}
	
	Rectangle worldToScreen(float objectX, float objectY, float objectWidth, float objectHeight) {
		int left = (int)(mScreenCentreX - ((mCurrentCameraWorldCentre.x - objectX) * mPixelsPerMetre));
		int top = (int)(mScreenCentreY - ((mCurrentCameraWorldCentre.y - objectY) * mPixelsPerMetre));
		int width = (int)(objectWidth * mPixelsPerMetre);
		int height = (int)(objectHeight * mPixelsPerMetre);
		
		mConvertedRect.setBounds(left, top, width, height);
		return mConvertedRect;
	}

}
