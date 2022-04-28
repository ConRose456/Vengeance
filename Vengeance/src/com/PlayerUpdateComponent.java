package com;

import com.util.PointF;

class PlayerUpdateComponent implements UpdateComponent {
	
	private boolean mIsJumping = false;
	private long mJumpStartTime;
	
	private final long MAX_JUMP_TIME = 175;
	private final float GRAVITY = 8;
	
	private float acceleration = 1.4f;

	@Override
	public void update(long fps, Transform t, Transform playerTransform) {
		
		PlayerTransform pt = (PlayerTransform) t;
		PointF location = t.getLocation();
		float speed = t.getSpeed();
		
		if (t.headingRight()) {
			location.x += speed / fps;
		}
		else if (t.headingLeft()) {
			location.x -= speed / fps;
		}
		
		
		if (pt.bumpedHead()) {
			mIsJumping = false;
			pt.handlingBumpedHead();
		}
		
		if (pt.jumpTriggered() && !mIsJumping && pt.isGrounded()) {
			mIsJumping = true;
			pt.handlingJump();
			mJumpStartTime = System.currentTimeMillis();
		}
		
		if (!mIsJumping) {
			location.y += (GRAVITY / fps);
		} else if (mIsJumping) {
			pt.setNotGrounded();
			
			if (System.currentTimeMillis() < mJumpStartTime + (MAX_JUMP_TIME / 1.5)) {
				location.y -= (GRAVITY * 1.8) / fps;
			} 
			else if (System.currentTimeMillis() < mJumpStartTime + MAX_JUMP_TIME) {
				location.y -= GRAVITY / fps;
			} 
			else if (System.currentTimeMillis() < mJumpStartTime + (MAX_JUMP_TIME * 1.2)) {
				
			}
			else if (System.currentTimeMillis() > mJumpStartTime + (MAX_JUMP_TIME * 1.2)) {
				mIsJumping = false;
				pt.handlingJump();
			}
		}
		
		t.updateCollider();
	}
}
