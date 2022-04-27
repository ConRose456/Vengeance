package com;

class BackgroundUpdateComponent implements UpdateComponent {

	@Override
	public void update(long fps, Transform t, Transform playerTransform) {
		
		BackgroundTransform bt = (BackgroundTransform) t;
		PlayerTransform pt = (PlayerTransform) playerTransform;
		float currentXClip = bt.getXClip();

		if (playerTransform.headingRight()) {
			currentXClip -= t.getSpeed() / fps;
			bt.setXClip(currentXClip);
		} else if (playerTransform.headingLeft()) {
			currentXClip += t.getSpeed() / fps;
			bt.setXClip(currentXClip);
		}

		if (currentXClip >= t.getSize().x) {
			bt.setXClip(0);
			bt.filpReversedFirst();
		} else if (currentXClip <= 0) {
			bt.setXClip((int) t.getSize().x);
			bt.filpReversedFirst();
		}
	}
}
