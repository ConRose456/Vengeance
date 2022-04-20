package com;

import java.awt.Image;
import java.awt.Rectangle;

class Animator {
	
	private Rectangle mSourceRect;
	private int mFrameCount;
	private int mCurrentFrame;
	private long mFrameTicker;
	private int mFramePeriod;
	private int mFrameWidth;
	
	Animator(Image bitmap, float frameHeight, float frameWidth, int frameCount, int pixelsPerMetre) {
		
		final int ANIM_FPS = 10;
		
		this.mCurrentFrame = 0;
		this.mFrameCount = frameCount;
		this.mFrameWidth = (int)(bitmap.getWidth(null) / frameCount);
		
		frameHeight = bitmap.getHeight(null);
		
		mSourceRect = new Rectangle(0, 0, this.mFrameWidth, (int)frameHeight);
		mFramePeriod = 1000 / ANIM_FPS;
		mFrameTicker = 0L;
	}
	
	Rectangle getCurrentFrame(long time) {
		if (time > mFrameTicker + mFramePeriod) {
			mFrameTicker = time;
			mCurrentFrame++;
			if (mCurrentFrame >= mFrameCount) {
				mCurrentFrame = 0;
			}
		}
		
		this.mSourceRect.x = mCurrentFrame * mFrameWidth;
		return mSourceRect;
	}
	
}
