package com;

class InanimateUpdateComponent implements UpdateComponent {

	private boolean mCollidersNotSet = true;
	
	@Override
	public void update(long fps, Transform t, Transform playerTransform) {
		if (mCollidersNotSet) {
			t.updateCollider();
			mCollidersNotSet = false;
		}
	}
}
