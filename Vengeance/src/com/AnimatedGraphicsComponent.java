package com;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.GOSpecs.GameObjectSpec;
import com.util.PointF;

class AnimatedGraphicsComponent implements GraphicsComponent {

	private String mBitmapName;
	private Animator mAnimator;
	private Rectangle mSectionToDraw;
	
	private int pixelsPerMetre;
	private PointF objectSize;
	
	@Override
	public void init(GameObjectSpec spec, PointF objectSize, int pixelsPerMetre) {
		
		float totalWidth = objectSize.x * spec.getFramesOfAnimation();
		this.pixelsPerMetre = pixelsPerMetre;
		this.objectSize = objectSize;
		
		mBitmapName = spec.getBitmapName();
		System.out.println(mBitmapName);
		BitmapStore.addBitmap(mBitmapName, new PointF(totalWidth, objectSize.y), pixelsPerMetre, true);
		
		mAnimator = new Animator(BitmapStore.getBitmap(mBitmapName), objectSize.y, objectSize.x,
				spec.getFramesOfAnimation(), pixelsPerMetre);
		
		mSectionToDraw = mAnimator.getCurrentFrame(System.currentTimeMillis());
	}

	@Override
	public void draw(Graphics g, Transform t, Camera cam) {
		
		if (t.headingRight() || t.headingLeft() || t.getSpeed() == 0) {
			mSectionToDraw = mAnimator.getCurrentFrame(System.currentTimeMillis());
		}
		
		Rectangle screenCoordinates = cam.worldToScreen(t.getLocation().x, t.getLocation().y, 
				t.getSize().x, t.getSize().y);
		
		if (t.getFacingRight()) {
			g.drawImage(BitmapStore.getBitmap(mBitmapName), 
					screenCoordinates.x - (screenCoordinates.width / 2), screenCoordinates.y, 
					screenCoordinates.x + screenCoordinates.width, 
					screenCoordinates.y + screenCoordinates.height,
					mSectionToDraw.x, mSectionToDraw.y, 
					mSectionToDraw.x + mSectionToDraw.width, 
					mSectionToDraw.height, null);
		} else if (!t.getFacingRight()) {
			g.drawImage(BitmapStore.getBitmapReversed(mBitmapName), 
					screenCoordinates.x + screenCoordinates.width, screenCoordinates.y, 
					screenCoordinates.x - (screenCoordinates.width / 2), 
					screenCoordinates.y + screenCoordinates.height,
					mSectionToDraw.x + mSectionToDraw.width, mSectionToDraw.y, 
					mSectionToDraw.x + mSectionToDraw.width - mSectionToDraw.width, 
					mSectionToDraw.height, null);
		} 
	}
}
